package lib;

// TODO: Auto-generated Javadoc
/*
 * @author Ben Bynum
 * 
 */

/**
 * The Class Session.
 */
public class Whitelist {
	
	private String ispName;
	private String domainName;
	private String location;
	private String zipCode;
	private String industry;
	private String subIndustry;
	private String referer;
	private String searchTerms;
	private int priorityInc;
	
	/**
	 * Instantiates a new session.
	 */
	public Whitelist() {
	
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
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code.
	 *
	 * @param zipCode the new zip code
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	/**
	 * @return the priorityIncrement
	 */
	public int getPriorityInc() {
		return priorityInc;
	}

	/**
	 * @param priorityInc the priorityIncrement to set
	 */
	public void setPriorityInc(int priorityInc) {
		this.priorityInc = priorityInc;
	}
}
