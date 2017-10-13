package ui;

import addressfinder.*;
import lib.BlacklistHandler;
import lib.Business;
import lib.WhitelistHandler;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.InputEvent;

public class ThomasNetUI extends JFrame {
	private JTextField textFieldSaveFilenameRegional;
	private JCheckBox chckBxKeepJunk;
	private JButton btnBrowseSaveRegional;
	private JButton btnBrowseLoad;
	private JTextField textFieldLoadFilename;
	private String loadFilename;
	private static String saveFilenameRegional;
	public static String getSaveFilenameRegional() {
		return saveFilenameRegional;
	}
	private String saveFilenameNational;
	private static String loadBlacklist;
	private static String saveBlacklist;
	/**
	 * @return the saveBlacklist
	 */
	public static String getSaveBlacklist() {
		return saveBlacklist;
	}

	/**
	 * @param saveBlacklist the saveBlacklist to set
	 */
	public static void setSaveBlacklist(String saveBlacklist) {
		ThomasNetUI.saveBlacklist = saveBlacklist;
	}
	private static String loadWhitelist;
	private static String saveWhitelist;
	/**
	 * @return the saveWhitelist
	 */
	public static String getSaveWhitelist() {
		return saveWhitelist;
	}

	/**
	 * @param saveWhitelist the saveWhitelist to set
	 */
	public static void setSaveWhitelist(String saveWhitelist) {
		ThomasNetUI.saveWhitelist = saveWhitelist;
	}
	private static JLabel lblStatus;
	private final ButtonGroup buttonGroupSort = new ButtonGroup();
	private JRadioButton rdbtnPriorityHighest;
	private JRadioButton rdbtnTimeNewest;
	private JRadioButton rdbtnPagesHighest;
	private JTextField textFieldSaveFilenameNational;
	private JCheckBox chckbxCustomFileLocations;
	private JButton btnProcessAndSave;
	private JMenuItem mntmAddItemBlacklist;
	private JTextField textFieldBlacklistName;
	private JMenuItem mntmCreateNewBlacklist;
	private JCheckBox chckbxBuildRegAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThomasNetUI frame = new ThomasNetUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				File blacklistCheck = new File(System.getProperty("user.dir") + "/res/Blacklist.csv");
				loadBlacklist = blacklistCheck.toString();
				saveBlacklist = blacklistCheck.toString();
				if (blacklistCheck.exists() && !blacklistCheck.isDirectory()) {
					BlacklistHandler.BlacklistReader(loadBlacklist);
					System.out.println("BL loaded");
				} else {
					BlacklistHandler.blacklistWriter(saveBlacklist);
					System.out.println("BL saved");
				}
				File whitelistCheck = new File(System.getProperty("user.dir") + "/res/Whitelist.csv");
				loadWhitelist = whitelistCheck.toString();
				saveWhitelist = whitelistCheck.toString();
				if (whitelistCheck.exists() && !whitelistCheck.isDirectory()) {
					WhitelistHandler.WhitelistReader(loadWhitelist);
					System.out.println("WL loaded");
				} else {
					WhitelistHandler.whitelistWriter(saveWhitelist);
					System.out.println("WL saved");
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThomasNetUI() {
		setTitle("ThomasNet Sessions Processor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\ThomasNetSessionsProcessor.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		getContentPane().setLayout(null);
		
		JPanel panelFile = new JPanel();
		panelFile.setBorder(new TitledBorder(null, "File", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFile.setBounds(10, 75, 476, 161);
		getContentPane().add(panelFile);
		panelFile.setLayout(null);
		
		textFieldLoadFilename = new JTextField();
		textFieldLoadFilename.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			loadFilename = textFieldLoadFilename.getText();
			}
		});
		textFieldLoadFilename.setBounds(10, 133, 357, 20);
		panelFile.add(textFieldLoadFilename);
		textFieldLoadFilename.setColumns(10);
		
		btnBrowseLoad = new JButton("Browse");
		btnBrowseLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFilename = Business.loadFileChooser();
				textFieldLoadFilename.setText(loadFilename);
			}
		});
		btnBrowseLoad.setBounds(377, 132, 89, 23);
		panelFile.add(btnBrowseLoad);
		
		JLabel lblCsvFileTo = new JLabel("CSV File to Process:");
		lblCsvFileTo.setBounds(10, 108, 144, 14);
		panelFile.add(lblCsvFileTo);
		
		JLabel lblNewFileLocation = new JLabel("New file location for regional list:");
		lblNewFileLocation.setBounds(10, 164, 249, 14);
		panelFile.add(lblNewFileLocation);
		
		textFieldSaveFilenameRegional = new JTextField();
		textFieldSaveFilenameRegional.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				saveFilenameRegional = textFieldSaveFilenameRegional.getText();
			}
		});
		textFieldSaveFilenameRegional.setColumns(10);
		textFieldSaveFilenameRegional.setBounds(10, 189, 357, 20);
		panelFile.add(textFieldSaveFilenameRegional);
		
		btnBrowseSaveRegional = new JButton("Browse");
		btnBrowseSaveRegional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFilenameRegional = Business.saveFileChooser();
				textFieldSaveFilenameRegional.setText(saveFilenameRegional);
			}
		});
		btnBrowseSaveRegional.setBounds(377, 188, 89, 23);
		panelFile.add(btnBrowseSaveRegional);
		
		JLabel lblPleaseChoose = new JLabel("<html>Please choose a CSV file to be processed. Files will generate in<br>original file's direcory.</html>");
		lblPleaseChoose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPleaseChoose.setBounds(10, 11, 456, 47);
		panelFile.add(lblPleaseChoose);
		
		JLabel lblFileToBe = new JLabel("<html>Loaded file must be an unprocessed .csv file downloaded <br> from the ThomasNet Web Traxs Sessions Page. See Help for more info.</html.");
		lblFileToBe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFileToBe.setBounds(10, 69, 456, 28);
		panelFile.add(lblFileToBe);
		
		JLabel lblNewFileLocation_1 = new JLabel("New file location for national/international list:");
		lblNewFileLocation_1.setBounds(10, 220, 300, 14);
		panelFile.add(lblNewFileLocation_1);
		
		textFieldSaveFilenameNational = new JTextField();
		textFieldSaveFilenameNational.setColumns(10);
		textFieldSaveFilenameNational.setBounds(10, 245, 357, 20);
		panelFile.add(textFieldSaveFilenameNational);
		
		JButton buttonNational = new JButton("Browse");
		buttonNational.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveFilenameNational = Business.saveFileChooser();
				textFieldSaveFilenameNational.setText(saveFilenameNational);
			}
		});
		buttonNational.setBounds(377, 244, 89, 23);
		panelFile.add(buttonNational);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 684, 21);
		getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		mnFile.add(mntmExit);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenu mnBlacklist = new JMenu("Blacklist");
		mnOptions.add(mnBlacklist);
		
		mntmAddItemBlacklist = new JMenuItem("Add Item To Blacklist");
		mntmAddItemBlacklist.setEnabled(true);
		mntmAddItemBlacklist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnBlacklist.add(mntmAddItemBlacklist);
		
		mntmCreateNewBlacklist = new JMenuItem("Create New Blacklist");
		mnBlacklist.add(mntmCreateNewBlacklist);
		
		JMenuItem mntmImportBlacklist = new JMenuItem("Import Blacklist");
		mnBlacklist.add(mntmImportBlacklist);
		mntmImportBlacklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent erg0) {
				int response = JOptionPane.showConfirmDialog(null, "<html>WARNING: Importing a blacklist will overwrite the old blacklist. "
						+ "It is recommended that you backup your current blacklist first."
	    				+ "This action cannot be undone.<br> Continue?</html>", "WARNING!", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					String importBlacklist;
					importBlacklist = Business.loadFileChooser();
					BlacklistHandler.BlacklistReader(importBlacklist);
					BlacklistHandler.blacklistWriter(saveBlacklist);
				}

			}
		});
		
		JMenuItem mntmExportBlacklist = new JMenuItem("Export Blacklist (Backup)");
		mnBlacklist.add(mntmExportBlacklist);
		mntmExportBlacklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent erg0) {
				String backupBlacklist;
				backupBlacklist = Business.saveFileChooser();
				BlacklistHandler.blacklistWriter(backupBlacklist);
			}
		});
		
	    mntmCreateNewBlacklist.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent erg0) {
	    		
	    		int response = JOptionPane.showConfirmDialog(null, "<html>WARNING: Creating a new blacklist will overwrite the old Blacklist. "
	    				+ "This cannot be undone.<br> Continue?</html>", "WARNING!", JOptionPane.YES_NO_OPTION);
	    		if (response == JOptionPane.YES_OPTION) {
	    			BlacklistHandler.clearBlacklistArray();
	    			BlacklistHandler.blacklistWriter(saveBlacklist);
	    			try {
	    				AddToBlacklist dialog = new AddToBlacklist();
	    				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    				dialog.setVisible(true);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    		}

	    	}
	    });
		
		mntmAddItemBlacklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddToBlacklist dialog = new AddToBlacklist();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JMenu mnWhitelist = new JMenu("Whitelist");
		mnOptions.add(mnWhitelist);
		
		JMenuItem mntmAddItemToWhitelist = new JMenuItem("Add Item To Whitelist");
		mntmAddItemToWhitelist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		mnWhitelist.add(mntmAddItemToWhitelist);

		mntmAddItemToWhitelist.setEnabled(true);
		mntmAddItemToWhitelist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddToWhitelist dialog = new AddToWhitelist();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmCreateNewWhitelist = new JMenuItem("Create New Whitelist");
		mnWhitelist.add(mntmCreateNewWhitelist);
		
		JMenuItem mntmImportWhitelist = new JMenuItem("Import Whitelist");
		mnWhitelist.add(mntmImportWhitelist);
		
		JMenuItem mntmExportWhitelist = new JMenuItem("Export Whitelist (Backup)");
		mnWhitelist.add(mntmExportWhitelist);
		mntmExportWhitelist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent erg0) {
				String backupWhitelist;
				backupWhitelist = Business.saveFileChooser();
				WhitelistHandler.whitelistWriter(backupWhitelist);
			}
		});
		
		mntmImportWhitelist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent erg0) {
				int response = JOptionPane.showConfirmDialog(null, "<html>WARNING: Importing a whitelist will overwrite the old whitelist. "
						+ "It is recommended that you backup your current whitelist first."
	    				+ "This action cannot be undone.<br> Continue?</html>", "WARNING!", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					String importWhitelist;
					importWhitelist = Business.loadFileChooser();
					WhitelistHandler.WhitelistReader(importWhitelist);
					WhitelistHandler.whitelistWriter(saveWhitelist);
				}

			}
		});
		
		mntmCreateNewWhitelist.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent erg0) {
	    		saveWhitelist = Business.saveFileChooser();
	    		WhitelistHandler.clearWhitelistArray();
	    		WhitelistHandler.whitelistWriter(saveWhitelist);
				mntmAddItemToWhitelist.setEnabled(true);
	    		try {
					AddToWhitelist dialog = new AddToWhitelist();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    });
		
		JMenuItem mntmAdvanced = new JMenuItem("Advanced");
		mnOptions.add(mntmAdvanced);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);
		
		JPanel panelProcessingOptions = new JPanel();
		panelProcessingOptions.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Processing Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelProcessingOptions.setBounds(496, 75, 178, 95);
		getContentPane().add(panelProcessingOptions);
		panelProcessingOptions.setLayout(null);
		
		chckBxKeepJunk = new JCheckBox("Keep Junk");
		chckBxKeepJunk.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckBxKeepJunk.setBounds(6, 29, 166, 23);
		panelProcessingOptions.add(chckBxKeepJunk);
		
		chckbxCustomFileLocations = new JCheckBox("Custom File Locations");
		chckbxCustomFileLocations.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxCustomFileLocations.setBounds(6, 55, 166, 23);
		panelProcessingOptions.add(chckbxCustomFileLocations);
		chckbxCustomFileLocations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxCustomFileLocations.isSelected() == true) {
					panelFile.setBounds(10,75,476,280);
					btnProcessAndSave.setBounds(339, 366, 147, 23);
					lblPleaseChoose.setText("<html>Please choose a CSV file to be processed. Files will generate where<br>specified.</html>");
				} else if (chckbxCustomFileLocations.isSelected() == false) {
					panelFile.setBounds(10, 75, 476, 161);
					btnProcessAndSave.setBounds(339, 247, 147, 23);
					lblPleaseChoose.setText("<html>Please choose a CSV file to be processed. Files will generate in<br>original file's direcory.</html>");
				}
			}
		});
		
		btnProcessAndSave = new JButton("Process and Save");
		btnProcessAndSave.setEnabled(true);
		btnProcessAndSave.addActionListener(new ActionListener() {
			
			//When Process and Save button is clicked
			public void actionPerformed(ActionEvent e) {
				
				lblStatus.setText(" ");
				//Update filename(s) with JTextField(s)
				loadFilename = textFieldLoadFilename.getText();
				if (chckbxCustomFileLocations.isSelected() == true) {
					saveFilenameRegional = textFieldSaveFilenameRegional.getText();
					saveFilenameNational = textFieldSaveFilenameNational.getText();
				} else if (chckbxCustomFileLocations.isSelected() == false) {
					String timeStamp = new SimpleDateFormat("yyyyMMdd--HH-mm-ss").format(new java.util.Date());
					
					saveFilenameRegional = textFieldLoadFilename.getText();
					saveFilenameRegional = saveFilenameRegional.replace(".csv", "");
					saveFilenameRegional = saveFilenameRegional + " - " + timeStamp + " - Regional" + ".csv" ;
					saveFilenameNational = textFieldLoadFilename.getText();
					saveFilenameNational = saveFilenameNational.replace(".csv", "");
					saveFilenameNational = saveFilenameNational + " - " + timeStamp + " - National" + ".csv";
				}				
								
				//Read file and process priorities
			    if (Business.readCsvFile(loadFilename) == "File is incompatible!") {
			    	lblStatus.setText("incompatible file");
			    } else {
			    	Business.getPriorities();
			    	
			    	//Sort ArrayList and therefore output file based on selected radio button
			    	if (rdbtnPriorityHighest.isSelected()) {
			    		Business.sortByPriorityHighest();
			    	} else if (rdbtnPagesHighest.isSelected()) {
			    		Business.sortByPagesHighest();
			    	}
			    	
					//Write file
					if (chckBxKeepJunk.isSelected()) {
						lblStatus.setText(Business.writeCsvFileRegionalWithJunk(saveFilenameRegional));
						lblStatus.setText(Business.writeCsvFileNationalWithJunk(saveFilenameNational));
						if (chckbxBuildRegAddress.isSelected()) {
				    		try {
								AddressSheet.SheetBuilder(Business.getRegionalList());
								AddressSheet.SheetBuilder(Business.getNationalList());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    	}
						
						Business.clearSessionLists();
					} else {
						lblStatus.setText(Business.writeCsvFileRegional(saveFilenameRegional));
						lblStatus.setText(Business.writeCsvFileNational(saveFilenameNational));
						if (chckbxBuildRegAddress.isSelected()) {
				    		try {
								AddressSheet.SheetBuilder(Business.getRegionalList());
								//AddressSheet.SheetBuilder(Business.getNationalList());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    	}
						Business.clearSessionLists();
					}
			    }				
			}
		});
		btnProcessAndSave.setBounds(339, 247, 147, 23);
		getContentPane().add(btnProcessAndSave);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(73, 320, 256, 14);
		getContentPane().add(lblStatus);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Sort", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(496, 181, 178, 103);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		rdbtnPriorityHighest = new JRadioButton("Priority (Highest to Lowest)");
		rdbtnPriorityHighest.setSelected(true);
		buttonGroupSort.add(rdbtnPriorityHighest);
		rdbtnPriorityHighest.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnPriorityHighest.setBounds(6, 18, 166, 23);
		panel.add(rdbtnPriorityHighest);
		
		rdbtnTimeNewest = new JRadioButton("Time (Newest to Oldest)");
		buttonGroupSort.add(rdbtnTimeNewest);
		rdbtnTimeNewest.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnTimeNewest.setBounds(6, 44, 166, 23);
		panel.add(rdbtnTimeNewest);
		
		rdbtnPagesHighest = new JRadioButton("Pages (Highest to Lowest)");
		buttonGroupSort.add(rdbtnPagesHighest);
		rdbtnPagesHighest.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnPagesHighest.setBounds(6, 70, 166, 23);
		panel.add(rdbtnPagesHighest);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Address List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(496, 295, 178, 95);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		chckbxBuildRegAddress = new JCheckBox("Build Regional Address List");
		chckbxBuildRegAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxBuildRegAddress.setBounds(6, 23, 166, 23);
		panel_1.add(chckbxBuildRegAddress);
		
	}
	
	private JPanel getAddBlacklistPanel() {
		
		JPanel addBlacklistPanel = new JPanel();
		
		JLabel lblAddToBlacklist = new JLabel("Add Item To Blacklist");
		lblAddToBlacklist.setFont(new Font("Tahoma", Font.BOLD, 15));
		addBlacklistPanel.add(lblAddToBlacklist);
		
		textFieldBlacklistName = new JTextField();
		addBlacklistPanel.add(textFieldBlacklistName);
		textFieldBlacklistName.setColumns(10);
		
		
		JComboBox comboBoxField = new JComboBox();
		addBlacklistPanel.add(comboBoxField);
		
		JButton btnAddToBlacklist = new JButton("Add to Blacklist");
		addBlacklistPanel.add(btnAddToBlacklist);
		
		JLabel lblName = new JLabel("Name (exactly as it appears in CSV file):");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		addBlacklistPanel.add(lblName);
		
		JLabel lblField = new JLabel("Field:");
		lblField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		addBlacklistPanel.add(lblField);
		
		return addBlacklistPanel;
	}
	
	public static void updateLoading(int i) {
		lblStatus.setText(Integer.toString(i));
	}
}
