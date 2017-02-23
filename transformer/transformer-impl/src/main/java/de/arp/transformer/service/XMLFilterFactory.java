/**
 * 
 */
package de.arp.transformer.service;

import org.xml.sax.XMLFilter;

/**
 * @author arp
 *
 */
public interface XMLFilterFactory {

	/**
	 * Create a new instance of XSLT filter
	 * @return a new XMLFilter
	 */
	public XMLFilter newFilter();
}
