package ui;

import lib.WhitelistHandler;
import ui.ThomasNetUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;

public class AddToWhitelist extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldWhitelist;
	private JButton btnWhitelist;
	private JComboBox comboBoxType;
	private JComboBox comboBoxPriority;

	/**
	 * Create the dialog.
	 */
	public AddToWhitelist() {
		setBounds(100, 100, 450, 280);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		this.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 57, 414, 130);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		textFieldWhitelist = new JTextField();
		textFieldWhitelist.setBounds(10, 93, 299, 20);
		panel.add(textFieldWhitelist);
		textFieldWhitelist.setColumns(10);
		
		btnWhitelist = new JButton("Whitelist");
		btnWhitelist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WhitelistHandler.addToWhitelist(comboBoxType.getSelectedItem().toString(), textFieldWhitelist.getText().toString(), Integer.parseInt(comboBoxPriority.getSelectedItem().toString()));
				textFieldWhitelist.setText("");
				textFieldWhitelist.requestFocusInWindow();
				
				getRootPane().setDefaultButton(btnWhitelist);
			}
		});
		btnWhitelist.setBounds(315, 92, 89, 23);
		panel.add(btnWhitelist);
		
		comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"ISP (Business Name)", "Domain Name", "Location", "Zip Code", "Industry (Not Reccomended)", "Sub Industry (Not Recommended)", "Referrer", "Search Terms"}));
		comboBoxType.setBounds(10, 36, 215, 23);
		panel.add(comboBoxType);
		
		JLabel lblSeslectTheType = new JLabel("Select the type of item to be whitelisted:");
		lblSeslectTheType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSeslectTheType.setBounds(10, 11, 198, 14);
		panel.add(lblSeslectTheType);
		
		JLabel lblTypeTheName = new JLabel("Type the name of the item EXACTLY as it appears in the spreadsheet:");
		lblTypeTheName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTypeTheName.setBounds(10, 70, 394, 14);
		panel.add(lblTypeTheName);
		
		comboBoxPriority = new JComboBox();
		comboBoxPriority.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBoxPriority.setBounds(283, 37, 48, 20);
		panel.add(comboBoxPriority);
		
		JLabel lblPriorityOfItem = new JLabel("Priority of Item:");
		lblPriorityOfItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPriorityOfItem.setBounds(283, 11, 121, 14);
		panel.add(lblPriorityOfItem);
		
		JLabel lblAddItemTo = new JLabel("Add Item To Whitelist");
		lblAddItemTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddItemTo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddItemTo.setBounds(92, 11, 249, 42);
		contentPanel.add(lblAddItemTo);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WhitelistHandler.whitelistWriter(ThomasNetUI.getSaveWhitelist());
				dispose();
			}
		});
		btnDone.setBounds(161, 198, 115, 23);
		contentPanel.add(btnDone);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
