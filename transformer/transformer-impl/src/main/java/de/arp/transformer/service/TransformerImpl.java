/**
 * 
 */
package de.arp.transformer.service;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.XMLFilter;

import de.arp.transformer.api.AddXSLTRequest;
import de.arp.transformer.api.TransformerAPI;
import de.arp.transformer.api.TransformerService;
import de.arp.transformer.api.XMLFilterInfo;
import de.arp.utils.xml.SAXTransformer;
import de.arp.utils.xml.filter.ElementNamesToLowerCaseFilter;
import de.arp.utils.xml.filter.ElementNamesToUpperCaseFilter;
import de.arp.utils.xml.filter.SAXEventLogFilter;

/**
 * The Implementaion class for Service Transformer
 * @author arp
 *
 */
@RestController
@RequestMapping(TransformerAPI.SERVICE_URL)
public class TransformerImpl implements TransformerService {
	
	private static Logger logger = LoggerFactory.getLogger(TransformerImpl.class);
	
	private Map<String, XMLFilterInfoExt> filters = new HashMap<String, XMLFilterInfoExt>();
	private SAXTransformerFactory factory;

	@Autowired
	private ApplicationContext ctx;
	
	/**
	 * Return the ID of the service
	 */
	@GetMapping("/_id")
	public String getServiceId() {
		return TransformerAPI.SERVICE_ID;
	}

	@PostConstruct
	protected void initialize() {
		factory = (SAXTransformerFactory) TransformerFactory.newInstance(SAXTransformer.SAXON_HE_FACTORY, Thread.currentThread().getContextClassLoader());
		addFilter("elementNamesToLowerCaseFilter", ElementNamesToLowerCaseFilter.class);
		addFilter("elementNamesToUpperCaseFilter", ElementNamesToUpperCaseFilter.class);
		addFilter("saxEventLogFilter", SAXEventLogFilter.class);
		addXslt("copyXsltFilter", new AddXSLTRequest("classpath:/xslt/copy.xsl"));
	}
	
	/* (non-Javadoc)
	 * @see de.arp.transformer.api.TransformerService#getXMLFilters()
	 */
	@Override
	@GetMapping("/filters")
	public List<String> getXMLFilters() {
		return new ArrayList<String>(filters.keySet());
	}

	/* (non-Javadoc)
	 * @see de.arp.transformer.api.TransformerService#getXMLFilter(java.lang.String)
	 */
	@Override
	@GetMapping("/filters/{name}")
	public XMLFilterInfo getXMLFilter(@PathVariable String name) {
		return filters.get(name);
	}

	/* (non-Javadoc)
	 * @see de.arp.transformer.api.TransformerService#addXslt(java.lang.String, de.arp.transformer.api.AddXSLTRequest)
	 */
	@Override
	@PutMapping("/filters/{name}")
	public XMLFilterInfo addXslt(@PathVariable String name, @RequestBody AddXSLTRequest request) {
		InputStream in;
		try {
			in = ctx.getResource(request.getUrl()).getInputStream();
		} catch (Exception ex) {
			logger.info("Unable to load resource {}", request.getUrl());
			in = null;
		}
		try {
			if (in == null) {
				in = new URL(request.getUrl()).openStream();
			}
			StreamSource source = new StreamSource(in);
			source.setSystemId(request.getUrl());
			Templates t = factory.newTemplates(source);
			XMLFilterInfoExt info = new XMLFilterInfoExt(name, request.getUrl(), t);
			filters.put(name, info);
			return info;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	public XMLFilterInfo addFilter(String name, Class<? extends XMLFilter> filterClass) {
		XMLFilterInfoExt info = new XMLFilterInfoExt(name, filterClass);
		filters.put(name, info);
		return info;
	}
	
}