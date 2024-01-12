package gameGUI;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import team.Team;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
/**
 * Class to conduct the playoff session
 */
public class Playoff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static SwingWorker<Void, Void> swingWorker;
	private boolean isRunning;
	private JLabel teamOneTwoLbl;
	private JLabel teamThreeFourLbl;
	private JLabel teamFiveSixLbl;
	private JLabel teamSevenEightLbl;
	private JLabel finalistOneLbl;
	private JLabel finalistTwoLbl;
	private int i;
	private Random randbool = new Random();
	private Team winnerOneTwo;
	private Team winnerThreeFour;
	private Team winnerFiveSix;
	private Team winnerSevenEight;
	private Team finalistOne;
	private Team finalistTwo;
	private JButton pauseResumeBtn;
	private String result = "";
	private JLabel championLbl;
	
	/**
	 * Initialize the frame
	 * @param playoffTeams List type, stores list of teams
	 */
	public static void initPlayoff(List<Team> playoffTeams, String[] userArr, Team userTeam) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
					Playoff frame = new Playoff(playoffTeams, userArr, userTeam);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * @param playoffTeams List type, stores list of teams
	 */
	public Playoff(List<Team> playoffTeams, String[] userArr, Team userTeam) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().setLayout(new BorderLayout());
		
		isRunning = false;
		i = 0;
		
        JPanel backgroundPanel = new JPanel() {
        	/**
        	 * Method to draw the tournament tree to the background of the JPanel
        	 * Image resolution is different than the frame size
        	 * This method is used to set the image resolution equal to frame size
        	 * @param g Graphics type, used to draw the image
        	 */
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("src\\logos\\playoff.jpg"));
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();

                    double imageAspect = (double) image.getWidth() / image.getHeight();
                    double panelAspect = (double) panelWidth / panelHeight;

                    int x = 0;
                    int y = 0;
                    int width;
                    int height;

                    if (imageAspect > panelAspect) {
                        width = panelWidth;
                        height = (int) (width / imageAspect);
                        y = (panelHeight - height) / 2;
                    } else {
                        height = panelHeight;
                        width = (int) (height * imageAspect);
                        x = (panelWidth - width) / 2;
                    }

                    g.drawImage(image, x, y, width, height, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundPanel.setBackground(new Color(255, 255, 255));

        backgroundPanel.setPreferredSize(new Dimension(900, 500)); 
		
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        backgroundPanel.setLayout(null);
        
        JLabel teamOneLbl = new JLabel(playoffTeams.get(0).getTeamName());
        teamOneLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamOneLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamOneLbl.setBounds(49, 64, 113, 14);
        backgroundPanel.add(teamOneLbl);
        
        JLabel teamTwoLbl = new JLabel(playoffTeams.get(1).getTeamName());
        teamTwoLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamTwoLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamTwoLbl.setBounds(49, 169, 113, 14);
        backgroundPanel.add(teamTwoLbl);
        
        JLabel teamThreeLbl = new JLabel(playoffTeams.get(2).getTeamName());
        teamThreeLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamThreeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamThreeLbl.setBounds(49, 276, 113, 14);
        backgroundPanel.add(teamThreeLbl);
        
        JLabel teamFourLbl = new JLabel(playoffTeams.get(3).getTeamName());
        teamFourLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamFourLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamFourLbl.setBounds(49, 382, 113, 14);
        backgroundPanel.add(teamFourLbl);
        
        teamOneTwoLbl = new JLabel("");
        teamOneTwoLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamOneTwoLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamOneTwoLbl.setBounds(218, 115, 113, 14);
        backgroundPanel.add(teamOneTwoLbl);
        
        teamThreeFourLbl = new JLabel("");
        teamThreeFourLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamThreeFourLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamThreeFourLbl.setBounds(218, 325, 113, 14);
        backgroundPanel.add(teamThreeFourLbl);
        
        finalistTwoLbl = new JLabel("");
        finalistTwoLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        finalistTwoLbl.setHorizontalAlignment(SwingConstants.CENTER);
        finalistTwoLbl.setBounds(386, 169, 113, 14);
        backgroundPanel.add(finalistTwoLbl);
        
        finalistOneLbl = new JLabel("");
        finalistOneLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        finalistOneLbl.setHorizontalAlignment(SwingConstants.CENTER);
        finalistOneLbl.setBounds(386, 276, 113, 14);
        backgroundPanel.add(finalistOneLbl);
        
        teamFiveSixLbl = new JLabel("");
        teamFiveSixLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamFiveSixLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamFiveSixLbl.setBounds(539, 115, 126, 14);
        backgroundPanel.add(teamFiveSixLbl);
        
        teamSevenEightLbl = new JLabel("");
        teamSevenEightLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamSevenEightLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamSevenEightLbl.setBounds(539, 325, 126, 14);
        backgroundPanel.add(teamSevenEightLbl);
        
        JLabel teamFiveLbl = new JLabel(playoffTeams.get(4).getTeamName());
        teamFiveLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamFiveLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamFiveLbl.setBounds(707, 64, 131, 14);
        backgroundPanel.add(teamFiveLbl);
        
        JLabel teamSixLbl = new JLabel(playoffTeams.get(5).getTeamName());
        teamSixLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamSixLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamSixLbl.setBounds(707, 169, 131, 14);
        backgroundPanel.add(teamSixLbl);
        
        JLabel teamSevenLbl = new JLabel(playoffTeams.get(6).getTeamName());
        teamSevenLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamSevenLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamSevenLbl.setBounds(707, 276, 131, 14);
        backgroundPanel.add(teamSevenLbl);
        
        JLabel teamEightLbl = new JLabel(playoffTeams.get(7).getTeamName());
        teamEightLbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
        teamEightLbl.setHorizontalAlignment(SwingConstants.CENTER);
        teamEightLbl.setBounds(707, 382, 131, 14);
        backgroundPanel.add(teamEightLbl);
        
        JButton pauseResumeBtn = new JButton("Resume");
        pauseResumeBtn.setBounds(471, 427, 89, 23);
        backgroundPanel.add(pauseResumeBtn);
        
        JButton viewTeamBtn = new JButton("View team");
        viewTeamBtn.setBounds(334, 427, 107, 23);
        backgroundPanel.add(viewTeamBtn);
        
        championLbl = new JLabel("");
        championLbl.setBounds(386, 221, 113, 14);
        backgroundPanel.add(championLbl);
        
        pauseResumeBtn.addActionListener(new ActionListener() {
        	/**
        	 * Button action to start and stop the matchmaking
        	 * @param e
        	 */
			public void actionPerformed(ActionEvent e) {
				if(i==7) {
					pauseResumeBtn.setText("Resume");
					isRunning = false;
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\gameGUI\\playoffResults.txt"))) {
						bw.write(result);
						bw.newLine();
						bw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(!isRunning) {
					startMatchmaking(playoffTeams);
					pauseResumeBtn.setText("Pause");
				}
				else {
					stopMatchmaking();
					pauseResumeBtn.setText("Resume");
				}
			}
		});
        
        viewTeamBtn.addActionListener(new ActionListener() {
			/**
			 * Button action to view team
			 * It stops the matchmaking and opens the view team frame
			 */
			public void actionPerformed(ActionEvent e) {
				stopMatchmaking();
				TeamInfo.initTeamInfo(userArr, userTeam);
				pauseResumeBtn.setText("Resume");
			}
		});
		
	}
	
	/**
	 * Similar to Matchmaking class, this method uses SwingWorker
	 * to run the simulation in a seperate thread
	 * In each if-else block it simulates the match, then writes the winner to the next label
	 * Finally, after the final match, it displays the winner as a seperate message dialog
	 * @param playoffTeams List type, stores list of teams
	 */
	private void startMatchmaking(List<Team> playoffTeams) {
		System.out.println("Matchmaking started");
		isRunning = true;
		
		swingWorker = new SwingWorker<Void, Void>() {
			/**
             * Swing Worker creates doInBackground method
             * and runs it another thread
             * @throws Exception
             */
            @Override
            protected Void doInBackground() throws Exception {
            	
            	if(i==7) {
					pauseResumeBtn.setText("Resume");
					isRunning = false;
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\gameGUI\\playoffResults.txt"))) {
						bw.write(result);
						bw.newLine();
						bw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
            	
            	while(isRunning) {
    				
					System.out.println("asd");
					
						for(; i < 7 && isRunning; i++) {
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							if(i==0) {
								boolean home = randbool.nextBoolean();
								if(home) {
									double score1 = playoffTeams.get(0).getTotalScore()*1.05;
									double score2 = playoffTeams.get(1).getTotalScore();
									if(score1 > score2) {
										teamOneTwoLbl.setText(playoffTeams.get(0).getTeamName());
										winnerOneTwo = playoffTeams.get(0);
										result = String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(0).getTeamName(), score1, playoffTeams.get(1).getTeamName(), score2, winnerOneTwo.getTeamName());
									}
									else {
										teamOneTwoLbl.setText(playoffTeams.get(1).getTeamName());
										winnerOneTwo = playoffTeams.get(1);
										result = String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(0).getTeamName(), score1, playoffTeams.get(1).getTeamName(), score2, winnerOneTwo.getTeamName());
									}
									
								}
								else {
									double score1 = playoffTeams.get(0).getTotalScore();
									double score2 = playoffTeams.get(1).getTotalScore()*1.05;
									if(score1 > score2) {
										teamOneTwoLbl.setText(playoffTeams.get(0).getTeamName());
										winnerOneTwo = playoffTeams.get(0);
										result = String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(0).getTeamName(), score1, playoffTeams.get(1).getTeamName(), score2, winnerOneTwo.getTeamName());
									}
									else {
										teamOneTwoLbl.setText(playoffTeams.get(1).getTeamName());
										winnerOneTwo = playoffTeams.get(1);
										result = String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(0).getTeamName(), score1, playoffTeams.get(1).getTeamName(), score2, winnerOneTwo.getTeamName());
									}
								}
							}
							else if(i==1) {
								boolean home = randbool.nextBoolean();
								if(home) {
									double score1 = playoffTeams.get(2).getTotalScore()*1.05;
									double score2 = playoffTeams.get(3).getTotalScore();
									if(score1 > score2) {
										teamThreeFourLbl.setText(playoffTeams.get(2).getTeamName());
										winnerThreeFour = playoffTeams.get(2);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(2).getTeamName(), score1, playoffTeams.get(3).getTeamName(), score2, winnerThreeFour.getTeamName());
									}
									else {
										teamThreeFourLbl.setText(playoffTeams.get(3).getTeamName());
										winnerThreeFour = playoffTeams.get(3);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(2).getTeamName(), score1, playoffTeams.get(3).getTeamName(), score2, winnerThreeFour.getTeamName());
									}
								}
								else {
									double score1 = playoffTeams.get(2).getTotalScore();
									double score2 = playoffTeams.get(3).getTotalScore()*1.05;
									if(score1 > score2) {
										teamThreeFourLbl.setText(playoffTeams.get(2).getTeamName());
										winnerThreeFour = playoffTeams.get(2);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(2).getTeamName(), score1, playoffTeams.get(3).getTeamName(), score2, winnerThreeFour.getTeamName());
									}
									else {
										teamThreeFourLbl.setText(playoffTeams.get(3).getTeamName());
										winnerThreeFour = playoffTeams.get(3);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(2).getTeamName(), score1, playoffTeams.get(3).getTeamName(), score2, winnerThreeFour.getTeamName());
									}
								}
							}
							else if(i==2) {
								boolean home = randbool.nextBoolean();
								if(home) {
									double score1 = playoffTeams.get(4).getTotalScore()*1.05;
									double score2 = playoffTeams.get(5).getTotalScore();
									if(score1 > score2) {
										teamFiveSixLbl.setText(playoffTeams.get(4).getTeamName());
										winnerFiveSix = playoffTeams.get(4);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(4).getTeamName(), score1, playoffTeams.get(5).getTeamName(), score2, winnerFiveSix.getTeamName());
									}
									else {
										teamFiveSixLbl.setText(playoffTeams.get(5).getTeamName());
										winnerFiveSix = playoffTeams.get(5);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(4).getTeamName(), score1, playoffTeams.get(5).getTeamName(), score2, winnerFiveSix.getTeamName());
									}
								}
								else {
									double score1 = playoffTeams.get(4).getTotalScore();
									double score2 = playoffTeams.get(5).getTotalScore()*1.05;
									if(score1 > score2) {
										teamFiveSixLbl.setText(playoffTeams.get(4).getTeamName());
										winnerFiveSix = playoffTeams.get(4);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(4).getTeamName(), score1, playoffTeams.get(5).getTeamName(), score2, winnerFiveSix.getTeamName());
									}
									else {
										teamFiveSixLbl.setText(playoffTeams.get(5).getTeamName());
										winnerFiveSix = playoffTeams.get(5);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(4).getTeamName(), score1, playoffTeams.get(5).getTeamName(), score2, winnerFiveSix.getTeamName());
									}
								}
							}
							else if(i==3) {
								boolean home = randbool.nextBoolean();
								if(home) {
									double score1 = playoffTeams.get(6).getTotalScore()*1.05;
									double score2 = playoffTeams.get(7).getTotalScore();
									if(score1 > score2) {
										teamSevenEightLbl.setText(playoffTeams.get(6).getTeamName());
										winnerSevenEight = playoffTeams.get(6);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(6).getTeamName(), score1, playoffTeams.get(7).getTeamName(), score2, winnerSevenEight.getTeamName());
									}
									else {
										teamSevenEightLbl.setText(playoffTeams.get(7).getTeamName());
										winnerSevenEight = playoffTeams.get(7);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(6).getTeamName(), score1, playoffTeams.get(7).getTeamName(), score2, winnerSevenEight.getTeamName());
									}
								}
								else {
									double score1 = playoffTeams.get(6).getTotalScore();
									double score2 = playoffTeams.get(7).getTotalScore()*1.05;
									if(score1 > score2) {
										teamSevenEightLbl.setText(playoffTeams.get(6).getTeamName());
										winnerSevenEight = playoffTeams.get(6);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(6).getTeamName(), score1, playoffTeams.get(7).getTeamName(), score2, winnerSevenEight.getTeamName());
									}
									else {
										teamSevenEightLbl.setText(playoffTeams.get(7).getTeamName());
										winnerSevenEight = playoffTeams.get(7);
										result += String.format("Quarter final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", playoffTeams.get(6).getTeamName(), score1, playoffTeams.get(7).getTeamName(), score2, winnerSevenEight.getTeamName());
									}
								}
							}
							else if(i==4) {
								boolean home = randbool.nextBoolean();
								if(home) {
									double score1 = winnerOneTwo.getTotalScore()*1.05;
									double score2 = winnerThreeFour.getTotalScore();
									if(score1 > score2) {
										finalistOneLbl.setText(winnerOneTwo.getTeamName());
										finalistOne = winnerOneTwo;
										result += String.format("Semi final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", winnerOneTwo.getTeamName(), score1, winnerThreeFour.getTeamName(), score2, finalistOne.getTeamName());
									}
									else {
										finalistOneLbl.setText(winnerThreeFour.getTeamName());
										finalistOne = winnerThreeFour;
										result += String.format("Semi final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", winnerOneTwo.getTeamName(), score1, winnerThreeFour.getTeamName(), score2, finalistOne.getTeamName());
									}
								}
								else {
									double score1 = winnerOneTwo.getTotalScore();
									double score2 = winnerThreeFour.getTotalScore()*1.05;
									if(score1 > score2) {
										finalistOneLbl.setText(winnerOneTwo.getTeamName());
										finalistOne = winnerOneTwo;
										result += String.format("Semi final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", winnerOneTwo.getTeamName(), score1, winnerThreeFour.getTeamName(), score2, finalistOne.getTeamName());
									}
									else {
										finalistOneLbl.setText(winnerThreeFour.getTeamName());
										finalistOne = winnerThreeFour;
										result += String.format("Semi final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", winnerOneTwo.getTeamName(), score1, winnerThreeFour.getTeamName(), score2, finalistOne.getTeamName());
									}
								}
							}
							else if(i==5) {
								boolean home = randbool.nextBoolean();
								if(home) {
									double score1 = winnerFiveSix.getTotalScore()*1.05;
									double score2 = winnerSevenEight.getTotalScore();
									if(score1 > score2) {
										finalistTwoLbl.setText(winnerFiveSix.getTeamName());
										finalistTwo = winnerFiveSix;
										result += String.format("Semi final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", winnerFiveSix.getTeamName(), score1, winnerSevenEight.getTeamName(), score2, finalistTwo.getTeamName());
									}
									else {
										finalistTwoLbl.setText(winnerSevenEight.getTeamName());
										finalistTwo = winnerSevenEight;
										result += String.format("Semi final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", winnerFiveSix.getTeamName(), score1, winnerSevenEight.getTeamName(), score2, finalistTwo.getTeamName());
									}
								}
								else {
									double score1 = winnerFiveSix.getTotalScore();
									double score2 = winnerSevenEight.getTotalScore()*1.05;
									if(score1 > score2) {
										finalistTwoLbl.setText(winnerFiveSix.getTeamName());
										finalistTwo = winnerFiveSix;
										result += String.format("Semi final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", winnerFiveSix.getTeamName(), score1, winnerSevenEight.getTeamName(), score2, finalistTwo.getTeamName());
									}
									else {
										finalistTwoLbl.setText(winnerSevenEight.getTeamName());
										finalistTwo = winnerSevenEight;
										result += String.format("Semi final, %s with %.1f points vs %s with %.1f points, Winner: %s\n", winnerFiveSix.getTeamName(), score1, winnerSevenEight.getTeamName(), score2, finalistTwo.getTeamName());
									}
								}
							}
							else if(i==6) {
								boolean home = randbool.nextBoolean();
								if(home) {
									double score1 = finalistOne.getTotalScore()*1.05;
									double score2 = finalistTwo.getTotalScore();
									if(score1 > score2) {
										result += String.format("Final match, %s with %.1f points vs %s with %.1f points, Winner: %s\n", finalistOne.getTeamName(), score1, finalistTwo.getTeamName(), score2, finalistOne.getTeamName());
										JButton teamLogoBtn = new JButton();
										ImageIcon imageIcon = new ImageIcon(finalistOne.getImgDir());
										Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
										teamLogoBtn.setIcon(new ImageIcon(image));
										JPanel panel = new JPanel(new BorderLayout());
										JLabel label = new JLabel("Winner team: " + finalistOne.getTeamName() + " with " + finalistTwo.getWins() + " wins.");
										panel.add(teamLogoBtn, BorderLayout.CENTER);
										panel.add(label, BorderLayout.SOUTH);
										JOptionPane.showMessageDialog(null, panel, "Winner is: " + finalistOne.getTeamName(), JOptionPane.PLAIN_MESSAGE);
										championLbl.setText(finalistOne.getTeamName());									}
									else {
										result += String.format("Final match, %s with %.1f points vs %s with %.1f points, Winner: %s\n", finalistOne.getTeamName(), score1, finalistTwo.getTeamName(), score2, finalistTwo.getTeamName());
										JButton teamLogoBtn = new JButton();
										ImageIcon imageIcon = new ImageIcon(finalistTwo.getImgDir());
										Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
										teamLogoBtn.setIcon(new ImageIcon(image));
										JPanel panel = new JPanel(new BorderLayout());
										JLabel label = new JLabel("Winner team: " + finalistTwo.getTeamName() + " with " + finalistTwo.getWins() + " wins.");
										panel.add(teamLogoBtn, BorderLayout.CENTER);
										panel.add(label, BorderLayout.SOUTH);
										JOptionPane.showMessageDialog(null, panel, "Winner is: " + finalistOne.getTeamName(), JOptionPane.PLAIN_MESSAGE);
										championLbl.setText(finalistTwo.getTeamName());		
									}
								}
								else {
									double score1 = finalistOne.getTotalScore();
									double score2 = finalistTwo.getTotalScore()*1.05;
									if(score1 > score2) {
										result += String.format("Final match, %s with %.1f points vs %s with %.1f points, Winner: %s\n", finalistOne.getTeamName(), score1, finalistTwo.getTeamName(), score2, finalistOne.getTeamName());
										JButton teamLogoBtn = new JButton();
										ImageIcon imageIcon = new ImageIcon(finalistOne.getImgDir());
										Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
										teamLogoBtn.setIcon(new ImageIcon(image));
										JLabel label = new JLabel("Winner team: " + finalistOne.getTeamName() + " with " + finalistTwo.getWins() + " wins.");
										JPanel panel = new JPanel(new BorderLayout());
										panel.add(teamLogoBtn, BorderLayout.CENTER);
										panel.add(label, BorderLayout.SOUTH);
										JOptionPane.showMessageDialog(null, panel, "Winner is: " + finalistOne.getTeamName(), JOptionPane.PLAIN_MESSAGE);
										championLbl.setText(finalistOne.getTeamName());		
									}
									else {
										result += String.format("Final match, %s with %.1f points vs %s with %.1f points, Winner: %s\n", finalistOne.getTeamName(), score1, finalistTwo.getTeamName(), score2, finalistTwo.getTeamName());
										JButton teamLogoBtn = new JButton();
										ImageIcon imageIcon = new ImageIcon(finalistTwo.getImgDir());
										Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
										teamLogoBtn.setIcon(new ImageIcon(image));
										JPanel panel = new JPanel(new BorderLayout());
										JLabel label = new JLabel("Winner team: " + finalistTwo.getTeamName() + " with " + finalistTwo.getWins() + " wins.");
										panel.add(teamLogoBtn, BorderLayout.CENTER);
										panel.add(label, BorderLayout.SOUTH);
										JOptionPane.showMessageDialog(null, panel, "Winner is: " + finalistOne.getTeamName(), JOptionPane.PLAIN_MESSAGE);
										championLbl.setText(finalistTwo.getTeamName());		
									}
								}
							}
						}
						
    			}
            	
            	

            	return null;
            }
            /**
             * 
             */
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
