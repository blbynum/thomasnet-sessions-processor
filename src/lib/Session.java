package lib;

// TODO: Auto-generated Javadoc
/*
 * @author Ben Bynum
 * 
 */

/**
 * The Class Session.
 */
public class Session {
	
	private String ipAddress;
	private String ispName;
	private String domainName;
	private String location;
	private String zipCode;
	private String industry;
	private String subIndustry;
	private String b2bOrB2c;
	private String audience;
	private String referer;
	private String searchTerms;
	private String time;
	private String duration;
	private int pages;
	private int priority;
	private boolean junk;
	private boolean regional;
	
	/**
	 * Instantiates a new session.
	 */
	public Session() {
	
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
	 * Gets the b 2 b or B 2 c.
	 *
	 * @return the b 2 b or B 2 c
	 */
	public String getB2bOrB2c() {
		return b2bOrB2c;
	}

	/**
	 * Sets the b 2 b or B 2 c.
	 *
	 * @param b2bOrB2c the new b 2 b or B 2 c
	 */
	public void setB2bOrB2c(String b2bOrB2c) {
		this.b2bOrB2c = b2bOrB2c;
	}

	/**
	 * Gets the audience.
	 *
	 * @return the audience
	 */
	public String getAudience() {
		return audience;
	}

	/**
	 * Sets the audience.
	 *
	 * @param audience the new audience
	 */
	public void setAudience(String audience) {
		this.audience = audience;
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
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Gets the pages.
	 *
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * Sets the pages.
	 *
	 * @param pages the new pages
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "Session [ipAddress=" + ipAddress + ", ispName=" + ispName + ", domainName=" + domainName +
		                ", location=" + location + ", zipCode=" + zipCode + ", industry=" + industry +
		                ", subIndustry = " + subIndustry + ", b2bOrB2c=" + b2bOrB2c + ", audience=" + audience + 
		                ", referer=" + referer + ", searchTerms=" + searchTerms + ", time=" + time + ", duration=" +
		                duration + ", pages=" + pages + ", priority=" + priority + "]";
	}

	/**
	 * @return the junk
	 */
	public boolean isJunk() {
		return junk;
	}

	/**
	 * @param junk the junk to set
	 */
	public void setJunk(boolean junk) {
		this.junk = junk;
	}

	/**
	 * @return the regional
	 */
	public boolean isRegional() {
		return regional;
	}

	/**
	 * @param regional the regional to set
	 */
	public void setRegional(boolean regional) {
		this.regional = regional;
	}

}
