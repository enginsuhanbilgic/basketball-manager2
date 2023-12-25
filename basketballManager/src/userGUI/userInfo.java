package userGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
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
					frame.setSize(850, 540);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 540);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 64));
		panel.setBounds(0, 0, 191, 501);
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{190, 0};
		gbl_panel.rowHeights = new int[]{165, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

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
					
					String filePath = "src//user//user.txt"; // Replace with your file path
			        String usernameToFind = userArr[0]; // Replace with the username to locate
			        String newImageDirectory = selectedFile.getAbsolutePath(); // Replace with the new image directory

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
			                    System.out.println("Could not rename the temporary file.");
			                }
			            } else {
			                System.out.println("Could not delete the original file.");
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
		
		JButton usernameBtn = new JButton("Change username");
		usernameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		
		JButton ageBtn = new JButton("Change age");
		ageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		GridBagConstraints gbc_mailBtn = new GridBagConstraints();
		gbc_mailBtn.gridx = 0;
		gbc_mailBtn.gridy = 12;
		panel.add(mailBtn, gbc_mailBtn);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(190, 0, 644, 501);
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
	}
}