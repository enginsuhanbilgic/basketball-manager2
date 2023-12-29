package team;

import java.util.ArrayList;
import java.util.List;
import player.*;

public class Team {
	
	private List<Player> players;
	private String teamName;
	private String imgDir;
	private int wins;
	
	public Team(String teamName, String imgDir) {
		this.teamName = teamName;
		this.imgDir = imgDir;
		this.wins = 0;
		players = new ArrayList<Player>();
	}
	
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
	
	public void addWins() {this.wins++;}
	
	public int getWins() {return this.wins;}
	public String getTeamName() {return this.teamName;}
	public String getImgDir() {return this.imgDir;}
	public List<Player> getPlayers() {return this.players;}
	
	public void setName(String name) {this.teamName = name;}
	public void setImgDir(String imgDir) {this.imgDir = imgDir;}
	
	public void addPlayer(Player player) {this.players.add(player);}
}
