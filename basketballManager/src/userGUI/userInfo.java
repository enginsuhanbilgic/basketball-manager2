package userGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import user.user;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;

public class userInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField ageField;
	private JTextField mailField;

	public static void main(String[] userArr) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userInfo frame = new userInfo(userArr);
					frame.setSize(640, 540);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getUserInfoFrame() {
		return this;
	}

	public userInfo(String[] userAr) {
		final String[] userArr = new String[7];
		if(userAr.length==0) {
			userArr[0] = "null";
			userArr[1] = "null";
			userArr[2] = "null";
			userArr[3] = "null";
			userArr[4] = "0";
			userArr[5] = "null";
			userArr[6] = "C:\\\\Users\\\\EBILGIC20\\\\git\\\\repository2\\\\basketballManager\\\\src\\\\defaultProfileImage.jpg";
			String username = "null";
			String password = "null";
			String name = "null";
			String surname = "null";
			int age = 0;
			String email = "null";
			String imgDir = "C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\defaultProfileImage.jpg";

		}

		if(userAr.length>0) {
			userArr[0] = userAr[0];
			userArr[1] = userAr[1];
			userArr[2] = userAr[2];
			userArr[3] = userAr[3];
			userArr[4] = userAr[4];
			userArr[5] = userAr[5];
			userArr[6] = userAr[6];
			String username = userArr[0];
			String password = userArr[1];
			String name = userArr[2];
			String surname = userArr[3];
			System.out.println(userArr[4]);
			int age = Integer.parseInt(userArr[4]);
			String email = userArr[5];
			String imgDir = userArr[6];
		}

		setTitle("User Information");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 541);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 64));
		panel.setBounds(0, 0, 191, 501);
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{190, 0};
		gbl_panel.rowHeights = new int[]{165, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		//Action listener for changing the profile image and saving it
		//First we have our current image directory. When the user
		//clicks the button, it opens the directory chooser and
		//user can select an image. That image will take the place 
		//of the old image in the txt file and in the user array
		JButton changeImageBtn = new JButton("");
		changeImageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = fc.showOpenDialog(null);
				if (result==JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
					Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
					changeImageBtn.setIcon(new ImageIcon(image));
					userArr[6] = selectedFile.getAbsolutePath();
					
					String filePath = "src//user//user.txt"; 
			        String usernameToFind = userArr[0]; 
			        String newImageDirectory = selectedFile.getAbsolutePath(); 

					try {
			            File inputFile = new File(filePath);
			            File tempFile = new File("src//user//temp.txt");
			            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			            String currentLine;
			            List<String> updatedLines = new ArrayList<>();

			            while ((currentLine = reader.readLine()) != null) {
			                String[] userInfo = currentLine.split(",");
			                if (userInfo.length >= 7 && userInfo[0].equals(usernameToFind)) {
			                    // Found the user by username, update the image directory
			                    userInfo[6] = newImageDirectory;
			                    JOptionPane.showMessageDialog(null, "Profile image changed");
			                }
			                updatedLines.add(String.join(",", userInfo));
			            }

			            // Writing the updated lines to the temporary file
			            for (String line : updatedLines) {
			                writer.write(line);
			                writer.newLine();
			            }

			            writer.close();
			            reader.close();

			            // Rename the temporary file to the original file
			            if (inputFile.delete()) {
			                if (!tempFile.renameTo(inputFile)) {
			                	JOptionPane.showMessageDialog(null, "Could not rename the temporary file");
			                }
			            } else {
			            	JOptionPane.showMessageDialog(null, "Could not delete the original file");
			            }
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }
				}
			}
		});
		
		ImageIcon imageIcon = new ImageIcon(userArr[6]);
		Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		changeImageBtn.setIcon(new ImageIcon(image));
		GridBagConstraints gbc_changeImageBtn = new GridBagConstraints();
		gbc_changeImageBtn.insets = new Insets(0, 0, 5, 0);
		gbc_changeImageBtn.gridx = 0;
		gbc_changeImageBtn.gridy = 0;
		panel.add(changeImageBtn, gbc_changeImageBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(190, 0, 434, 501);
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{429, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 106, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		
		JLabel userInformationLbl = new JLabel("User Information");
		userInformationLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		GridBagConstraints gbc_userInformationLbl = new GridBagConstraints();
		gbc_userInformationLbl.insets = new Insets(0, 0, 5, 0);
		gbc_userInformationLbl.gridx = 0;
		gbc_userInformationLbl.gridy = 0;
		panel_1.add(userInformationLbl, gbc_userInformationLbl);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel usernameShowLbl = new JLabel(String.format("Username:    %s", userArr[0]));
		usernameShowLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		GridBagConstraints gbc_usernameShowLbl = new GridBagConstraints();
		gbc_usernameShowLbl.insets = new Insets(0, 0, 5, 0);
		gbc_usernameShowLbl.gridx = 0;
		gbc_usernameShowLbl.gridy = 5;
		panel_1.add(usernameShowLbl, gbc_usernameShowLbl);
		
		JLabel ageShowLbl = new JLabel(String.format("Age:    %s", userArr[4]));
		ageShowLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		GridBagConstraints gbc_ageShowLbl = new GridBagConstraints();
		gbc_ageShowLbl.insets = new Insets(0, 0, 5, 0);
		gbc_ageShowLbl.gridx = 0;
		gbc_ageShowLbl.gridy = 7;
		panel_1.add(ageShowLbl, gbc_ageShowLbl);
		
		JLabel mailShowLbl = new JLabel(String.format("Mail:    %s", userArr[5]));
		mailShowLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		GridBagConstraints gbc_mailShowLbl = new GridBagConstraints();
		gbc_mailShowLbl.insets = new Insets(0, 0, 5, 0);
		gbc_mailShowLbl.gridx = 0;
		gbc_mailShowLbl.gridy = 9;
		panel_1.add(mailShowLbl, gbc_mailShowLbl);
		
		JLabel nameShowLbl = new JLabel(String.format("Name:    %s", userArr[2]));
		nameShowLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		GridBagConstraints gbc_nameShowLbl = new GridBagConstraints();
		gbc_nameShowLbl.insets = new Insets(0, 0, 5, 0);
		gbc_nameShowLbl.gridx = 0;
		gbc_nameShowLbl.gridy = 11;
		panel_1.add(nameShowLbl, gbc_nameShowLbl);
		
		JLabel surnameShowLbl = new JLabel(String.format("Surname:    %s", userArr[3]));
		surnameShowLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		GridBagConstraints gbc_surnameShowLbl = new GridBagConstraints();
		gbc_surnameShowLbl.insets = new Insets(0, 0, 5, 0);
		gbc_surnameShowLbl.gridx = 0;
		gbc_surnameShowLbl.gridy = 13;
		panel_1.add(surnameShowLbl, gbc_surnameShowLbl);
		
		JLabel usernameLbl = new JLabel(" Change username");
		usernameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_usernameLbl = new GridBagConstraints();
		gbc_usernameLbl.anchor = GridBagConstraints.WEST;
		gbc_usernameLbl.insets = new Insets(0, 0, 5, 0);
		gbc_usernameLbl.gridx = 0;
		gbc_usernameLbl.gridy = 1;
		panel.add(usernameLbl, gbc_usernameLbl);
		
		usernameField = new JTextField();
		usernameField.setBackground(new Color(255, 255, 255));
		usernameField.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.gridx = 0;
		gbc_usernameField.gridy = 2;
		panel.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		//Action listener for changing the username
		//Works similar to the image directory change
		//But there is no file selection or image changing
		JButton usernameBtn = new JButton("Change username");
		usernameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = "src//user//user.txt"; 
		        String usernameToFind = userArr[0]; 
		        String newUsername = usernameField.getText(); 

				try {
		            File inputFile = new File(filePath);
		            File tempFile = new File("src//user//temp.txt");
		            BufferedReader readerFirst = new BufferedReader(new FileReader(inputFile));
		            BufferedReader readerFinal = new BufferedReader(new FileReader(inputFile));
		            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		            String currentLine;
		            List<String> updatedLines = new ArrayList<>();
		            int cnt = 0;
		            while ((currentLine = readerFirst.readLine()) != null) {
		                String[] userInfo = currentLine.split(",");
		                if (userInfo.length >= 7 && userInfo[0].equals(newUsername)) {
		                	cnt++;
		                }
		                updatedLines.add(String.join(",", userInfo));
		            }
		            
		            if(cnt==0) {
		            	updatedLines.clear();
		            	while ((currentLine = readerFinal.readLine()) != null) {
			                String[] userInfo = currentLine.split(",");
			                if (userInfo.length >= 7 && userInfo[0].equals(usernameToFind)) {
			                	userInfo[0] = newUsername;
			                	JOptionPane.showMessageDialog(null, "Username changed");
			                	usernameShowLbl.setText(String.format("Username:    %s", newUsername));
			                }
			                updatedLines.add(String.join(",", userInfo));
			            }
		            }
		            else {
		            	JOptionPane.showMessageDialog(null, "Username is occupied");
		            }
		            // Writing the updated lines to the temporary file
		            for (String line : updatedLines) {
		                writer.write(line);
		                writer.newLine();
		            }

		            writer.close();
		            readerFirst.close();
		            readerFinal.close();

		            // Rename the temporary file to the original file
		            if (inputFile.delete()) {
		                if (!tempFile.renameTo(inputFile)) {
		                	JOptionPane.showMessageDialog(null, "Could not rename the temporary file");
		                }
		            } else {
		            	JOptionPane.showMessageDialog(null, "Could not delete the original file");
		            }
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		GridBagConstraints gbc_usernameBtn = new GridBagConstraints();
		gbc_usernameBtn.insets = new Insets(0, 0, 5, 0);
		gbc_usernameBtn.gridx = 0;
		gbc_usernameBtn.gridy = 3;
		panel.add(usernameBtn, gbc_usernameBtn);
		
		JLabel passwordLbl = new JLabel(" Change password");
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.anchor = GridBagConstraints.WEST;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 0);
		gbc_passwordLbl.gridx = 0;
		gbc_passwordLbl.gridy = 4;
		panel.add(passwordLbl, gbc_passwordLbl);
		
		passwordField = new JTextField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 5;
		panel.add(passwordField, gbc_passwordField);
		passwordField.setColumns(10);
		
		JButton passwordBtn = new JButton("Change password");
		passwordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = "src//user//user.txt"; 
		        String usernameToFind = userArr[0]; 
		        String newPassword = passwordField.getText(); 

				try {
		            File inputFile = new File(filePath);
		            File tempFile = new File("src//user//temp.txt");
		            BufferedReader readerFirst = new BufferedReader(new FileReader(inputFile));
		            BufferedReader readerFinal = new BufferedReader(new FileReader(inputFile));
		            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		            String currentLine;
		            List<String> updatedLines = new ArrayList<>();

	            	while ((currentLine = readerFinal.readLine()) != null) {
		                String[] userInfo = currentLine.split(",");
		                if (userInfo.length >= 7 && userInfo[0].equals(usernameToFind)) {
		                	if(newPassword.length()>8) {
		                		userInfo[1] = newPassword;
		                		JOptionPane.showMessageDialog(null, "Password changed");
		                	}
		                	else {
		                		JOptionPane.showMessageDialog(null, "Password cannot be shorter than 8 characters");
		                	}
		                }
		                updatedLines.add(String.join(",", userInfo));
		            }
		           
		            // Writing the updated lines to the temporary file
		            for (String line : updatedLines) {
		                writer.write(line);
		                writer.newLine();
		            }

		            writer.close();
		            readerFirst.close();
		            readerFinal.close();

		            // Rename the temporary file to the original file
		            if (inputFile.delete()) {
		                if (!tempFile.renameTo(inputFile)) {
		                	JOptionPane.showMessageDialog(null, "Could not rename the temporary file");
		                }
		            } else {
		            	JOptionPane.showMessageDialog(null, "Could not delete the original file");
		            }
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		GridBagConstraints gbc_passwordBtn = new GridBagConstraints();
		gbc_passwordBtn.insets = new Insets(0, 0, 5, 0);
		gbc_passwordBtn.gridx = 0;
		gbc_passwordBtn.gridy = 6;
		panel.add(passwordBtn, gbc_passwordBtn);
		
		JLabel ageLbl = new JLabel(" Change age");
		GridBagConstraints gbc_ageLbl = new GridBagConstraints();
		gbc_ageLbl.anchor = GridBagConstraints.WEST;
		gbc_ageLbl.insets = new Insets(0, 0, 5, 0);
		gbc_ageLbl.gridx = 0;
		gbc_ageLbl.gridy = 7;
		panel.add(ageLbl, gbc_ageLbl);
		
		ageField = new JTextField();
		GridBagConstraints gbc_ageField = new GridBagConstraints();
		gbc_ageField.insets = new Insets(0, 0, 5, 0);
		gbc_ageField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ageField.gridx = 0;
		gbc_ageField.gridy = 8;
		panel.add(ageField, gbc_ageField);
		ageField.setColumns(10);
		
		JButton ageBtn = new JButton("Change age ");
		ageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = "src//user//user.txt"; 
		        String usernameToFind = userArr[0]; 
		        String newAge = ageField.getText(); 

				try {
		            File inputFile = new File(filePath);
		            File tempFile = new File("src//user//temp.txt");
		            BufferedReader readerFirst = new BufferedReader(new FileReader(inputFile));
		            BufferedReader readerFinal = new BufferedReader(new FileReader(inputFile));
		            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		            String currentLine;
		            List<String> updatedLines = new ArrayList<>();

	            	while ((currentLine = readerFinal.readLine()) != null) {
		                String[] userInfo = currentLine.split(",");
		                if (userInfo.length >= 7 && userInfo[0].equals(usernameToFind)) {
		                	if(Integer.parseInt(newAge)>12) {
		                		userInfo[4] = newAge;
		                		JOptionPane.showMessageDialog(null, "Age changed");
		                		ageShowLbl.setText(String.format("Age:    %s", newAge));
		                	}
		                	else {
		                		JOptionPane.showMessageDialog(null, "Age cannot be smaller than 12");
		                	}
		                }
		                updatedLines.add(String.join(",", userInfo));
		            }
		           
		            // Writing the updated lines to the temporary file
		            for (String line : updatedLines) {
		                writer.write(line);
		                writer.newLine();
		            }

		            writer.close();
		            readerFirst.close();
		            readerFinal.close();

		            // Rename the temporary file to the original file
		            if (inputFile.delete()) {
		                if (!tempFile.renameTo(inputFile)) {
		                	JOptionPane.showMessageDialog(null, "Could not rename the temporary file");
		                }
		            } else {
		            	JOptionPane.showMessageDialog(null, "Could not delete the original file");
		            }
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		GridBagConstraints gbc_ageBtn = new GridBagConstraints();
		gbc_ageBtn.insets = new Insets(0, 0, 5, 0);
		gbc_ageBtn.gridx = 0;
		gbc_ageBtn.gridy = 9;
		panel.add(ageBtn, gbc_ageBtn);
		
		JLabel mailLbl = new JLabel(" Change mail");
		GridBagConstraints gbc_mailLbl = new GridBagConstraints();
		gbc_mailLbl.anchor = GridBagConstraints.WEST;
		gbc_mailLbl.insets = new Insets(0, 0, 5, 0);
		gbc_mailLbl.gridx = 0;
		gbc_mailLbl.gridy = 10;
		panel.add(mailLbl, gbc_mailLbl);
		
		mailField = new JTextField();
		GridBagConstraints gbc_mailField = new GridBagConstraints();
		gbc_mailField.insets = new Insets(0, 0, 5, 0);
		gbc_mailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_mailField.gridx = 0;
		gbc_mailField.gridy = 11;
		panel.add(mailField, gbc_mailField);
		mailField.setColumns(10);
		
		JButton mailBtn = new JButton("Change mail");
		mailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = "src//user//user.txt"; 
		        String mailToFind = userArr[5]; 
		        String newMail = mailField.getText(); 

				try {
		            File inputFile = new File(filePath);
		            File tempFile = new File("src//user//temp.txt");
		            BufferedReader readerFirst = new BufferedReader(new FileReader(inputFile));
		            BufferedReader readerFinal = new BufferedReader(new FileReader(inputFile));
		            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		            String currentLine;
		            List<String> updatedLines = new ArrayList<>();
		            int cnt = 0;
		            while ((currentLine = readerFirst.readLine()) != null) {
		                String[] userInfo = currentLine.split(",");
		                if (userInfo.length >= 7 && userInfo[5].equals(newMail)) {
		                	cnt++;
		                }
		                updatedLines.add(String.join(",", userInfo));
		            }
		            
		            if(cnt==0) {
		            	updatedLines.clear();
		            	while ((currentLine = readerFinal.readLine()) != null) {
			                String[] userInfo = currentLine.split(",");
			                if (userInfo.length >= 7 && userInfo[5].equals(mailToFind)) {
			                	userInfo[5] = newMail;
			                	JOptionPane.showMessageDialog(null, "E-mail changed");
			                	mailShowLbl.setText(String.format("E-mail:    %s", newMail));
			                }
			                updatedLines.add(String.join(",", userInfo));
			            }
		            }
		            else {
		            	JOptionPane.showMessageDialog(null, "This e-mail is occupied");
		            }
		            // Writing the updated lines to the temporary file
		            for (String line : updatedLines) {
		                writer.write(line);
		                writer.newLine();
		            }

		            writer.close();
		            readerFirst.close();
		            readerFinal.close();

		            // Rename the temporary file to the original file
		            if (inputFile.delete()) {
		                if (!tempFile.renameTo(inputFile)) {
		                	JOptionPane.showMessageDialog(null, "Could not rename the temporary file");
		                }
		            } else {
		            	JOptionPane.showMessageDialog(null, "Could not delete the original file");
		            }
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		GridBagConstraints gbc_mailBtn = new GridBagConstraints();
		gbc_mailBtn.insets = new Insets(0, 0, 5, 0);
		gbc_mailBtn.gridx = 0;
		gbc_mailBtn.gridy = 12;
		panel.add(mailBtn, gbc_mailBtn);
		
		JButton goToLoginBtn = new JButton("Go to login page");
		goToLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startProgram.main(null);
				getUserInfoFrame().dispose();
			}
		});
		GridBagConstraints gbc_goToLoginBtn = new GridBagConstraints();
		gbc_goToLoginBtn.gridx = 0;
		gbc_goToLoginBtn.gridy = 14;
		panel.add(goToLoginBtn, gbc_goToLoginBtn);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(621, 0, 212, 501);
		getContentPane().add(panel_2);
	}
}