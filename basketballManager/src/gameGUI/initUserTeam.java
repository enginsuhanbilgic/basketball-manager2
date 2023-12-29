package gameGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import user.user;
import userGUI.userInfo;
import team.Team;
import team.TeamInit;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class initUserTeam extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField teamNameField;
	private String imgDir = "C:\\\\Users\\\\EBILGIC20\\\\git\\\\repository2\\\\basketballManager\\\\src\\\\defaultProfileImage.jpg";

	/**
	 * Launch the application.
	 */
	public static void runInitUserTeam(String[] userArr) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initUserTeam frame = new initUserTeam(userArr);
					frame.setSize(300, 400);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JFrame getInitUserTeamFrame() {
		return this;
	}

	/**
	 * Create the frame.
	 */
	public initUserTeam(String[] userArr) {
		setTitle("Configure team info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 284, 361);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel teamNameLbl = new JLabel("Set team name:");
		teamNameLbl.setBounds(40, 8, 82, 16);
		teamNameLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel.add(teamNameLbl);
		
		teamNameField = new JTextField();
		teamNameField.setBounds(127, 5, 116, 22);
		teamNameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel.add(teamNameField);
		teamNameField.setColumns(10);
		
		JButton teamLogoBtn = new JButton("");
		teamLogoBtn.setBackground(new Color(255, 255, 255));
		teamLogoBtn.setBounds(53, 61, 183, 159);
		teamLogoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(fc.getFileSystemView().getParentDirectory(new File("C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\")));
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = fc.showOpenDialog(null);
				if (result==JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
					Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
					teamLogoBtn.setIcon(new ImageIcon(image));
					imgDir = selectedFile.getAbsolutePath();
				}
			}
		});
		
		ImageIcon imageIcon = new ImageIcon(imgDir);
		Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		teamLogoBtn.setIcon(new ImageIcon(image));
		JLabel teamLogoLbl = new JLabel("Set Team Logo");
		teamLogoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		teamLogoLbl.setBounds(71, 35, 129, 14);
		panel.add(teamLogoLbl);
		panel.add(teamLogoBtn);
		
		JButton goToUserInfoBtn = new JButton("goToUserInfo");
		goToUserInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userInfo.userInfoInit(userArr);
				getInitUserTeamFrame().dispose();
			}
		});
		goToUserInfoBtn.setBounds(10, 327, 116, 23);
		panel.add(goToUserInfoBtn);
		
		JButton startDraftingSessionBtn = new JButton("Start Drafting Session");
		startDraftingSessionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Team userTeam = new	Team(teamNameField.getText(), imgDir);
				JOptionPane.showMessageDialog(null, "Succesfully created team");
				TeamInit.teamInit(userTeam);
				draftingGUI.runDraftingGUI(userArr, userTeam);
				getInitUserTeamFrame().dispose();
			}
		});
		startDraftingSessionBtn.setBounds(137, 327, 137, 23);
		panel.add(startDraftingSessionBtn);
	}
}
