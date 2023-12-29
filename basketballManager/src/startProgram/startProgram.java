package startProgram;

import java.awt.EventQueue;

import player.PlayerInit;
import userGUI.userSignup;

public class startProgram {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerInit.playerInit();
					userSignup frame = new userSignup();
					frame.setSize(810, 486);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}