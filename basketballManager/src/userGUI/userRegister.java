package userGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import startProgram.startProgram;
import user.user;

public class userRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame loginFrame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	protected boolean found;
	private JTextField mailField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField ageField;

	public static void userRegisterInit() {
		EventQueue.invokeLater(new Runnable() {
			/**
			 * Method for initializing register page
			 */
			public void run() {
				try {
					userRegister frame = new userRegister();
					frame.setSize(810, 486);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Method that returns the current frame
	 * @return JFrame
	 */
	public JFrame getRegisterFrame() {
		return this;
	}
	
	public userRegister() {
		setTitle("Register");
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
		JLabel imgLbl = new JLabel("");
		imgLbl.setHorizontalAlignment(SwingConstants.CENTER);
		imgLbl.setIcon(new ImageIcon("src\\game-icon.jpg"));
		iconpanel.add(imgLbl);
		
		//Log in panel
		JPanel login = new JPanel();
		login.setBackground(new Color(255, 128, 64));
		login.setBounds(305, 2, 489, 444);
		getContentPane().add(login);
		
		//I used Grid Bag Layout because it is convenient for elements to be placed in organized manner
		GridBagLayout gbl_login = new GridBagLayout();
		gbl_login.columnWidths = new int[]{31, 111, 90, 183, 145, 0};
		gbl_login.rowHeights = new int[]{45, 16, 0, 0, 0, 0, 0, 0, 0, 29, 0, 17, 32, 0, 0, 0, 0, 0, 21, 0};
		gbl_login.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_login.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		login.setLayout(gbl_login);
		
		//Log in title
		JLabel register = new JLabel("Register");
		register.setForeground(new Color(255, 255, 255));
		register.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		register.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_register = new GridBagConstraints();
		gbc_register.fill = GridBagConstraints.BOTH;
		gbc_register.insets = new Insets(0, 0, 5, 0);
		gbc_register.gridwidth = 5;
		gbc_register.gridx = 0;
		gbc_register.gridy = 0;
		login.add(register, gbc_register);
		
		JLabel usernameLbl = new JLabel(" Username");
		usernameLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		usernameLbl.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_usernameLbl = new GridBagConstraints();
		gbc_usernameLbl.gridwidth = 3;
		gbc_usernameLbl.anchor = GridBagConstraints.NORTH;
		gbc_usernameLbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLbl.gridx = 1;
		gbc_usernameLbl.gridy = 2;
		login.add(usernameLbl, gbc_usernameLbl);
		
		//Text field for entering email to log in
		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		usernameField.setForeground(new Color(0, 0, 0));
		usernameField.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.gridwidth = 3;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.anchor = GridBagConstraints.SOUTH;
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 3;
		login.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		JLabel mailLbl = new JLabel(" E-mail");
		mailLbl.setForeground(Color.WHITE);
		mailLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_mailLbl = new GridBagConstraints();
		gbc_mailLbl.gridwidth = 3;
		gbc_mailLbl.anchor = GridBagConstraints.WEST;
		gbc_mailLbl.insets = new Insets(0, 0, 5, 5);
		gbc_mailLbl.gridx = 1;
		gbc_mailLbl.gridy = 4;
		login.add(mailLbl, gbc_mailLbl);
		
		mailField = new JTextField();
		mailField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_mailField = new GridBagConstraints();
		gbc_mailField.gridwidth = 3;
		gbc_mailField.insets = new Insets(0, 0, 5, 5);
		gbc_mailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_mailField.gridx = 1;
		gbc_mailField.gridy = 5;
		login.add(mailField, gbc_mailField);
		mailField.setColumns(10);
		
		JLabel passwordLbl = new JLabel(" Password");
		passwordLbl.setForeground(Color.WHITE);
		passwordLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.gridwidth = 3;
		gbc_passwordLbl.anchor = GridBagConstraints.WEST;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 1;
		gbc_passwordLbl.gridy = 6;
		login.add(passwordLbl, gbc_passwordLbl);
		
		//Password field for entering password to log in
		//Password field works the same with text field but
		//it doesn't show the text input
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		passwordField.setForeground(new Color(0, 0, 0));
		passwordField.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 7;
		login.add(passwordField, gbc_passwordField);
		
		JLabel nameLbl = new JLabel(" Name");
		nameLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		nameLbl.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.gridwidth = 2;
		gbc_nameLbl.anchor = GridBagConstraints.WEST;
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.gridx = 1;
		gbc_nameLbl.gridy = 8;
		login.add(nameLbl, gbc_nameLbl);
		
		JLabel surnameLbl = new JLabel(" Surname");
		surnameLbl.setForeground(new Color(255, 255, 255));
		surnameLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_surnameLbl = new GridBagConstraints();
		gbc_surnameLbl.gridwidth = 3;
		gbc_surnameLbl.anchor = GridBagConstraints.WEST;
		gbc_surnameLbl.insets = new Insets(0, 0, 5, 0);
		gbc_surnameLbl.gridx = 3;
		gbc_surnameLbl.gridy = 8;
		login.add(surnameLbl, gbc_surnameLbl);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 2;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 9;
		login.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_surnameField = new GridBagConstraints();
		gbc_surnameField.insets = new Insets(0, 0, 5, 5);
		gbc_surnameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_surnameField.gridx = 3;
		gbc_surnameField.gridy = 9;
		login.add(surnameField, gbc_surnameField);
		surnameField.setColumns(10);
		
		JLabel ageLbl = new JLabel(" Age");
		ageLbl.setForeground(new Color(255, 255, 255));
		ageLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_ageLbl = new GridBagConstraints();
		gbc_ageLbl.gridwidth = 3;
		gbc_ageLbl.anchor = GridBagConstraints.WEST;
		gbc_ageLbl.insets = new Insets(0, 0, 5, 5);
		gbc_ageLbl.gridx = 1;
		gbc_ageLbl.gridy = 10;
		login.add(ageLbl, gbc_ageLbl);
		
		ageField = new JTextField();
		ageField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_ageField = new GridBagConstraints();
		gbc_ageField.insets = new Insets(0, 0, 5, 5);
		gbc_ageField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ageField.gridx = 1;
		gbc_ageField.gridy = 11;
		login.add(ageField, gbc_ageField);
		ageField.setColumns(10);
		
		//Register button
		JButton registerButton = new JButton("Register");
		//Checks if information are valid and registers user
		registerButton.addActionListener(new ActionListener() {
			/**
			 * Register button action 
			 * It checks if the information fits the requirements
			 * and then creates a User object and saves it to the
			 * user.txt file
			 */
			public void actionPerformed(ActionEvent e) {
				
				String mailfield = mailField.getText();
				char[] p = passwordField.getPassword();
				String passwordfield = new String(p);
				String usernamefield = usernameField.getText();
				String namefield = nameField.getText();
				String surnamefield = surnameField.getText();
				int agefield = -999;
				try {agefield = Integer.parseInt(ageField.getText());}
				catch(NumberFormatException err) {
					JOptionPane.showMessageDialog(null, "Age cannot be non-integer");
				}
				boolean register = true;
				
				if(passwordfield.length()<8) {
					register = false;
					JOptionPane.showMessageDialog(null, "Password should be at least 8 characters");
				}
				if(namefield.length()<3 || surnamefield.length()<3) {
					register = false;
					JOptionPane.showMessageDialog(null, "Name and surname should be at least 3 characters");
				}
				if (!namefield.matches("[\\p{L}&&[^\\s-]]+") && !surnamefield.matches("[\\p{L}&&[^\\s-]]+")) {
				    register = false;
				    JOptionPane.showMessageDialog(null, "Name should only contain letters");
				}
				if(!usernamefield.matches("[a-zA-Z0-9]+")) {
					register = false;
					JOptionPane.showMessageDialog(null, "Username may only contain letters and numbers");
				}
				if(agefield<12 && agefield!=-999) {
					register = false;
					JOptionPane.showMessageDialog(null, "Age can not be less than 12");
				}
				if(!mailfield.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
					register = false;
					JOptionPane.showMessageDialog(null, "Email address is not in correct format");
				}
				
				boolean isUsernameUnique = true;
			    boolean isEmailUnique = true;
				
			    try (BufferedReader br = new BufferedReader(new FileReader("src//user//user.txt"))) {
		            String line;
		            while ((line = br.readLine()) != null) {
		                String[] userInfo = line.split(",");
		                String existingUsername = userInfo[0];
		                String existingEmail = userInfo[5];

		                if (usernamefield.equals(existingUsername)) {
		                    isUsernameUnique = false;
		                    JOptionPane.showMessageDialog(null, "This username is already occupied");
		                    break;
		                }

		                if (mailfield.equals(existingEmail)) {
		                    isEmailUnique = false;
		                    JOptionPane.showMessageDialog(null, "This email is already occupied");
		                    break;
		                }
		            }
		        } catch (IOException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error reading user information.");
		        }
			    
				if(register && isEmailUnique && isUsernameUnique) {
					String imgDir = "C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\defaultProfileImage.jpg";
					String userInfoStr = String.format("%s,%s,%s,%s,%d,%s,%s",usernamefield, passwordfield, namefield, surnamefield, agefield, mailfield, imgDir);
					String[] userArr = userInfoStr.split(",");
					try(BufferedWriter bw = new BufferedWriter(new FileWriter("src//user//user.txt", true))) {
						bw.write(userInfoStr);
						bw.newLine();
						bw.close();
						JOptionPane.showMessageDialog(null, "User is registered succesfully");
						user User = new user(usernamefield, passwordfield, namefield, surnamefield, agefield, mailfield, imgDir);
						userInfo.userInfoInit(userArr);
						getRegisterFrame().dispose();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					}
				}
			});
		
		JLabel loginPageLabel = new JLabel(" Already have an account?");
		loginPageLabel.setForeground(new Color(255, 255, 255));
		loginPageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		loginPageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_loginPageLabel = new GridBagConstraints();
		gbc_loginPageLabel.insets = new Insets(0, 0, 5, 0);
		gbc_loginPageLabel.gridx = 4;
		gbc_loginPageLabel.gridy = 16;
		login.add(loginPageLabel, gbc_loginPageLabel);
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.gridwidth = 3;
		gbc_registerButton.anchor = GridBagConstraints.WEST;
		gbc_registerButton.insets = new Insets(0, 0, 5, 5);
		gbc_registerButton.gridx = 1;
		gbc_registerButton.gridy = 17;
		login.add(registerButton, gbc_registerButton);
		
		JButton loginPageButton = new JButton("Go to login page");
		loginPageButton.addActionListener(new ActionListener() {
			/**
			 * Button action to go to login page
			 * It calls the main function
			 * and then disposes the current frame
			 */
			public void actionPerformed(ActionEvent e) {
				startProgram.main(null);
				getRegisterFrame().dispose();
			}
		});
		loginPageButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_loginPageButton = new GridBagConstraints();
		gbc_loginPageButton.insets = new Insets(0, 0, 5, 0);
		gbc_loginPageButton.gridx = 4;
		gbc_loginPageButton.gridy = 17;
		login.add(loginPageButton, gbc_loginPageButton);
	}

}
