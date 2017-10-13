package lib;

// TODO: Auto-generated Javadoc
/*
 * @author Ben Bynum
 * 
 */

/**
 * The Class Session.
 */
public class Blacklist {
	
	private String ipAddress;
	private String ispName;
	private String domainName;
	private String industry;
	private String subIndustry;
	private String referer;
	private String searchTerms;
	
	/**
	 * Instantiates a new session.
	 */
	public Blacklist() {
	
	}

	/**
	 * Gets the ip address.
	 *
	 * @return the ip address
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Sets the ip address.
	 *
	 * @param ipAddress the new ip address
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Gets the isp name.
	 *
	 * @return the isp name
	 */
	public String getIspName() {
		return ispName;
	}

	/**
	 * Sets the isp name.
	 *
	 * @param ispName the new isp name
	 */
	public void setIspName(String ispName) {
		this.ispName = ispName;
	}

	/**
	 * Gets the domain name.
	 *
	 * @return the domain name
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * Sets the domain name.
	 *
	 * @param domainName the new domain name
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	/**
	 * Gets the industry.
	 *
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * Sets the industry.
	 *
	 * @param industry the new industry
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * Gets the sub industry.
	 *
	 * @return the sub industry
	 */
	public String getSubIndustry() {
		return subIndustry;
	}

	/**
	 * Sets the sub industry.
	 *
	 * @param subIndustry the new sub industry
	 */
	public void setSubIndustry(String subIndustry) {
		this.subIndustry = subIndustry;
	}


	/**
	 * Gets the referer.
	 *
	 * @return the referer
	 */
	public String getReferer() {
		return referer;
	}

	/**
	 * Sets the referer.
	 *
	 * @param referer the new referer
	 */
	public void setReferer(String referer) {
		this.referer = referer;
	}

	/**
	 * Gets the search terms.
	 *
	 * @return the search terms
	 */
	public String getSearchTerms() {
		return searchTerms;
	}

	/**
	 * Sets the search terms.
	 *
	 * @param searchTerms the new search terms
	 */
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}

}
