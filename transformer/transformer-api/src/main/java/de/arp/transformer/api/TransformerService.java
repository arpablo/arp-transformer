/**
 * 
 */
package de.arp.transformer.api;

import java.util.List;

/**
 * This interface defines the methods provided by the
 * microservice Transformer
 * @author arp
 *
 */
public interface TransformerService {

	/**
	 * Return the ID of the service
	 */
	String getServiceId();
	
	/**
	 * Return the name oa all configured XML filters
	 * @return
	 */
	public List<String> getXMLFilters();
	
	/**
	 * Add a new XSLT stylesheet to the configured XSLT filters
	 * @param name		the name of the stylesheet
	 * @param request	the AddXSLTRequest instance 
	 * @return the XMLFilterInfo of the Filter
	 */
	public XMLFilterInfo addXslt(String name, AddXSLTRequest request);
	
}
