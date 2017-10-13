package lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WhitelistHandler {
	
	//Create a new list of Whitelist objects to be filled by CSV file data
	private static List<Whitelist> whitelistList = new ArrayList<Whitelist>();
	
	/**
	 * @return the whitelistArray
	 */
	public static List<Whitelist> getWhitelistList() {
		return whitelistList;
	}

	//Delimiter used in CSV file
	private static final String COMMA_QUOTE_DELIMITER = "\",\"";
	private static final String QUOTE_DELIMITER = "\"";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "Thomasnet Sessions Processor Whitelist";
	
	public static void whitelistWriter(String filename) {
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(filename, false);
			
			//Write the CSV file header and add a new line
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new Whitelist object to the CSV File
			for (Whitelist currentWhitelist : whitelistList) {
				fileWriter.append(QUOTE_DELIMITER);
				fileWriter.append(currentWhitelist.getIspName());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentWhitelist.getDomainName());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentWhitelist.getLocation());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentWhitelist.getZipCode());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentWhitelist.getIndustry());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentWhitelist.getSubIndustry());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentWhitelist.getReferer());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(currentWhitelist.getSearchTerms());
				fileWriter.append(COMMA_QUOTE_DELIMITER);
				fileWriter.append(String.valueOf(currentWhitelist.getPriorityInc()));
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
	
	public static String WhitelistReader(String filename) {
		
		boolean stop = false;
		
		while (stop == false) {
		
			BufferedReader fileReader = null;
			
			try {
				
				//Clear current WhitelistArray
				clearWhitelistArray();
				
				//Instantiate String to be filled by each line or CSV file
				String line = "";
			
				//Create the file reader
				fileReader = new BufferedReader(new FileReader(filename));
			
				//Validate the file by the header
				String header;
				header = fileReader.readLine().toString();
				if (Validator.validateWhitelistCsv(header) == "nope") {
						stop = true;
						return "incompatible file";
				}
				
				//Read the file line by line starting from the second line
				while ((line = fileReader.readLine()) != null) {
					//Get all tokens available in line
					String[] currentTokens = line.split(COMMA_QUOTE_DELIMITER);
					if (currentTokens.length > 0) {
						//Create a new Whitelist object for the current line
						Whitelist currentWhitelist = new Whitelist();
						// Set all of the properties of the current Whitelist from the current line
						currentWhitelist.setIspName(currentTokens[0].replace("\"", "").toString());
						currentWhitelist.setDomainName(currentTokens[1].toString());
						currentWhitelist.setLocation(currentTokens[2].toString());
						currentWhitelist.setZipCode(currentTokens[3].toString());
						currentWhitelist.setIndustry(currentTokens[4].toString());
						currentWhitelist.setSubIndustry(currentTokens[5].toString());
						currentWhitelist.setReferer(currentTokens[6].toString());
						currentWhitelist.setSearchTerms(currentTokens[7].toString());
						currentWhitelist.setPriorityInc(Integer.parseInt(currentTokens[8].replace("\"", "")));
						// Add the filled Whitelist to the Whitelist list
						whitelistList.add(currentWhitelist);
					}
				}
				//print the new Whitelist list
				for (Whitelist currentWhitelist : whitelistList) {
					System.out.println(currentWhitelist.toString());				
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
	
	//Add Item To Whitelist
	public static void addToWhitelist(String selection, String typedItem, int priorityInc) {
		
		if (selection.equals("ISP (Business Name)")) {
			Whitelist currentWhitelist = new Whitelist();
			currentWhitelist.setIspName(typedItem);
			currentWhitelist.setDomainName("");
			currentWhitelist.setLocation("");
			currentWhitelist.setZipCode("");
			currentWhitelist.setIndustry("");
			currentWhitelist.setSubIndustry("");
			currentWhitelist.setReferer("");
			currentWhitelist.setSearchTerms("");
			currentWhitelist.setPriorityInc(priorityInc);
			// Add the filled Whitelist to the Whitelist list
			whitelistList.add(currentWhitelist);
		} else if (selection.equals("Domain Name")) {
			Whitelist currentWhitelist = new Whitelist();
			currentWhitelist.setIspName("");
			currentWhitelist.setDomainName(typedItem);
			currentWhitelist.setLocation("");
			currentWhitelist.setZipCode("");
			currentWhitelist.setIndustry("");
			currentWhitelist.setSubIndustry("");
			currentWhitelist.setReferer("");
			currentWhitelist.setSearchTerms("");
			currentWhitelist.setPriorityInc(priorityInc);
			// Add the filled Whitelist to the Whitelist list
			whitelistList.add(currentWhitelist);
		} else if (selection.equals("Location")) {
			Whitelist currentWhitelist = new Whitelist();
			currentWhitelist.setIspName("");
			currentWhitelist.setDomainName("");
			currentWhitelist.setLocation(typedItem);
			currentWhitelist.setZipCode("");
			currentWhitelist.setIndustry("");
			currentWhitelist.setSubIndustry("");
			currentWhitelist.setReferer("");
			currentWhitelist.setSearchTerms("");
			currentWhitelist.setPriorityInc(priorityInc);
			// Add the filled Whitelist to the Whitelist list
			whitelistList.add(currentWhitelist);
		} else if (selection.equals("Zip Code")) {
			Whitelist currentWhitelist = new Whitelist();
			currentWhitelist.setIspName("");
			currentWhitelist.setDomainName("");
			currentWhitelist.setLocation("");
			currentWhitelist.setZipCode(typedItem);
			currentWhitelist.setIndustry("");
			currentWhitelist.setSubIndustry("");
			currentWhitelist.setReferer("");
			currentWhitelist.setSearchTerms("");
			currentWhitelist.setPriorityInc(priorityInc);
			// Add the filled Whitelist to the Whitelist list
			whitelistList.add(currentWhitelist);
		} else if (selection.equals("Industry (Not Reccomended)")) {
			Whitelist currentWhitelist = new Whitelist();
			currentWhitelist.setIspName("");
			currentWhitelist.setDomainName("");
			currentWhitelist.setLocation("");
			currentWhitelist.setZipCode("");
			currentWhitelist.setIndustry(typedItem);
			currentWhitelist.setSubIndustry("");
			currentWhitelist.setReferer("");
			currentWhitelist.setSearchTerms("");
			currentWhitelist.setPriorityInc(priorityInc);
			// Add the filled Whitelist to the Whitelist list
			whitelistList.add(currentWhitelist);
		} else if (selection.equals("Sub Industry (Not Recommended)")) {
			Whitelist currentWhitelist = new Whitelist();
			currentWhitelist.setIspName("");
			currentWhitelist.setDomainName("");
			currentWhitelist.setLocation("");
			currentWhitelist.setZipCode("");
			currentWhitelist.setIndustry("");
			currentWhitelist.setSubIndustry(typedItem);
			currentWhitelist.setReferer("");
			currentWhitelist.setSearchTerms("");
			currentWhitelist.setPriorityInc(priorityInc);
			// Add the filled Whitelist to the Whitelist list
			whitelistList.add(currentWhitelist);
		} else if (selection.equals("Referrer")) {
			Whitelist currentWhitelist = new Whitelist();
			currentWhitelist.setIspName("");
			currentWhitelist.setDomainName("");
			currentWhitelist.setLocation("");
			currentWhitelist.setZipCode("");
			currentWhitelist.setIndustry("");
			currentWhitelist.setSubIndustry("");
			currentWhitelist.setReferer(typedItem);
			currentWhitelist.setSearchTerms("");
			currentWhitelist.setPriorityInc(priorityInc);
			// Add the filled Whitelist to the Whitelist list
			whitelistList.add(currentWhitelist);
		} else if (selection.equals("Search Terms")) {
			Whitelist currentWhitelist = new Whitelist();
			currentWhitelist.setIspName("");
			currentWhitelist.setDomainName("");
			currentWhitelist.setLocation("");
			currentWhitelist.setZipCode("");
			currentWhitelist.setIndustry("");
			currentWhitelist.setSubIndustry("");
			currentWhitelist.setReferer("");
			currentWhitelist.setSearchTerms(typedItem);
			currentWhitelist.setPriorityInc(priorityInc);
			// Add the filled Whitelist to the Whitelist list
			whitelistList.add(currentWhitelist);
		}
	}	
	
	//Clear The Current Whitelist
	public static void clearWhitelistArray() {
		whitelistList.removeAll(whitelistList);
	}
}
