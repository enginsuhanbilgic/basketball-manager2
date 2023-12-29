package team;

import java.util.ArrayList;
import java.util.List;

public class TeamInit {
	
	private static List<Team> teams = new ArrayList<>();
	private static Team userTeam;
	
	public static void teamInit(Team userTeam) {
		
		//Adding 15 teams to the teams list. These will be played by the computer
		teams.add(new Team("Fenerbahçe","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\fenerbahce.png"));
		teams.add(new Team("Galatasaray","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\galatasaray.png"));
		teams.add(new Team("Anadolu Efes","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\anadoluEfes.png"));
		teams.add(new Team("Beşiktaş","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\besiktas.png"));
		teams.add(new Team("Trabzonspor","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\trabzonspor.png"));
		teams.add(new Team("Karşıyaka","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\karsiyaka.png"));
		teams.add(new Team("Boston Celtics","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\bostonCeltics.png"));
		teams.add(new Team("Chicago Bulls","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\chicagoBulls.png"));
		teams.add(new Team("Atlanta Hawks","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\atlantaHawks.png"));
		teams.add(new Team("Dallas Mavericks","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\dallasMavericks.png"));
		teams.add(new Team("Los Angeles Lakers","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\losAngelesLakers.png"));
		teams.add(new Team("Golden State Warriors","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\goldenStateWarriors.png"));
		teams.add(new Team("San Antonio Spurs","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\sanAntonioSpurs.png"));
		teams.add(new Team("Miami Heat","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\miamiHeat.png"));
		teams.add(new Team("Detroit Pistons","C:\\Users\\EBILGIC20\\git\\repository2\\basketballManager\\src\\logos\\detroitPistons.png"));
		
		//Team of the user. Image is set to be default, can be changed later
		teams.add(userTeam);
		
	}	
	
	public static List<Team> getTeams() {return teams;} 
}
