/**
 * 
 */
package de.arp.transformer.api;

import java.util.List;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * This interface defines the microservice Transformer
 * @author arp
 *
 */
public interface TransformerAPI {

	public static final String SERVICE_ID = "Transformer-microservice";
	public static final String SERVICE_URL = "/transformer/api/v1";
	
	@Headers("Content-Type: text/plain")
	@RequestLine("GET " + SERVICE_URL + "/_id")
	String getServiceId();
	
	/**
	 * Return the name oa all configured XML filters
	 * @return
	 */
	@RequestLine("GET " + SERVICE_URL + "/filters")
	public List<String> getXMLFilters();
	
	/**
	 * Add a new XSLT stylesheet to the configured XSLT filters
	 * @param name		the name of the stylesheet
	 * @param request	the AddXSLTRequest instance 
	 * @return the name of the Filter
	 */
	@Headers("Content-Type: application/json")
	@RequestLine("PUT /filters/{name}")
	public String addXslt(@Param("name") String name, AddXSLTRequest request);
}
