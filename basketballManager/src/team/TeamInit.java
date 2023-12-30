package team;

import java.util.ArrayList;
import java.util.List;

public class TeamInit {
	
	private static List<Team> teams = new ArrayList<>();
	private static Team userTeam;
	
	public static void teamInit(Team userTeam) {
		
		//Adding 15 teams to the teams list. These will be played by the computer
		teams.add(new Team("Fenerbahçe","src\\logos\\fenerbahce.png"));
		teams.add(new Team("Galatasaray","src\\logos\\galatasaray.png"));
		teams.add(new Team("Anadolu Efes","src\\logos\\anadoluEfes.png"));
		teams.add(new Team("Beşiktaş","src\\logos\\besiktas.png"));
		teams.add(new Team("Trabzonspor","src\\logos\\trabzonspor.png"));
		teams.add(new Team("Karşıyaka","src\\logos\\karsiyaka.png"));
		teams.add(new Team("Boston Celtics","src\\logos\\bostonCeltics.png"));
		teams.add(new Team("Chicago Bulls","src\\logos\\chicagoBulls.png"));
		teams.add(new Team("Atlanta Hawks","src\\logos\\atlantaHawks.png"));
		teams.add(new Team("Dallas Mavericks","src\\logos\\dallasMavericks.png"));
		teams.add(new Team("Los Angeles Lakers","src\\logos\\losAngelesLakers.png"));
		teams.add(new Team("Golden State Warriors","src\\logos\\goldenStateWarriors.png"));
		teams.add(new Team("San Antonio Spurs","src\\logos\\sanAntonioSpurs.png"));
		teams.add(new Team("Miami Heat","src\\logos\\miamiHeat.png"));
		teams.add(new Team("Detroit Pistons","src\\logos\\detroitPistons.png"));
		
		//Team of the user. Image is set to be default, can be changed later
		teams.add(userTeam);
		
	}	
	
	public static List<Team> getTeams() {return teams;} 
}
