package player;

import java.security.SecureRandom;
/**
 * Player class to create objects of type Player
 * 
 */
public class Player {
	
	private String name;
	private String position;
	private double points;
	private double rebounds;
	private double assists;
	private double blocks;
	private double steals;
	private boolean isDrafted;
	final public static int N = 5;
	public SecureRandom randnum = new SecureRandom();
	
	/**
	 * Player class constructor
	 * @param name
	 * @param position
	 * @param points
	 * @param rebounds
	 * @param assists
	 * @param blocks
	 * @param steals
	 */
	public Player(String name, String position, double points, double rebounds, 
			double assists, double blocks, double steals) {
		this.name = name;
		this.position = position;
		this.points = points;
		this.rebounds = rebounds;
		this.assists = assists;
		this.blocks = blocks;
		this.steals = steals;
		this.randnum.setSeed(System.currentTimeMillis());
		this.isDrafted = false;
	}
	
	/**
	 * Getter methods for player class
	 * @return of different types
	 */
	public String getName() {return this.name;}
	public String getPosition() {return this.position;}
	public double getPoints() {return this.points;}
	public double getRebounds() {return this.rebounds;}
	public double getAssists() {return this.assists;}
	public double getBlocks() {return this.blocks;}
	public double getSteals() {return this.steals;}
	public boolean getIsDrafted() {return this.isDrafted;}
	
	/**
	 * Methods for drafting and undrafting the player,
	 * meaning that, when the player is taken to a team
	 * it will be drafted and will no longer be able to 
	 * be drafted by another team
	 */
	public void draftPlayer() {this.isDrafted = true;}
	public void unDraftPlayer() {this.isDrafted = false;}
	
}
