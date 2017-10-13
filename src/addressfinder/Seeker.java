package addressfinder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Seeker {

	public static String AddressGetter(String ispName, String zipCode) throws Exception {
		String google = "http://www.google.com/search?q=";
		String search = ispName + " " + zipCode;
		String charset = "UTF-8";
		String userAgent = "Industrial Shelving Systems (+http://industrialshelving.com)";
		String address;
		
		Document links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get();
		String html = links.outerHtml();
		Pattern p = Pattern.compile("<span class=\"_gS\">Address:&nbsp;</span>\\s*<span class=\"_tA\">(.*\\d{5})</span>", Pattern.DOTALL);
		Matcher m = p.matcher(html);
		
		if (m.find()) {
			address = m.group(1);
//			System.out.format("'%s'\n", address);
		} else {
			address = "No Address Found";
		}
		
		return address;

	}

}
