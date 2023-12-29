package userGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import user.user;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class userSignup extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField email;
	private JPasswordField passwordField;
	protected boolean found;
	
	public JFrame getLoginFrame() {
		return this;
	}
	
	public userSignup() {
		setTitle("Log in");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//Panel that shows the game logo on the left side of the screen
		JPanel iconpanel = new JPanel();
		iconpanel.setBackground(new Color(255, 255, 255));
		iconpanel.setForeground(new Color(0, 0, 0));
		iconpanel.setBounds(0, 0, 305, 446);
		getContentPane().add(iconpanel);
		
		//Image label
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon("src\\game-icon.jpg"));
		iconpanel.add(lblNewLabel_2);
		
		//Log in panel
		JPanel login = new JPanel();
		login.setBackground(new Color(255, 128, 64));
		login.setBounds(305, 2, 489, 446);
		getContentPane().add(login);
		
		//I used Grid Bag Layout because it is convenient for elements to be placed in organized manner
		GridBagLayout gbl_login = new GridBagLayout();
		gbl_login.columnWidths = new int[]{31, 183, 145, 0};
		gbl_login.rowHeights = new int[]{45, 16, 0, 0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 21, 0};
		gbl_login.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_login.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		login.setLayout(gbl_login);
		
		//Log in title
		JLabel lblNewLabel = new JLabel("Log in");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		login.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" Username/E-Mail");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		login.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		//Text field for entering email to log in
		email = new JTextField();
		email.setHorizontalAlignment(SwingConstants.LEFT);
		email.setFont(new Font("SansSerif", Font.PLAIN, 11));
		email.setForeground(new Color(0, 0, 0));
		email.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_txtemail = new GridBagConstraints();
		gbc_txtemail.insets = new Insets(0, 0, 5, 5);
		gbc_txtemail.anchor = GridBagConstraints.SOUTH;
		gbc_txtemail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtemail.gridx = 1;
		gbc_txtemail.gridy = 3;
		login.add(email, gbc_txtemail);
		email.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel(" Password");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 4;
		login.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		//Password field for entering password to log in
		//Password field works the same with text field but
		//it doesn't show the text input
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(0, 0, 0));
		passwordField.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		login.add(passwordField, gbc_passwordField);
		
		//Log in button
		JButton btnNewButton = new JButton("Log in");
		//Checks if the email/username and password is valid and sends it to user class
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mailField = email.getText();
				char[] p = passwordField.getPassword();
				String passwordfield = new String(p);
				String[] userArr = null;
				
				try(BufferedReader br = new BufferedReader(new FileReader("src//user//user.txt"))){
					String line;
					while((line = br.readLine()) != null) {
						String[] userr = line.split(",");
						
						String usernameLogged = userr[0];
						String passwordLogged = userr[1];
						String userEmailLogged = userr[5];
						
						if((mailField.equals(userEmailLogged) || mailField.equals(usernameLogged)) && passwordfield.equals(passwordLogged)) {
							found = true;
							userArr = userr;
							break;
						}
			
					}
				} catch (FileNotFoundException e1) {
					found = false;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				if(found) {
					JOptionPane.showMessageDialog(null, "Login Successful!");
					getLoginFrame().dispose();
					userInfo.userInfoInit(userArr);
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid username/email or password. Please try again.");
				}
				
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		login.add(btnNewButton, gbc_btnNewButton);
		
		JLabel registerPageLbl = new JLabel("Don't have an account?");
		registerPageLbl.setForeground(new Color(255, 255, 255));
		registerPageLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_registerPageLbl = new GridBagConstraints();
		gbc_registerPageLbl.insets = new Insets(0, 0, 5, 0);
		gbc_registerPageLbl.gridx = 2;
		gbc_registerPageLbl.gridy = 12;
		login.add(registerPageLbl, gbc_registerPageLbl);
		
		JButton registerPageButton = new JButton("Go to register page");
		registerPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userRegister.userRegisterInit();
				getLoginFrame().dispose();
			}
		});
		registerPageButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_registerPageButton = new GridBagConstraints();
		gbc_registerPageButton.insets = new Insets(0, 0, 5, 0);
		gbc_registerPageButton.gridx = 2;
		gbc_registerPageButton.gridy = 13;
		login.add(registerPageButton, gbc_registerPageButton);
	}
}