package team;
/**
 * Class to initialize teams controlled by 
 * the program and by the user
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamInit {
	
	private static List<Team> teams = new ArrayList<>();
	private static HashMap<String, Team> teamsMap = new HashMap<>();
	private static Team userTeam;
	
	/**
	 * Static method to initialize the teams
	 * It first creates 15 different teams,
	 * then adds them to a set, 
	 * then adds them to a map<String, team>
	 * as well as userTeam
	 * @param userTeam Team type, team of the user
	 */
	public static void teamInit(Team userTeam) {
		
		Team fb = new Team("Fenerbahçe","src\\logos\\fenerbahce.png");
		Team gs = new Team("Galatasaray","src\\logos\\galatasaray.png");
		Team ae = new Team("Anadolu Efes","src\\logos\\anadoluEfes.png");
		Team bjk = new Team("Beşiktaş","src\\logos\\besiktas.png");
		Team ts = new Team("Trabzonspor","src\\logos\\trabzonspor.png");
		Team krsyk = new Team("Karşıyaka","src\\logos\\karsiyaka.png");
		Team bc = new Team("Boston Celtics","src\\logos\\bostonCeltics.png");
		Team cb = new Team("Chicago Bulls","src\\logos\\chicagoBulls.png");
		Team ah = new Team("Atlanta Hawks","src\\logos\\atlantaHawks.png");
		Team dm = new Team("Dallas Mavericks","src\\logos\\dallasMavericks.png");
		Team lal = new Team("Los Angeles Lakers","src\\logos\\losAngelesLakers.png");
		Team gsw = new Team("Golden State Warriors","src\\logos\\goldenStateWarriors.png");
		Team sap = new Team("San Antonio Spurs","src\\logos\\sanAntonioSpurs.png");
		Team mh = new Team("Miami Heat","src\\logos\\miamiHeat.png");
		Team dp = new Team("Detroit Pistons","src\\logos\\detroitPistons.png");
		//Adding 15 teams to the teams list. These will be played by the computer
		teams.add(fb);teams.add(gs);teams.add(ae);teams.add(bjk);teams.add(ts);
		teams.add(krsyk);teams.add(bc);teams.add(cb);teams.add(ah);teams.add(dm);
		teams.add(lal);teams.add(gsw);teams.add(sap);teams.add(mh);teams.add(dp);
		
		//Team of the user. Image is set to be default, can be changed later
		teams.add(userTeam);
		
		teamsMap.put("Fenerbahçe", fb);
		teamsMap.put("Galatasaray", gs);
		teamsMap.put("Anadolu Efes", ae);
		teamsMap.put("Beşiktaş", bjk);
		teamsMap.put("Trabzonspor", ts);
		teamsMap.put("Karşıyaka", krsyk);
		teamsMap.put("Boston Celtics", bc);
		teamsMap.put("Chicago Bulls", cb);
		teamsMap.put("Atlanta Hawks", ah);
		teamsMap.put("Dallas Mavericks", dm);
		teamsMap.put("Los Angeles Lakers", lal);
		teamsMap.put("Golden State Warriors", gsw);
		teamsMap.put("San Antonio Spurs", sap);
		teamsMap.put("Miami Heat", mh);
		teamsMap.put("Detroit Pistons", dp);
		
	}	
	
	/**
	 * Getter methods for team lists and maps
	 * @return list of teams
	 * @return map of key: Team name, value: team
	 */
	public static List<Team> getTeams() {return teams;} 
	public static HashMap<String, Team> getTeamMap() {return teamsMap;}
}
