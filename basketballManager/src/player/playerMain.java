package player;

public class playerMain {
	
	public static void main(String[] args) {
		
		System.out.println("Initializing players...");
		PlayerInit.playerInit();
		System.out.println(PlayerInit.getCPlayers().get(6).getScore());
	}
}
