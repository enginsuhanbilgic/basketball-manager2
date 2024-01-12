/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Engin Sühan Bilgiç, 75771>
*************************************************************************/

package startProgram;

import java.awt.EventQueue;

import player.PlayerInit;
import userGUI.userSignup;

public class startProgram {
	
	/**
	 * Main method to start the program
	 * First I initialize the players
	 * Then call the log in page
	 * EQ invokeLater should be used for thread safety
	 * 
	 * @author EBILGIC20
	 * @param args
	 */
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