package gameGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import team.Team;
import team.TeamInit;
import player.*;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
/**
 * Class for drafting session of the game
 */
public class draftingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static List<Team> selectingOrder = TeamInit.getTeams();
	private static List<Team> selectingOrderReversed = new ArrayList<>();
	private static String teamOrder = "";
	private static int tour = 0;
	private JComboBox<DisplayItem<Player>> comboBox;
	private Player selectedPlayer;
	
	/**
	 * Launch the application
	 * @param userArr Array type, stores user information
	 * @param userTeam Team type, user's team object
	 */
	public static void runDraftingGUI(String[] userArr, Team userTeam) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					draftingGUI frame = new draftingGUI(userArr, userTeam);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Static block that shuffles the teams to randomize the selecting order
	 * It also adds the teams to a reversed order list to use it in stages
	 * It also appends the team names in an ordered form to a string
	 * This string is shown to the user
	 */
	static {
		Collections.shuffle(selectingOrder);
		
		for(Team team : selectingOrder) {
			selectingOrderReversed.add(team);
			teamOrder += team.getTeamName() + "\n";
		}
		Collections.reverse(selectingOrderReversed);	
	}
	
	/**
	 * 
	 * @return JFrame, return the current frame
	 */
	protected JFrame getDraftingGUIFrame() {
		return this;
	}
	
	/**
	 * Create the frame.
	 */
	public draftingGUI(String[] userArr, Team userTeam) {
		setTitle("Drafting Session");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 64));
		panel.setBounds(0, 0, 884, 528);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[][][][grow][][][][][grow][grow][grow][][][][38.00]", "[][30.00][][21.00,grow][][grow][grow][grow][][][][][]"));
		
		JButton teamLogoBtn = new JButton("");
		teamLogoBtn.setIcon(new ImageIcon("src\\defaultProfileImage.jpg"));
		JLabel selectingOrderLbl = new JLabel("Selecting order");
		selectingOrderLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel.add(selectingOrderLbl, "cell 7 1 3 1,alignx center,aligny bottom");
		
		JTextArea selectingOrderTextArea = new JTextArea();
		selectingOrderTextArea.setEditable(false);
		selectingOrderTextArea.setText(teamOrder); // Use setText instead of append
		selectingOrderTextArea.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(selectingOrderTextArea, "cell 7 2 3 3,grow");
		
		JLabel selectingTeamLbl = new JLabel("");
		selectingTeamLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectingTeamLbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panel.add(selectingTeamLbl, "cell 3 3,alignx center,aligny top");
		
		JTextArea draftedPlayerTextArea = new JTextArea();
		draftedPlayerTextArea.setEditable(false);
		panel.add(draftedPlayerTextArea, "cell 2 5 3 8,grow");
		
		comboBox = new JComboBox<>();
		comboBox.setSelectedIndex(-1);
		comboBox.setSelectedItem(draftedPlayerTextArea);
		
		JButton viewTeamBtn = new JButton("View Team");
		viewTeamBtn.setEnabled(false);
		JButton startTournament = new JButton("StartTournament");
		startTournament.setEnabled(false);
		JButton nextStepBtn = new JButton("Next Step");
		JButton saveChoiceBtn = new JButton("saveChoice");
		saveChoiceBtn.setEnabled(false);
		saveChoiceBtn.addActionListener(new ActionListener() {
			/**
			 * Button action for saving the user's player choice in drafting session
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				DisplayItem<Player> selectedDisplayItem = (DisplayItem<Player>) comboBox.getSelectedItem();
				
				selectedPlayer = selectedDisplayItem.getObject();
				userTeam.addPlayer(selectedPlayer);
				draftedPlayerTextArea.append("Player: " + selectedDisplayItem.getObject().getName() + "\n Points: " + selectedDisplayItem.getObject().getPoints() + "\n Rebounds: " + selectedDisplayItem.getObject().getRebounds() + 
						"\n Assists: " + selectedDisplayItem.getObject().getAssists() + "\n Steals: " + selectedDisplayItem.getObject().getSteals() + "\n Blocks: " + selectedDisplayItem.getObject().getBlocks() + "\n");
				selectingTeamLbl.setText(userTeam.getTeamName());
				ImageIcon imageIcon = new ImageIcon(userTeam.getImgDir());
				Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				teamLogoBtn.setIcon(new ImageIcon(image));
				saveChoiceBtn.setEnabled(false);
				nextStepBtn.setEnabled(true);
				comboBox.setSelectedIndex(-1);
				tour++;
				if(tour==80) {
					startTournament.setEnabled(true);
					nextStepBtn.setEnabled(false);
					viewTeamBtn.setEnabled(true);
				}
			}
		});
		
		
		nextStepBtn.addActionListener(new ActionListener() {
			/**
			 * Button action to progress in game's tours
			 * Each time the next button is pressed, next team in the line will draft player
			 * There are five tours for five different positions in the game
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				Team teamInLine;
				
				SecureRandom randnum = new SecureRandom();
				if(tour == 80) {
					JOptionPane.showMessageDialog(null, "Drafting session is done");
					nextStepBtn.setEnabled(false);
					startTournament.setEnabled(true);
					viewTeamBtn.setEnabled(true);
				}
				if(tour<80) {
					nextStepBtn.setEnabled(true);
					int position = tour / 16;
						switch (position) {
							case 0:
								draftedPlayerTextArea.setText("");
								System.out.println(tour);
								teamInLine = selectingOrder.get(tour%16);
								C Cplayer;
								if(teamInLine.equals(userTeam)) {
									comboBox.removeAllItems();
									for(Player CCplayer : PlayerInit.getCPlayers()) {
										comboBox.addItem(new DisplayItem<>(CCplayer, CCplayer.toString()));
									}
									nextStepBtn.setEnabled(false);
									saveChoiceBtn.setEnabled(true);
								}
								else {
									Cplayer = PlayerInit.getCPlayers().get(randnum.nextInt(0, PlayerInit.getCPlayers().size()));
									teamInLine.addPlayer(Cplayer);
									Cplayer.draftPlayer();
									PlayerInit.getCPlayers().remove(Cplayer);
									PlayerInit.getPlayers().remove(Cplayer);
									selectingTeamLbl.setText(teamInLine.getTeamName());
									ImageIcon imageIcon = new ImageIcon(teamInLine.getImgDir());
									Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
									teamLogoBtn.setIcon(new ImageIcon(image));
									draftedPlayerTextArea.append("Center player: " + Cplayer.getName() + "\n Points: " + Cplayer.getPoints() + "\n Rebounds: " + Cplayer.getRebounds() + 
											"\n Assists: " + Cplayer.getAssists() + "\n Steals: " + Cplayer.getSteals() + "\n Blocks: " + Cplayer.getBlocks() + "\n");
									tour++;
								}
								break;
							case 1:
								draftedPlayerTextArea.setText("");
								System.out.println(tour);
								teamInLine = selectingOrderReversed.get(tour%16);
								PF PFplayer;
								if(teamInLine.equals(userTeam)) {
									comboBox.removeAllItems();
									for(Player PFFplayer : PlayerInit.getPFPlayers()) {
										comboBox.addItem(new DisplayItem<>(PFFplayer, PFFplayer.toString()));
									}
									nextStepBtn.setEnabled(false);
									saveChoiceBtn.setEnabled(true);
								}
								else {
									PFplayer = PlayerInit.getPFPlayers().get(randnum.nextInt(0, PlayerInit.getPFPlayers().size()));
									teamInLine.addPlayer(PFplayer);
									PFplayer.draftPlayer();
									PlayerInit.getPFPlayers().remove(PFplayer);
									PlayerInit.getPlayers().remove(PFplayer);
									selectingTeamLbl.setText(teamInLine.getTeamName());
									ImageIcon imageIcon = new ImageIcon(teamInLine.getImgDir());
									Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
									teamLogoBtn.setIcon(new ImageIcon(image));
									draftedPlayerTextArea.append("Power Forward player: " + PFplayer.getName() + "\n Points: " + PFplayer.getPoints() + "\n Rebounds: " + PFplayer.getRebounds() + 
											"\n Assists: " + PFplayer.getAssists() + "\n Steals: " + PFplayer.getSteals() + "\n Blocks: " + PFplayer.getBlocks() + "\n");
									tour++;
								}
								break;
							case 2:
								draftedPlayerTextArea.setText("");
								System.out.println(tour);
								teamInLine = selectingOrder.get(tour%16);
								PG PGplayer;
								if(teamInLine.equals(userTeam)) {
									comboBox.removeAllItems();
									for(Player PGGplayer : PlayerInit.getPGPlayers()) {
										comboBox.addItem(new DisplayItem<>(PGGplayer, PGGplayer.toString()));
									}
									nextStepBtn.setEnabled(false);
									saveChoiceBtn.setEnabled(true);
								}
								else {
									PGplayer = PlayerInit.getPGPlayers().get(randnum.nextInt(0, PlayerInit.getPGPlayers().size()));
									teamInLine.addPlayer(PGplayer);
									PGplayer.draftPlayer();
									PlayerInit.getPGPlayers().remove(PGplayer);
									PlayerInit.getPlayers().remove(PGplayer);
									selectingTeamLbl.setText(teamInLine.getTeamName());
									ImageIcon imageIcon = new ImageIcon(teamInLine.getImgDir());
									Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
									teamLogoBtn.setIcon(new ImageIcon(image));
									draftedPlayerTextArea.append("Point Guard player: " + PGplayer.getName() + "\n Points: " + PGplayer.getPoints() + "\n Rebounds: " + PGplayer.getRebounds() + 
											"\n Assists: " + PGplayer.getAssists() + "\n Steals: " + PGplayer.getSteals() + "\n Blocks: " + PGplayer.getBlocks() + "\n");
									tour++;
								}
								break;
							case 3:
								draftedPlayerTextArea.setText("");
								System.out.println(tour);
								teamInLine = selectingOrderReversed.get(tour%16);
								SF SFplayer;
								if(teamInLine.equals(userTeam)) {
									comboBox.removeAllItems();
									for(Player SFFplayer : PlayerInit.getSFPlayers()) {
										comboBox.addItem(new DisplayItem<>(SFFplayer, SFFplayer.toString()));
									}
									nextStepBtn.setEnabled(false);
									saveChoiceBtn.setEnabled(true);
								}
								else {
									SFplayer = PlayerInit.getSFPlayers().get(randnum.nextInt(0, PlayerInit.getSFPlayers().size()));
									teamInLine.addPlayer(SFplayer);
									SFplayer.draftPlayer();
									PlayerInit.getSFPlayers().remove(SFplayer);
									PlayerInit.getPlayers().remove(SFplayer);
									selectingTeamLbl.setText(teamInLine.getTeamName());
									ImageIcon imageIcon = new ImageIcon(teamInLine.getImgDir());
									Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
									teamLogoBtn.setIcon(new ImageIcon(image));
									draftedPlayerTextArea.append("Small Forward player: " + SFplayer.getName() + "\n Points: " + SFplayer.getPoints() + "\n Rebounds: " + SFplayer.getRebounds() + 
											"\n Assists: " + SFplayer.getAssists() + "\n Steals: " + SFplayer.getSteals() + "\n Blocks: " + SFplayer.getBlocks() + "\n");
									tour++;
								}
								break;
							case 4:
								draftedPlayerTextArea.setText("");
								System.out.println(tour);
								teamInLine = selectingOrder.get(tour%16);
								SG SGplayer;
								if(teamInLine.equals(userTeam)) {
									comboBox.removeAllItems();
									for(Player SGGplayer : PlayerInit.getSGPlayers()) {
										comboBox.addItem(new DisplayItem<>(SGGplayer, SGGplayer.toString()));
									}
									nextStepBtn.setEnabled(false);
									saveChoiceBtn.setEnabled(true);
								}
								else {
									SGplayer = PlayerInit.getSGPlayers().get(randnum.nextInt(0, PlayerInit.getSGPlayers().size()));
									teamInLine.addPlayer(SGplayer);
									SGplayer.draftPlayer();
									PlayerInit.getSGPlayers().remove(SGplayer);
									PlayerInit.getPlayers().remove(SGplayer);
									selectingTeamLbl.setText(teamInLine.getTeamName());
									ImageIcon imageIcon = new ImageIcon(teamInLine.getImgDir());
									Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
									teamLogoBtn.setIcon(new ImageIcon(image));
									draftedPlayerTextArea.append("Shooting Guard player: " + SGplayer.getName() + "\n Points: " + SGplayer.getPoints() + "\n Rebounds: " + SGplayer.getRebounds() + 
											"\n Assists: " + SGplayer.getAssists() + "\n Steals: " + SGplayer.getSteals() + "\n Blocks: " + SGplayer.getBlocks() + "\n");
									tour++;
								}
								break;
						}
				}
			}
		});
		

		panel.add(comboBox, "cell 7 7 3 1,growx");
		panel.add(saveChoiceBtn, "cell 8 8,alignx center");
		panel.add(nextStepBtn, "cell 7 11");
		panel.add(teamLogoBtn, "cell 3 2,alignx center,aligny center");
		
		
		startTournament.addActionListener(new ActionListener() {
			/**
			 * Button action to start the tournament after the drafting ends
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				for (Team team : TeamInit.getTeams()) {
					System.out.println(team.getTeamName());
					System.out.println(team.getPlayers().size());
					for (Player player : team.getPlayers()) {
						System.out.println(player.getName() + " Position: " + player.getPosition());
					}
				}
				try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\team\\team.txt"))) {
					for(Team team : team.TeamInit.getTeams()) {
						bw.write(team.getTeamName());
						bw.newLine();
						for(Player player : team.getPlayers()) {
							bw.write(player.toString());
							bw.newLine();
						}
						bw.newLine();
					}
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Matchmaking.initMatchmaking(userArr, userTeam);
				getDraftingGUIFrame().dispose();
			}
		});
		
		
		viewTeamBtn.addActionListener(new ActionListener() {
			/**
			 * Button action to show team info frame
			 */
			public void actionPerformed(ActionEvent e) {
				TeamInfo.initTeamInfo(userArr, userTeam);
			}
		});
		panel.add(viewTeamBtn, "cell 8 11,alignx center");
		panel.add(startTournament, "cell 9 11 2 1,alignx center,aligny center");
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel, "cell 14 11");
		
	}
	
	/**
	 * Generic class for displaying items
	 * In my case T is passed as Player
	 * This static class is used in combo box
	 * Using this, I display the toString information of players in combo box
	 * When the user selects a specific player in the combo box,
	 * using this generic class I can get the according player object
	 * @param <T>
	 */
	private static class DisplayItem<T>{
		private T object;
		private String displayString;
		
		/**
		 * Constructor for DisplayItem
		 * @param object
		 * @param displayString
		 */
		public DisplayItem(T object, String displayString) {
			this.object = object;
			this.displayString = displayString;
		}
		
		/**
		 * Returns the object 
		 * @return T
		 */
		public T getObject() {
			return object;
		}
		
		/**
		 * Returns the toString form specified in the player class
		 * @return String
		 */
		@Override
		public String toString() {
			return displayString;
		}
	}
	
}
