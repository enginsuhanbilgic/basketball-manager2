package gameGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import player.Player;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.Color;
/**
 * Class to show the player information
 */
public class PlayerInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Initialize the frame
	 * @param player Player type
	 */
	public static void initPlayerInfo(Player player) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerInfo frame = new PlayerInfo(player);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Opens a frame with player information in it
	 * @param player Player type
	 */
	public PlayerInfo(Player player) {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextArea playerInfoArea = new JTextArea();
		String teamInfo = "";
			teamInfo += String.format("Player: %s\n Position: %s\n Points: %.1f\n Rebounds: %.1f\n Assists: %.1f\n Blocks: %.1f\n Steals: %.1f\n", player.getName(), player.getPosition(),
					player.getPoints(), player.getRebounds(), player.getAssists(), player.getBlocks(), player.getSteals());
		playerInfoArea.append(teamInfo);
		contentPane.add(playerInfoArea);
	}

}
