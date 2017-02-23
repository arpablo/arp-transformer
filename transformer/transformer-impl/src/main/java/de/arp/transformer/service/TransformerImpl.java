/**
 * 
 */
package de.arp.transformer.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.arp.transformer.api.AddXSLTRequest;
import de.arp.transformer.api.TransformerAPI;
import de.arp.transformer.api.TransformerService;
import de.arp.transformer.api.XMLFilterInfo;
import de.arp.transformer.api.XMLFilterType;
import de.arp.utils.xml.SAXTransformer;

/**
 * The Implementaion class for Service Transformer
 * @author arp
 *
 */
@RestController
@RequestMapping(TransformerAPI.SERVICE_URL)
public class TransformerImpl implements TransformerService {
	
	private static Logger logger = LoggerFactory.getLogger(TransformerImpl.class);
	
	private Map<String, XMLFilterFactory> filters = new HashMap<String, XMLFilterFactory>();
	
	private SAXTransformerFactory factory;
	
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
		filters.put("loggingFilter", null);
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
	 * @see de.arp.transformer.api.TransformerService#addXslt(java.lang.String, de.arp.transformer.api.AddXSLTRequest)
	 */
	@Override
	@PutMapping("/filters/{name}")
	public XMLFilterInfo addXslt(@PathVariable String name, @RequestBody AddXSLTRequest request) {
		try {
			StreamSource source = new StreamSource(new URL(request.getUrl()).openStream());
			factory.newTemplates(source);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return null;
		}
		XMLFilterInfo info = new XMLFilterInfo(XMLFilterType.XSLT, name);
		return info;
	}
	
	
}