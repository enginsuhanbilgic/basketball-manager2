package gameGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import team.Team;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Matchmaking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean isRunning;
	private ExecutorService executorService;
	private int i;
	private List<Team> teams = team.TeamInit.getTeams();
	private Random randnum = new Random();
	private boolean home;
	private boolean notHome;
	private static SwingWorker<Void, Void> swingWorker;
	private JTextArea scoreBoardArea;
	private JTextArea matchResultsArea;
	
	/**
	 * Launch the application.
	 */
	public static void initMatchmaking(String[] userArr, Team userTeam) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Matchmaking frame = new Matchmaking(userArr, userTeam);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Matchmaking(String[] userArr, Team userTeam) {
		setTitle("Matchmaking");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 64));
		panel.setBounds(0, 0, 744, 411);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		isRunning = false;
		i = 0;
		
		matchResultsArea = new JTextArea();
		GridBagConstraints gbc_matchResultsArea = new GridBagConstraints();
		gbc_matchResultsArea.insets = new Insets(0, 0, 5, 5);
		gbc_matchResultsArea.fill = GridBagConstraints.BOTH;
		gbc_matchResultsArea.gridx = 3;
		gbc_matchResultsArea.gridy = 2;
		panel.add(matchResultsArea, gbc_matchResultsArea);
		
		JButton pauseResumeBtn = new JButton("Resume");
		pauseResumeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isRunning) {
					startMatchmaking();
					pauseResumeBtn.setText("Pause");
				}
				else {
					stopMatchmaking();
					pauseResumeBtn.setText("Resume");
				}
			}
		});
		
		JButton viewTeamBtn = new JButton("View Team");
		viewTeamBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopMatchmaking();
				TeamInfo.initTeamInfo(userArr, userTeam);
				pauseResumeBtn.setText("Resume");
			}
		});
		
		scoreBoardArea = new JTextArea();
		GridBagConstraints gbc_scoreBoardArea = new GridBagConstraints();
		gbc_scoreBoardArea.insets = new Insets(0, 0, 5, 5);
		gbc_scoreBoardArea.fill = GridBagConstraints.BOTH;
		gbc_scoreBoardArea.gridx = 15;
		gbc_scoreBoardArea.gridy = 2;
		panel.add(scoreBoardArea, gbc_scoreBoardArea);
		
		GridBagConstraints gbc_viewTeamBtn = new GridBagConstraints();
		gbc_viewTeamBtn.insets = new Insets(0, 0, 5, 5);
		gbc_viewTeamBtn.gridx = 3;
		gbc_viewTeamBtn.gridy = 11;
		panel.add(viewTeamBtn, gbc_viewTeamBtn);
		
		GridBagConstraints gbc_pauseResumeBtn = new GridBagConstraints();
		gbc_pauseResumeBtn.insets = new Insets(0, 0, 5, 5);
		gbc_pauseResumeBtn.gridx = 9;
		gbc_pauseResumeBtn.gridy = 11;
		panel.add(pauseResumeBtn, gbc_pauseResumeBtn);
		
		JButton playoffBtn = new JButton("Start Playoff");
		playoffBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_playoffBtn = new GridBagConstraints();
		gbc_playoffBtn.insets = new Insets(0, 0, 5, 5);
		gbc_playoffBtn.gridx = 15;
		gbc_playoffBtn.gridy = 11;
		panel.add(playoffBtn, gbc_playoffBtn);
		
	}
	
	private void startMatchmaking() {
		System.out.println("Matchmaking started");
		isRunning = true;
		
		swingWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
            	while(isRunning) {
    				try {
    					double[] scores = new double[16];
    					Team[] winner = new Team[8];
    					Thread.sleep(1000);
    					System.out.println("asd");
    					
    						for(; i<32 && isRunning; i++) {
    							try {
    								Thread.sleep(1000);
    							} catch (InterruptedException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
    							scoreBoardArea.setText("");
    							
    							Collections.shuffle(teams);
    							
    							home = randnum.nextBoolean();
    							notHome = !home;
    							if(home) {
    								scores[0] = teams.get(0).getTotalScore()*1.05;
    								scores[1] = teams.get(1).getTotalScore();
    								if(scores[0] > scores[1]) {
    									teams.get(0).addWins();
    									winner[0] = teams.get(0);
    								}
    								else {
    									teams.get(1).addWins();
    									winner[0] = teams.get(1);
    								}
    							}
    							else {
    								scores[0] = teams.get(0).getTotalScore();
    								scores[1] = teams.get(1).getTotalScore()*1.05;
    								if(scores[0] > scores[1]) {
    									teams.get(0).addWins();
    									winner[0] = teams.get(0);
    								}
    								else {
    									teams.get(1).addWins();
    									winner[0] = teams.get(1);
    								}
    							}
    							
    							home = randnum.nextBoolean();
    							notHome = !home;
    							if(home) {
    								scores[2] = teams.get(2).getTotalScore()*1.05;
    								scores[3] = teams.get(3).getTotalScore();
    								if(scores[2] > scores[3]) {
    									teams.get(2).addWins();
    									winner[1] = teams.get(2);
    								}
    								else {
    									teams.get(3).addWins();
    									winner[1] = teams.get(3);
    								}
    							}
    							else {
    								scores[2] = teams.get(2).getTotalScore()*1.05;
    								scores[3] = teams.get(3).getTotalScore();
    								if(scores[2] > scores[3]) {
    									teams.get(2).addWins();
    									winner[1] = teams.get(2);
    								}
    								else {
    									teams.get(3).addWins();
    									winner[1] = teams.get(3);
    								}
    							}
    							
    							home = randnum.nextBoolean();
    							notHome = !home;
    							if(home) {
    								scores[4] = teams.get(4).getTotalScore()*1.05;
    								scores[5] = teams.get(5).getTotalScore();
    								if(scores[4] > scores[5]) {
    									teams.get(4).addWins();
    									winner[2] = teams.get(4);
    								}
    								else {
    									teams.get(5).addWins();
    									winner[2] = teams.get(5);
    								}
    							}
    							else {
    								scores[4] = teams.get(4).getTotalScore();
    								scores[5] = teams.get(5).getTotalScore()*1.05;
    								if(scores[4] > scores[5]) {
    									teams.get(4).addWins();
    									winner[2] = teams.get(4);
    								}
    								else {
    									teams.get(5).addWins();
    									winner[2] = teams.get(5);
    								}
    							}
    							
    							home = randnum.nextBoolean();
    							notHome = !home;
    							if(home) {
    								scores[6] = teams.get(6).getTotalScore()*1.05;
    								scores[7] = teams.get(7).getTotalScore();
    								if(scores[6] > scores[7]) {
    									teams.get(6).addWins();
    									winner[3] = teams.get(6);
    								}
    								else {
    									teams.get(7).addWins();
    									winner[3] = teams.get(7);
    								}
    							}
    							else {
    								scores[6] = teams.get(6).getTotalScore();
    								scores[7] = teams.get(7).getTotalScore()*1.05;
    								if(scores[6] > scores[7]) {
    									teams.get(6).addWins();
    									winner[3] = teams.get(6);
    								}
    								else {
    									teams.get(7).addWins();
    									winner[3] = teams.get(7);
    								}
    							}
    							
    							home = randnum.nextBoolean();
    							notHome = !home;
    							if(home) {
    								scores[8] = teams.get(8).getTotalScore()*1.05;
    								scores[9] = teams.get(9).getTotalScore();
    								if(scores[8] > scores[9]) {
    									teams.get(8).addWins();
    									winner[4] = teams.get(8);
    								}
    								else {
    									teams.get(9).addWins();
    									winner[4] = teams.get(9);
    								}
    							}
    							else {
    								scores[8] = teams.get(8).getTotalScore();
    								scores[9] = teams.get(9).getTotalScore()*1.05;
    								if(scores[8] > scores[9]) {
    									teams.get(8).addWins();
    									winner[4] = teams.get(8);
    								}
    								else {
    									teams.get(9).addWins();
    									winner[4] = teams.get(9);
    								}
    							}
    							
    							home = randnum.nextBoolean();
    							notHome = !home;
    							if(home) {
    								scores[10] = teams.get(10).getTotalScore()*1.05;
    								scores[11] = teams.get(11).getTotalScore();
    								if(scores[10] > scores[11]) {
    									teams.get(10).addWins();
    									winner[5] = teams.get(10);
    								}
    								else {
    									teams.get(11).addWins();
    									winner[5] = teams.get(11);
    								}
    							}
    							else {
    								scores[10] = teams.get(10).getTotalScore();
    								scores[11] = teams.get(11).getTotalScore()*1.05;
    								if(scores[10] > scores[11]) {
    									teams.get(10).addWins();
    									winner[5] = teams.get(10);
    								}
    								else {
    									teams.get(11).addWins();
    									winner[5] = teams.get(11);
    								}
    							}
    							
    							home = randnum.nextBoolean();
    							notHome = !home;
    							if(home) {
    								scores[12] = teams.get(12).getTotalScore()*1.05;
    								scores[13] = teams.get(13).getTotalScore();
    								if(scores[12] > scores[13]) {
    									teams.get(12).addWins();
    									winner[6] = teams.get(12);
    								}
    								else {
    									teams.get(13).addWins();
    									winner[6] = teams.get(13);
    								}
    							}
    							else {
    								scores[12] = teams.get(12).getTotalScore();
    								scores[13] = teams.get(13).getTotalScore()*1.05;
    								if(scores[12] > scores[13]) {
    									teams.get(12).addWins();
    									winner[6] = teams.get(12);
    								}
    								else {
    									teams.get(13).addWins();
    									winner[6] = teams.get(13);
    								}
    							}
    							
    							home = randnum.nextBoolean();
    							notHome = !home;
    							if(home) {
    								scores[14] = teams.get(14).getTotalScore()*1.05;
    								scores[15] = teams.get(15).getTotalScore();
    								if(scores[14] > scores[15]) {
    									teams.get(14).addWins();
    									winner[7] = teams.get(14);
    								}
    								else {
    									teams.get(15).addWins();
    									winner[7] = teams.get(15);
    								}
    							}
    							else {
    								scores[14] = teams.get(14).getTotalScore();
    								scores[15] = teams.get(15).getTotalScore()*1.05;
    								if(scores[14] > scores[15]) {
    									teams.get(14).addWins();
    									winner[7] = teams.get(14);
    								}
    								else {
    									teams.get(15).addWins();
    									winner[7] = teams.get(15);
    								}
    							}
    							for(int j = 0; j < teams.size(); j = j + 2) {
        							Team firstTeam = teams.get(j);
        							Team secondTeam = teams.get(j + 1);

        							scoreBoardArea.append(String.format("%s: %.1f points vs %s: %.1f points, Winner: %s\n", firstTeam.getTeamName(), scores[j], secondTeam.getTeamName(), scores[j+1], winner[j/2].getTeamName()));

        						}
    						}

    					
    				} catch (InterruptedException e) {
    					System.out.println("An error occured");
    				}
    			}

            	return null;
            }
            @Override
            public void done() {
            	}
            };
            swingWorker.execute();

	}
	
	private void stopMatchmaking() {
		isRunning = false;
		System.out.println("Matchmaking stopped");
	}

}
