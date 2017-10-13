package lib;

public class Validator {

	//Detect ThomasNet Web Traxs Sessions CSV file by header
	private final static String UNPROCESSED_SESSIONS_HEADER = "\"IP Address\",\"ISP Name\",\"Domain Name\",\"Location\",\"Zip Code\",\"Industry\",\"Sub Industry\",\"B2B or B2C\",\"Audience\",\"Referer\",\"Search Term\",\"Time\",\"Duration\",\"Pages\"";
	private final static String UNPROCESSED_SESSIONS_HEADER_2 = "\"IP Address\",\"ISP Name\",\"Domain Name\",\"Location\",\"Zip Code\",\"Industry\",\"Sub Industry\",\"B2B or B2C\",\"Audience\",\"Referer\",\"Search Term\",\"Time\",\"Duration\",\"Pages\",";
	private final static String PROCESSED_SESSIONS_HEADER = "\"IP Address\",\"ISP Name\",\"Domain Name\",\"Location\",\"Zip Code\",\"Industry\",\"Sub Industry\",\"B2B or B2C\",\"Audience\",\"Referer\",\"Search Term\",\"Time\",\"Duration\",\"Pages\",\"Priority\"";
	private final static String BLACKLIST_HEADER = "Thomasnet Sessions Processor Blacklist";
	private final static String WHITELIST_HEADER = "Thomasnet Sessions Processor Whitelist";
	
	public static String validateSessionsCsv(String header) {
	
		if (header.equalsIgnoreCase(UNPROCESSED_SESSIONS_HEADER)) {
			return "unprocessed";
		} else if (header.equalsIgnoreCase(UNPROCESSED_SESSIONS_HEADER_2)) {
			return "unprocessed";
		} else if (header.equalsIgnoreCase(PROCESSED_SESSIONS_HEADER)) {
			return "processed";
		} else {
			return "incompatible";
		}
	}
	
	public static String validateBlacklistCsv(String header) {
		if (header.equalsIgnoreCase(BLACKLIST_HEADER)) {
			return "good";
		} else {
			return "nope";
		}
	}
	
	public static String validateWhitelistCsv(String header) {
		if (header.equalsIgnoreCase(WHITELIST_HEADER)) {
			return "good";
		} else {
			return "nope";
		}
	}
}
