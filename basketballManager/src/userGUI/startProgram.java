package userGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class startProgram {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
