/**
 * 
 */
package de.arp.transformer.api;

/**
 * @author arp
 *
 */
public class XMLFilterInfo {

	private XMLFilterType type;
	private String name;

	/**
	 * The URL pointing to the stylesheet. This attribute is only set, if the type of the
	 * XMLFilterInfo instance is XSLT 
	 */
	private String url;

	
	/**
	 *	Default constructor 
	 */
	public XMLFilterInfo() {
	}

	/**
	 * @param type
	 * @param name
	 */
	public XMLFilterInfo(XMLFilterType type, String name) {
		this(type, name, null);
	}

	/**
	 * @param type
	 * @param name
	 * @param url
	 */
	public XMLFilterInfo(XMLFilterType type, String name, String url) {
		this.type = type;
		this.name = name;
		this.url = url;
	}

	/**
	 * @return the type
	 */
	public XMLFilterType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(XMLFilterType type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XMLFilterInfo other = (XMLFilterInfo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("XMLFilterInfo [type=%s, name=%s, url=%s]", type, name, url);
	}
	
	
	
}
