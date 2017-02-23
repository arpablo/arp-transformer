/**
 * 
 */
package de.arp.transformer.service;

import javax.xml.transform.Templates;

import org.xml.sax.XMLFilter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.arp.transformer.api.XMLFilterInfo;
import de.arp.transformer.api.XMLFilterType;

/**
 * @author arp
 *
 */
public class XMLFilterInfoExt extends XMLFilterInfo {

	@JsonIgnore
	private Class<? extends XMLFilter> filterClass;
	@JsonIgnore
	private Templates template;
	
	
	/**
	 * Constructor
	 * @param name
	 * @param filterClass
	 */
	public XMLFilterInfoExt(String name, Class<? extends XMLFilter> filterClass) {
		super(XMLFilterType.JAVA, name);
		this.filterClass = filterClass;
		this.template = null;
	}

	/**
	 * Constructor
	 * @param name
	 * @param url
	 * @param template
	 */
	public XMLFilterInfoExt(String name, String url, Templates template) {
		super(XMLFilterType.XSLT, name, url);
		this.filterClass = null;
		this.template = template;
	}


	/**
	 * @return the filterClass
	 */
	public Class<? extends XMLFilter> getFilterClass() {
		return filterClass;
	}


	/**
	 * @param filterClass the filterClass to set
	 */
	public void setFilterClass(Class<? extends XMLFilter> filterClass) {
		this.filterClass = filterClass;
	}


	/**
	 * @return the template
	 */
	public Templates getTemplate() {
		return template;
	}


	/**
	 * @param template the template to set
	 */
	public void setTemplate(Templates template) {
		this.template = template;
	}


	/* (non-Javadoc)
	 * @see de.arp.transformer.service.XMLFilterFactory#newFilter()
	 */
	public XMLFilter newFilter() throws InstantiationException, IllegalAccessException{
		if (getType() == XMLFilterType.JAVA) {
			if (filterClass != null) {
				return filterClass.newInstance();
			}
		} else if (getType() == XMLFilterType.XSLT) {
			
		}
		return null;
	}

}
