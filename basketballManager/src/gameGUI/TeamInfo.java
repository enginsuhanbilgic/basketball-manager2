package gameGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import player.*;
import team.Team;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
/**
 * Class to show team information
 */
public class TeamInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static C Cplayer;
	private static PF PFplayer;
	private static PG PGplayer;
	private static SF SFplayer;
	private static SG SGplayer;
	
	/**
	 * Method first seperates players according to their position
	 * Then starts the frame 
	 * @param userArr Array type, stores user information as string array
	 * @param userTeam Team type, user's team
	 */
	public static void initTeamInfo(String[] userArr, Team userTeam) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for(Player player : userTeam.getPlayers()) {
						if(player instanceof C) {
							Cplayer = (C) player;
						}
						if(player instanceof PF) {
							PFplayer = (PF) player;
						}
						if(player instanceof PG) {
							PGplayer = (PG) player;
						}
						if(player instanceof SF) {
							SFplayer = (SF) player;
						}
						if(player instanceof SG) {
							SGplayer = (SG) player;
						}
					}
					TeamInfo frame = new TeamInfo(userArr, userTeam);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Returns the current frame
	 * @return JFrame
	 */
	private JFrame getTeamInfoFrame() {
		return this;
	}


	/**
	 * 
	 * @param userArr Array type, stores user information as string array
	 * @param userTeam Team type, user's team
	 */
	public TeamInfo(String[] userArr, Team userTeam) {
		setBounds(100, 100, 650, 400);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 634, 361);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JLabel userLbl = new JLabel(userArr[0] + "'s team information");
		userLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		userLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.add(userLbl);
		
		JLabel lblNewLabel = new JLabel("  ");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel);
		
		JLabel teamLbl = new JLabel("Team name: " + userTeam.getTeamName());
		teamLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(teamLbl);
		
		JButton goBackBtn = new JButton("Go back");
		goBackBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		goBackBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		goBackBtn.addActionListener(new ActionListener() {
			/**
			 * Button action to close the team info frame
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				getTeamInfoFrame().dispose();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("  ");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_1);
		
		JButton centerPlayerBtn = new JButton(Cplayer.getName());
		centerPlayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPlayerBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.add(centerPlayerBtn);
		centerPlayerBtn.addActionListener(new ActionListener() {
			/**
			 * Button action to see center player information
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				PlayerInfo.initPlayerInfo(Cplayer);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_2);
		
		JButton powerForwardPlayerBtn = new JButton(PFplayer.getName());
		powerForwardPlayerBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		powerForwardPlayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(powerForwardPlayerBtn);
		powerForwardPlayerBtn.addActionListener(new ActionListener() {
			/**
			 * Button action to see power forward player information
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				PlayerInfo.initPlayerInfo(PFplayer);
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_3);
		
		JButton pointGuardPlayerBtn = new JButton(PGplayer.getName());
		pointGuardPlayerBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		pointGuardPlayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(pointGuardPlayerBtn);
		pointGuardPlayerBtn.addActionListener(new ActionListener() {
			/**
			 * Button action to see point guard player information
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				PlayerInfo.initPlayerInfo(PGplayer);
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel(" ");
		lblNewLabel_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_4);
		
		JButton smallForwardPlayerBtn = new JButton(SFplayer.getName());
		smallForwardPlayerBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		smallForwardPlayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(smallForwardPlayerBtn);
		smallForwardPlayerBtn.addActionListener(new ActionListener() {
			/**
			 * Button action to see small forward player information
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				PlayerInfo.initPlayerInfo(SFplayer);
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel(" ");
		lblNewLabel_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_5);
		
		JButton shootingGuardPlayerBtn = new JButton(SGplayer.getName());
		shootingGuardPlayerBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		shootingGuardPlayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(shootingGuardPlayerBtn);
		shootingGuardPlayerBtn.addActionListener(new ActionListener() {
			/**
			 * Button action to see shooting guard player information
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				PlayerInfo.initPlayerInfo(SGplayer);
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("  ");
		lblNewLabel_6.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(" ");
		lblNewLabel_7.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(" ");
		lblNewLabel_8.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_8);
		panel.add(goBackBtn);
	}

}
