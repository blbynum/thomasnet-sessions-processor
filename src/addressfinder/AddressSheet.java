package addressfinder;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.Session;
import ui.ThomasNetUI;


public class AddressSheet {
	private final static String FILE_HEADER = "\"Business Name\",\"Address Line 1\",\"City\",\"State\",\"Zip\"";
	private static final String COMMA_QUOTE_DELIMITER = "\",\"";
	private static final String QUOTE_DELIMITER = "\"";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static List<Address> addressList = new ArrayList<Address>();
	public static List<Address> getAddressList() {
		return addressList;
	}

	public static void SheetBuilder(List<Session> sessionList) throws Exception {
		for (Session currentSession : sessionList) {
			Address currentAddress = new Address();
			currentAddress.setBusinessName(currentSession.getIspName());
			String fullAddress = (Seeker.AddressGetter(currentSession.getIspName(), currentSession.getZipCode()));
			if (fullAddress != "No Address Found") {
				Pattern p = Pattern.compile("(.*,).(.*,).(.{2}).(.{5})");
				Matcher m = p.matcher(fullAddress);
				if (m.find()) {
					currentAddress.setAddressLine1(m.group(1));
					if (currentAddress.getAddressLine1().endsWith(","))
						currentAddress.setAddressLine1(currentAddress.getAddressLine1().replace(",", ""));
					currentAddress.setCity(m.group(2));
					if (currentAddress.getCity().endsWith(","))
						currentAddress.setCity(currentAddress.getCity().replace(",", ""));
					currentAddress.setState(m.group(3));
					currentAddress.setZip(m.group(4));
				}
				addressList.add(currentAddress);
			}
		}
		
		FileWriter fileWriter = null;
		String fileName = ThomasNetUI.getSaveFilenameRegional();
		fileName.replaceAll(".csv", "");
		fileName = fileName + " - AddressList - " + sessionList.size() + ".csv";
		
		try{
			fileWriter = new FileWriter(fileName, false);

			//Write the CSV file header and add a new line
			fileWriter.append(FILE_HEADER);
			fileWriter.append(NEW_LINE_SEPARATOR);

			for (Address currentAddress : addressList) {
				fileWriter.append(QUOTE_DELIMITER);
				fileWriter.append(currentAddress.getBusinessName());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentAddress.getAddressLine1());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentAddress.getCity());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentAddress.getState());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentAddress.getZip());
				fileWriter.append(QUOTE_DELIMITER);
				fileWriter.append(NEW_LINE_SEPARATOR);
			} 

			} catch (Exception e) {
				e.printStackTrace();
		} finally {
			try {
				addressList.clear();
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
