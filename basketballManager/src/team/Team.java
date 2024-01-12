package team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import player.*;

/**
 * Class for creating teams
 * It implements Comparable interface
 * and uses it to compare teams according to 
 * their wins
 */
public class Team implements Comparable<Team>{
	
	private List<Player> players;
	private String teamName;
	private String imgDir;
	private int wins;
	
	/**
	 * Team class constructor
	 * Inintializes the name and image directory specified
	 * and sets the total win to 0
	 * @param teamName String, name of the team
	 * @param imgDir String, image directory of the team
	 */
	public Team(String teamName, String imgDir) {
		this.teamName = teamName;
		this.imgDir = imgDir;
		this.wins = 0;
		players = new ArrayList<Player>();
	}
	
	/**
	 * This method checks every player's subclass type and calculates
	 * their score according to their subclass weights
	 * @return int This returns the total score of the team
	 */
	public int getTotalScore() {
		int score = 0;
		for(Player player : players) {
			if(player instanceof C) {
				score += ((C) player).getScore();
			}
			if(player instanceof PF) {
				score += ((PF) player).getScore();
			}
			if(player instanceof PG) {
				score += ((PG) player).getScore();
			}
			if(player instanceof SF) {
				score += ((SF) player).getScore();
			}
			if(player instanceof SG) {
				score += ((SG) player).getScore();
			}
		}
		return score;
	}
	
	/**
	 * Adds one win to the team
	 */
	public void addWins() {this.wins++;}
	
	/**
	 * Getters for team class
	 * @return Returns different types
	 */
	public int getWins() {return this.wins;}
	public String getTeamName() {return this.teamName;}
	public String getImgDir() {return this.imgDir;}
	public List<Player> getPlayers() {return this.players;}
	public void removePlayer(Player player) {this.players.remove(player);}
	
	/**
	 * Setters for name and image directory
	 * @param name String, sets the name
	 * @param imgDir String, sets the image directory 
	 */
	public void setName(String name) {this.teamName = name;}
	public void setImgDir(String imgDir) {this.imgDir = imgDir;}
	
	/**
	 * Adds players to the team
	 * @param player Player type, Holds information about players
	 */
	public void addPlayer(Player player) {this.players.add(player);}
	
	/**
	 * Overridden compareTo method implemented from Comparable interface
	 * It compares specified other team's wins with current team's wins
	 * @return int, -1, 0 or 1
	 */
	@Override
	public int compareTo(Team otherTeam) {
		return Integer.compare(otherTeam.getWins(), this.wins);
	}
	
	/**
	 * Using Collections sort and specified comparator
	 * to compare every team according to their win count
	 * @param teams
	 */
	public static void sortTeamsByWins(List<Team> teams) {
		Collections.sort(teams);
	}
	
	
}
