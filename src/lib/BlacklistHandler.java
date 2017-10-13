package lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlacklistHandler {
	
	//Create a new list of Blacklist objects to be filled by CSV file data
	private static List<Blacklist> blacklistList = new ArrayList<Blacklist>();
	
	/**
	 * @return the blacklistArray
	 */
	public static List<Blacklist> getBlacklistList() {
		return blacklistList;
	}

	//Delimiter used in CSV file
	private static final String COMMA_QUOTE_DELIMITER = "\",\"";
	private static final String QUOTE_DELIMITER = "\"";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "Thomasnet Sessions Processor Blacklist";
	
	public static void blacklistWriter(String filename) {
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(filename, false);
			
			//Write the CSV file header and add a new line
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new Blacklist object to the CSV File
			for (Blacklist currentBlacklist : blacklistList) {
				fileWriter.append(QUOTE_DELIMITER);
				fileWriter.append(currentBlacklist.getIpAddress());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentBlacklist.getIspName());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentBlacklist.getDomainName());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentBlacklist.getIndustry());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentBlacklist.getSubIndustry());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentBlacklist.getReferer());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentBlacklist.getSearchTerms());
				fileWriter.append(QUOTE_DELIMITER);
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			
			System.out.println("CSV file was created successfully!");
		} catch (Exception e) {
			System.out.println("Error in CSV Writer!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
				System.out.println("fileWriter flushed and closed");
			} catch (Exception e) {
				System.out.println("Error while flushing/closing filewriter!");
				e.printStackTrace();
			}
		}
	}
	
	public static String BlacklistReader(String filename) {
		
		boolean stop = false;
		
		while (stop == false) {
		
			BufferedReader fileReader = null;
			
			try {
				
				//Clear current BlacklistArray
				clearBlacklistArray();
				
				//Instantiate String to be filled by each line or CSV file
				String line = "";
			
				//Create the file reader
				fileReader = new BufferedReader(new FileReader(filename));
			
				//Validate the file by the header
				String header;
				header = fileReader.readLine().toString();
				if (Validator.validateBlacklistCsv(header) == "nope") {
						stop = true;
						return "incompatible file";
				}
				
				//Read the file line by line starting from the second line
				while ((line = fileReader.readLine()) != null) {
					//Get all tokens available in line
					String[] currentTokens = line.split(COMMA_QUOTE_DELIMITER);
					if (currentTokens.length > 0) {
						//Create a new Blacklist object for the current line
						Blacklist currentBlacklist = new Blacklist();
						// Set all of the properties of the current Blacklist from the current line
						currentBlacklist.setIpAddress(currentTokens[0].replace("\"", "").toString());
						currentBlacklist.setIspName(currentTokens[1].toString());
						currentBlacklist.setDomainName(currentTokens[2].toString());
						currentBlacklist.setIndustry(currentTokens[3].toString());
						currentBlacklist.setSubIndustry(currentTokens[4].toString());
						currentBlacklist.setReferer(currentTokens[5].toString());
						currentBlacklist.setSearchTerms(currentTokens[6].replace("\"", "").toString());
						// Add the filled Blacklist to the Blacklist list
						blacklistList.add(currentBlacklist);
					}
				}
				//print the new Blacklist list
				for (Blacklist currentBlacklist : blacklistList) {
					System.out.println(currentBlacklist.toString());				
				} 
				
			}
			catch (Exception e) {
				System.out.println("Error in CsvFileReader !!!");
				e.printStackTrace();
			} finally {
				try {
					fileReader.close();
				} catch (IOException e) {
					System.out.println("Error while closing fileReader !!!");
					e.printStackTrace();
				}
			}
			stop = true;
			
		}
		return "read successful";
	}
	
	//Add Item To Blacklist
	public static void addToBlacklist(String selection, String typedItem) {
		
		if (selection.equals("IP Address")) {
			Blacklist currentBlacklist = new Blacklist();
			currentBlacklist.setIpAddress(typedItem);
			currentBlacklist.setIspName("");
			currentBlacklist.setDomainName("");
			currentBlacklist.setIndustry("");
			currentBlacklist.setSubIndustry("");
			currentBlacklist.setReferer("");
			currentBlacklist.setSearchTerms("");
			// Add the filled Blacklist to the Blacklist list
			blacklistList.add(currentBlacklist);
		} else if (selection.equals("ISP (Business Name)")) {
			Blacklist currentBlacklist = new Blacklist();
			currentBlacklist.setIpAddress("");
			currentBlacklist.setIspName(typedItem);
			currentBlacklist.setDomainName("");
			currentBlacklist.setIndustry("");
			currentBlacklist.setSubIndustry("");
			currentBlacklist.setReferer("");
			currentBlacklist.setSearchTerms("");
			// Add the filled Blacklist to the Blacklist list
			blacklistList.add(currentBlacklist);
		} else if (selection.equals("Domain Name")) {
			Blacklist currentBlacklist = new Blacklist();
			currentBlacklist.setIpAddress("");
			currentBlacklist.setIspName("");
			currentBlacklist.setDomainName(typedItem);
			currentBlacklist.setIndustry("");
			currentBlacklist.setSubIndustry("");
			currentBlacklist.setReferer("");
			currentBlacklist.setSearchTerms("");
			// Add the filled Blacklist to the Blacklist list
			blacklistList.add(currentBlacklist);
		} else if (selection.equals("Industry (Not Reccomended)")) {
			Blacklist currentBlacklist = new Blacklist();
			currentBlacklist.setIpAddress("");
			currentBlacklist.setIspName("");
			currentBlacklist.setDomainName("");
			currentBlacklist.setIndustry(typedItem);
			currentBlacklist.setSubIndustry("");
			currentBlacklist.setReferer("");
			currentBlacklist.setSearchTerms("");
			// Add the filled Blacklist to the Blacklist list
			blacklistList.add(currentBlacklist);
		} else if (selection.equals("Sub Industry (Not Recommended)")) {
			Blacklist currentBlacklist = new Blacklist();
			currentBlacklist.setIpAddress("");
			currentBlacklist.setIspName("");
			currentBlacklist.setDomainName("");
			currentBlacklist.setIndustry("");
			currentBlacklist.setSubIndustry(typedItem);
			currentBlacklist.setReferer("");
			currentBlacklist.setSearchTerms("");
			// Add the filled Blacklist to the Blacklist list
			blacklistList.add(currentBlacklist);
		} else if (selection.equals("Referrer")) {
			Blacklist currentBlacklist = new Blacklist();
			currentBlacklist.setIpAddress("");
			currentBlacklist.setIspName("");
			currentBlacklist.setDomainName("");
			currentBlacklist.setIndustry("");
			currentBlacklist.setSubIndustry("");
			currentBlacklist.setReferer(typedItem);
			currentBlacklist.setSearchTerms("");
			// Add the filled Blacklist to the Blacklist list
			blacklistList.add(currentBlacklist);
		} else if (selection.equals("Search Terms")) {
			Blacklist currentBlacklist = new Blacklist();
			currentBlacklist.setIpAddress("");
			currentBlacklist.setIspName("");
			currentBlacklist.setDomainName("");
			currentBlacklist.setIndustry("");
			currentBlacklist.setSubIndustry("");
			currentBlacklist.setReferer("");
			currentBlacklist.setSearchTerms(typedItem);
			// Add the filled Blacklist to the Blacklist list
			blacklistList.add(currentBlacklist);
		}
	}	
	
	//Clear The Current Blacklist
	public static void clearBlacklistArray() {
		blacklistList.removeAll(blacklistList);
	}
}
