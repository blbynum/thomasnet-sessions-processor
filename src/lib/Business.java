package lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Ben Bynum
 *
 */
public class Business {
	
	//Create a new list of sessions to be filled by CSV file data
	private static List<Session> sessionList = new ArrayList<Session>();
	private static List<Session> regionalList = new ArrayList<Session>();
	private static List<Session> nationalList = new ArrayList<Session>();
	
	// Get regionalList
	public static List<Session> getRegionalList() {
		return regionalList;
	}
	public static List<Session> getNationalList() {
		return nationalList;
	}
	public static List<Session> getSessionList() {
		return sessionList;
	}

	
	//Delimiter used in CSV file
	private static final String COMMA_QUOTE_DELIMITER = "\",\"";
	private static final String QUOTE_DELIMITER = "\"";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "\"IP Address\",\"ISP Name\",\"Domain Name\",\"Location\",\"Zip Code\","
			+ "\"Industry\",\"Sub Industry\",\"B2B or B2C\",\"Audience\",\"Referer\",\"Search Term\",\"Time\",\"Duration\",\"Pages\",\"Priority\"";
	
	//Method for choosing CSV file to load
	public static String loadFileChooser() {
		String loadFilename;
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV file (*.csv", "CSV");
		fc.setFileFilter(csvFilter);
		fc.showOpenDialog(fc);
		loadFilename = fc.getSelectedFile().getAbsolutePath();
		return loadFilename;
	}
	
	//Method for choosing CSV file to save
	public static String saveFileChooser() {
		
		String saveFilename;
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV file (*.csv", "CSV");
		fc.setFileFilter(csvFilter);
		fc.showSaveDialog(fc);
		saveFilename = fc.getSelectedFile().getAbsolutePath();
		if (saveFilename.endsWith(".csv") == false) {
			saveFilename = saveFilename + ".csv";
		}
		return saveFilename;
	}
	
	public static String readCsvFile(String fileName) {
		
		boolean stop = false;
		
		while (stop == false) {
		
			BufferedReader fileReader = null;
			
			try {
				
				//Instantiate String to be filled by each line or CSV file
				String line = "";
			
				//Create the file reader
				fileReader = new BufferedReader(new FileReader(fileName));
			
				//Validate the file by the header
				String header;
				header = fileReader.readLine();
				if ("incompatible".equals(Validator.validateSessionsCsv(header))) {
						stop = true;
						return "incompatible file";
				}
				
				//Read the file line by line starting from the second line
				while ((line = fileReader.readLine()).length() > 5) {
						
					//Get all tokens available in line
					String[] currentTokens = line.split(COMMA_QUOTE_DELIMITER);
					if (currentTokens.length > 0) {
						//Create a new Session object for the current line
						Session currentSession = new Session();
						// Set all of the properties of the current Session from the current line
						currentSession.setIpAddress(currentTokens[0].replace("\"", ""));
						currentSession.setIspName(currentTokens[1]);
						currentSession.setDomainName(currentTokens[2]);
						currentSession.setLocation(currentTokens[3]);
						currentSession.setZipCode(currentTokens[4]);
						currentSession.setIndustry(currentTokens[5]);
						currentSession.setSubIndustry(currentTokens[6]);
						currentSession.setB2bOrB2c(currentTokens[7]);
						currentSession.setAudience(currentTokens[8]);
						currentSession.setReferer(currentTokens[9]);
						currentSession.setSearchTerms(currentTokens[10]);
						currentSession.setTime(currentTokens[11]);
						currentSession.setDuration(currentTokens[12]);
						
						try {
						String noQuotes = "";
						noQuotes = currentTokens[13];
						noQuotes = noQuotes.replace("\"", "");
						currentSession.setPages(Integer.parseInt(noQuotes));
						} catch (NumberFormatException e) {
							currentSession.setReferer(currentTokens[9] + currentTokens[10]);
							currentSession.setSearchTerms(currentTokens[11]);
							currentSession.setTime(currentTokens[12]);
							currentSession.setDuration(currentTokens[13]);
							try {
								String noQuotes = "";
								noQuotes = currentTokens[14];
								noQuotes = noQuotes.replace("\"", "");
								currentSession.setPages(Integer.parseInt(noQuotes));
							} catch (ArrayIndexOutOfBoundsException ee) {
								currentSession.setPages(1);
							}
						currentSession.setPriority(0);
						}
						currentSession.setJunk(false);
						// Add the filled Session to the Session list
						sessionList.add(currentSession);
					
					}
				}
			
                            //print the new session list
//                            sessionList.forEach((currentSession) -> {
//                                System.out.println(currentSession.toString());
//                            });
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

	

	public static void getPriorities() {
		
		for (Session currentSession : sessionList){
			
			//Filter junk based on audience property
			if (currentSession.getAudience().equalsIgnoreCase("Residential")) {
				currentSession.setJunk(true);
			} else if (currentSession.getAudience().equalsIgnoreCase("Wireless")) {
				currentSession.setJunk(true);
			}
			
			//Find priority and junk status based on pages property
			if (currentSession.getPages() > 12) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getPages() > 5) {
					int currentPriority = currentSession.getPriority();
					currentPriority++;
					currentSession.setPriority(currentPriority);
			} else if (currentSession.getPages() == 1) {
				currentSession.setJunk(true);
			}
			if (currentSession.getPages() > 80) {
				currentSession.setJunk(true);
			}
			
			//Modify priority based on search terms
			if (currentSession.getSearchTerms().length() > 0) {
				int currentPriority = currentSession.getPriority();
				currentPriority++;
				currentSession.setPriority(currentPriority);
			}
						
			//Import BlacklistList from BlacklistHandler
			List<Blacklist> blacklist = new ArrayList<>();
			blacklist = BlacklistHandler.getBlacklistList();
			
			//Compare with blacklist
			for (Blacklist currentBlacklist : blacklist) {
				if (currentSession.getIpAddress().equals(currentBlacklist.getIpAddress()) == true) {
					if (currentSession.getIpAddress().length() > 1)
					currentSession.setJunk(true);
				} else if (currentSession.getIspName().equals(currentBlacklist.getIspName()) == true) {
					if (currentSession.getIspName().length() > 1)
					currentSession.setJunk(true);
				} else if (currentSession.getDomainName().equals(currentBlacklist.getDomainName()) == true) {
					if (currentSession.getDomainName().length() > 1)
					currentSession.setJunk(true);
				} else if (currentSession.getIndustry().equals(currentBlacklist.getIndustry()) == true) {
					if (currentSession.getIndustry().length() > 1)
					currentSession.setJunk(true);
				} else if (currentSession.getSubIndustry().equals(currentBlacklist.getSubIndustry()) == true) {
					if (currentSession.getSubIndustry().length() > 1)
					currentSession.setJunk(true);
				} else if (currentSession.getReferer().equals(currentBlacklist.getReferer()) == true) {
					if (currentSession.getReferer().length() > 1)
					currentSession.setJunk(true);
				} else if (currentSession.getSearchTerms().equals(currentBlacklist.getSearchTerms()) == true) {
					if (currentSession.getSearchTerms().length() > 1)
					currentSession.setJunk(true);
				}
			}
			
			//Modify priority if regional
			if (currentSession.getLocation().contains("MO,  (US)")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
				currentSession.setRegional(true);
			} else if (currentSession.getLocation().contains("IL,  (US)")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
				currentSession.setRegional(true);
			} else if (currentSession.getLocation().contains("IA,  (US)")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
				currentSession.setRegional(true);
			}
			
			//Modify priority if hyperlocal
			if (currentSession.getZipCode().startsWith("630")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getZipCode().startsWith("631")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
				currentSession.setPriority(currentPriority);
			}
			
			//Prioritize major products
			if (currentSession.getSearchTerms().toLowerCase().contains("lyon")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getSearchTerms().toLowerCase().contains("hallowell")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getSearchTerms().toLowerCase().contains("rousseau")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getSearchTerms().toLowerCase().contains("speedrack")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getSearchTerms().toLowerCase().contains("quantum")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getSearchTerms().toLowerCase().contains("mezzanine")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getSearchTerms().toLowerCase().contains("guard")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getSearchTerms().toLowerCase().contains("industrialshelving")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} else if (currentSession.getSearchTerms().toLowerCase().contains("industrial shelving")) {
				int currentPriority = currentSession.getPriority();
				currentPriority = currentPriority + 2;
				currentSession.setPriority(currentPriority);
			} 
			
			//Filter as junk if Session has no search term and few clicks
			if (currentSession.getPages() < 5) {
				if (currentSession.getSearchTerms().equals("")) {
					currentSession.setJunk(true);
				}
			}
			
			//Replace referer URL with term
			if (currentSession.getReferer().toLowerCase().contains("facebook")) {
				currentSession.setReferer("Facebook");
			} else if (currentSession.getReferer().toLowerCase().contains("thomasnet")) {
				currentSession.setReferer("ThomasNet");
			} else if (currentSession.getReferer().toLowerCase().contains("google")) {
				currentSession.setReferer("Google");
			} else if (currentSession.getReferer().toLowerCase().contains("yahoo")) {
				currentSession.setReferer("Yahoo");
			} else if (currentSession.getReferer().toLowerCase().contains("bing")) {
				currentSession.setReferer("Bing");
			} else if (currentSession.getReferer().toLowerCase().contains("ask.com")) {
				currentSession.setReferer("Ask.com");
			} else if (currentSession.getReferer().toLowerCase().contains("myway")) {
				currentSession.setReferer("MyWay Search");
			} else if (currentSession.getReferer().toLowerCase().contains("industrialshelving.com")) {
				currentSession.setReferer("IndustrialShelving.com");
			} 
			
			//Import Whitelist from WhitelistHandler
			List<Whitelist> whitelist = new ArrayList<>();
			whitelist = WhitelistHandler.getWhitelistList();
			
			//Compare with Whitelist
			for (Whitelist currentWhitelist : whitelist) {
				if (currentSession.isJunk() == false) {
					if (currentSession.getIspName().equals(currentWhitelist.getIspName()) == true) {
						if (currentSession.getIspName().length() > 1) {
							int currentPriority = currentSession.getPriority();
							currentPriority = currentPriority + currentWhitelist.getPriorityInc();
							currentSession.setPriority(currentPriority);
						}
					} else if (currentSession.getDomainName().equals(currentWhitelist.getDomainName()) == true) {
						if (currentSession.getDomainName().length() > 1) {
							int currentPriority = currentSession.getPriority();
							currentPriority = currentPriority + currentWhitelist.getPriorityInc();
							currentSession.setPriority(currentPriority);
							}
					} else if (currentSession.getLocation().equals(currentWhitelist.getLocation()) == true) {
						if (currentSession.getLocation().length() > 1) {
							int currentPriority = currentSession.getPriority();
							currentPriority = currentPriority + currentWhitelist.getPriorityInc();
							currentSession.setPriority(currentPriority);
							} 
					} else if (currentSession.getZipCode().equals(currentWhitelist.getZipCode()) == true) {
						if (currentSession.getZipCode().length() > 1) {
							int currentPriority = currentSession.getPriority();
							currentPriority = currentPriority + currentWhitelist.getPriorityInc();
							currentSession.setPriority(currentPriority);
							} 
					} else if (currentSession.getIndustry().equals(currentWhitelist.getIndustry()) == true) {
						if (currentSession.getIndustry().length() > 1) {
							int currentPriority = currentSession.getPriority();
							currentPriority = currentPriority + currentWhitelist.getPriorityInc();
							currentSession.setPriority(currentPriority);
							} 
					} else if (currentSession.getSubIndustry().equals(currentWhitelist.getSubIndustry()) == true) {
						if (currentSession.getSubIndustry().length() > 1) {
							int currentPriority = currentSession.getPriority();
							currentPriority = currentPriority + currentWhitelist.getPriorityInc();
							currentSession.setPriority(currentPriority);
							} 
					} else if (currentSession.getReferer().equals(currentWhitelist.getReferer()) == true) {
						if (currentSession.getReferer().length() > 1) {
							int currentPriority = currentSession.getPriority();
							currentPriority = currentPriority + currentWhitelist.getPriorityInc();
							currentSession.setPriority(currentPriority);
							} 
					} else if (currentSession.getSearchTerms().equals(currentWhitelist.getSearchTerms()) == true) {
						if (currentSession.getSearchTerms().length() > 1) {
							int currentPriority = currentSession.getPriority();
							currentPriority = currentPriority + currentWhitelist.getPriorityInc();
							currentSession.setPriority(currentPriority);
							} 
					}	
				}
			}
		}
	}
	
	//Sort sessionList by priority; highest to lowest
	public static void sortByPriorityHighest() {
		Comparator<Session> comparator = Comparator.comparing(s -> s.getPriority());
		sessionList.sort(comparator.reversed());
	}
	
	//sort sessionList by pages; highest to lowest
	public static void sortByPagesHighest() {
		Comparator<Session> comparator = Comparator.comparing(s -> s.getPages());
		sessionList.sort(comparator.reversed());
	}
	
	//SUPER ULTRA COMPLICATED GOOGLE API INTERFACE --- GET TO IT
	public static void findOnGoogle() {
		
	}
	
	public static String writeCsvFileRegional(String filename) {
	
		
		FileWriter fileWriter = null;
		regionalList.clear();
		
		try {
			fileWriter = new FileWriter(filename, false);
			
			//Write the CSV file header and add a new line
			fileWriter.append(FILE_HEADER);
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new Session object to the CSV File
			for (Session currentSession : sessionList) {
				if (currentSession.isJunk() == false) {
					if (currentSession.isRegional() == true) {
						regionalList.add(currentSession);
						fileWriter.append(QUOTE_DELIMITER);
						fileWriter.append(currentSession.getIpAddress());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getIspName());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getDomainName());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getLocation());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getZipCode());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getIndustry());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getSubIndustry());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getB2bOrB2c());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getAudience());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getReferer());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getSearchTerms());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getTime());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getDuration());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(String.valueOf(currentSession.getPages()));
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(String.valueOf(currentSession.getPriority()));
						fileWriter.append(QUOTE_DELIMITER);
						fileWriter.append(NEW_LINE_SEPARATOR);
					}
				}
			}
			
			System.out.println("CSV file was created successfully!");
			return "CSV file was created successfully!";
		} catch (Exception e) {
			System.out.println("Error in CSV Writer!");
			e.printStackTrace();
			return "Error in CSV Writer!";
			
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
	
	public static String writeCsvFileRegionalWithJunk(String filename) {
	
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(filename, false);
			
			//Write the CSV file header and add a new line
			fileWriter.append(FILE_HEADER);
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new Session object to the CSV File
			for (Session currentSession : sessionList) {
				if (currentSession.isRegional() == true) {
					fileWriter.append(QUOTE_DELIMITER);
					fileWriter.append(currentSession.getIpAddress());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getIspName());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getDomainName());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getLocation());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getZipCode());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getIndustry());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getSubIndustry());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getB2bOrB2c());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getAudience());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getReferer());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getSearchTerms());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getTime());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getDuration());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(String.valueOf(currentSession.getPages()));
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(String.valueOf(currentSession.getPriority()));
					fileWriter.append(QUOTE_DELIMITER);
					fileWriter.append(NEW_LINE_SEPARATOR);
				}
			}
			
			System.out.println("CSV file was created successfully!");
			return "CSV file was created successfully!";
		} catch (Exception e) {
			System.out.println("Error in CSV Writer!");
			e.printStackTrace();
			return "Error in CSV Writer!";
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
	
public static String writeCsvFileNational(String filename) {
	
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(filename, false);
			
			//Write the CSV file header and add a new line
			fileWriter.append(FILE_HEADER);
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new Session object to the CSV File
			for (Session currentSession : sessionList) {
				if (currentSession.isJunk() == false) {
					if (currentSession.isRegional() == false) {
						nationalList.add(currentSession);
						fileWriter.append(QUOTE_DELIMITER);
						fileWriter.append(currentSession.getIpAddress());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getIspName());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getDomainName());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getLocation());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getZipCode());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getIndustry());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getSubIndustry());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getB2bOrB2c());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getAudience());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getReferer());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getSearchTerms());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getTime());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(currentSession.getDuration());
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(String.valueOf(currentSession.getPages()));
						fileWriter.append(COMMA_QUOTE_DELIMITER);
						fileWriter.append(String.valueOf(currentSession.getPriority()));
						fileWriter.append(QUOTE_DELIMITER);
						fileWriter.append(NEW_LINE_SEPARATOR);
					}
				}
			}
			
			System.out.println("CSV file was created successfully!");
			return "CSV file was created successfully!";
		} catch (Exception e) {
			System.out.println("Error in CSV Writer!");
			e.printStackTrace();
			return "Error in CSV Writer!";
			
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
	
	public static String writeCsvFileNationalWithJunk(String filename) {
	
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(filename, false);
			
			//Write the CSV file header and add a new line
			fileWriter.append(FILE_HEADER);
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new Session object to the CSV File
			for (Session currentSession : sessionList) {
				if (currentSession.isRegional() == false) {
					fileWriter.append(QUOTE_DELIMITER);
					fileWriter.append(currentSession.getIpAddress());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getIspName());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getDomainName());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getLocation());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getZipCode());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getIndustry());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getSubIndustry());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getB2bOrB2c());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getAudience());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getReferer());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getSearchTerms());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getTime());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(currentSession.getDuration());
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(String.valueOf(currentSession.getPages()));
					fileWriter.append(COMMA_QUOTE_DELIMITER);
					fileWriter.append(String.valueOf(currentSession.getPriority()));
					fileWriter.append(QUOTE_DELIMITER);
					fileWriter.append(NEW_LINE_SEPARATOR);
				}
			}
			
			System.out.println("CSV file was created successfully!");
			return "CSV file was created successfully!";
		} catch (Exception e) {
			System.out.println("Error in CSV Writer!");
			e.printStackTrace();
			return "Error in CSV Writer!";
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
	
	//Clear sessionList to avoid appending duplicates
	public static void clearSessionLists() {
		sessionList.clear();
		regionalList.clear();
		nationalList.clear();
	}
}
