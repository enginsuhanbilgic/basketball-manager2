package player;

import java.util.HashMap;
import java.util.Map;
/**
 * Center player class that is a 
 * subclass of Player
 */
public class C extends Player{
	
	private static final Map<String, Double> weights;
	/**
	 * C constructor 
	 * @param name
	 * @param position
	 * @param points
	 * @param rebounds
	 * @param assists
	 * @param blocks
	 * @param steals
	 */
	public C(String name, String position, double points, double rebounds, 
			double assists, double blocks, double steals) {
		super(name, position, points, rebounds, assists, blocks, steals);
	}
	
	/**
	 * Static block that sets the
	 * weights of the position 
	 */
	static {
		weights = new HashMap<>();
		weights.put("PTS", 0.22);
		weights.put("TRB", 0.30);
		weights.put("AST", 0.08);
		weights.put("BLK", 0.25);
		weights.put("STL", 0.15);
	}
	
	/**
	 * Getter method for weights map
	 * @return type of Map
	 */
	public Map<String, Double> getWeights(){
		return weights;
	}
	
	/**
	 * Method to calculate and get total score of the player
	 * @return int type, returns the total score calculated with weights
	 */
	public int getScore() {
		double pointScore = weights.get("PTS") * this.randnum.nextDouble(Math.max(0.0, this.getPoints() - N), this.getPoints() + N);
		double reboundScore = weights.get("TRB") * this.randnum.nextDouble(Math.max(0.0, this.getRebounds() - N), this.getRebounds() + N);
		double assistScore = weights.get("AST") * this.randnum.nextDouble(Math.max(0.0, this.getAssists() - N), this.getAssists() + N);
		double blockScore = weights.get("BLK") * this.randnum.nextDouble(Math.max(0.0, this.getBlocks() - N), this.getBlocks() + N);
		double stealScore = weights.get("STL") * this.randnum.nextDouble(Math.max(0.0, this.getSteals() - N), this.getSteals() + N);
		
		return (int) Math.round(pointScore + reboundScore + assistScore + blockScore + stealScore);
	}
	
	/**
	 * toString method of the object
	 * to be used in showing player information
	 */
	@Override
	public String toString() {
		return this.getName() + " Position: " + this.getPosition() + " Points: " + this.getPoints() + 
				" Rebounds: " + this.getRebounds() + " Assists: " + this.getAssists() + " Blocks: "
				+ this.getBlocks() + " Steals: " + this.getSteals();
	}
	
}
