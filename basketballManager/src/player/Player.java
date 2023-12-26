package player;


public class Player {
	
	private String name;
	private String position;
	private double points;
	private double rebounds;
	private double assists;
	private double blocks;
	private double steals;
	
	public Player(String name, String position, double points, double rebounds, 
			double assists, double blocks, double steals) {
		this.name = name;
		this.position = position;
		this.points = points;
		this.rebounds = rebounds;
		this.assists = assists;
		this.blocks = blocks;
		this.steals = steals;
	}
	
	public String getName() {return this.name;}
	public String getPosition() {return this.position;}
	public double getPoints() {return this.points;}
	public double getRebounds() {return this.rebounds;}
	public double getAssists() {return this.assists;}
	public double getBlocks() {return this.blocks;}
	public double getSteals() {return this.steals;}
	
}
