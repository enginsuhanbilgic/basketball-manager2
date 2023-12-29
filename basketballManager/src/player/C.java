package player;

import java.util.HashMap;
import java.util.Map;

public class C extends Player{
	
	private static final Map<String, Double> weights;
	
	public C(String name, String position, double points, double rebounds, 
			double assists, double blocks, double steals) {
		super(name, position, points, rebounds, assists, blocks, steals);
	}
	
	static {
		weights = new HashMap<>();
		weights.put("PTS", 0.22);
		weights.put("TRB", 0.30);
		weights.put("AST", 0.08);
		weights.put("BLK", 0.25);
		weights.put("STL", 0.15);
	}
	
	public Map<String, Double> getWeights(){
		return weights;
	}
	
	public int getScore() {
		double pointScore = weights.get("PTS") * this.randnum.nextDouble(Math.max(0.0, this.getPoints() - N), this.getPoints() + N);
		double reboundScore = weights.get("TRB") * this.randnum.nextDouble(Math.max(0.0, this.getRebounds() - N), this.getRebounds() + N);
		double assistScore = weights.get("AST") * this.randnum.nextDouble(Math.max(0.0, this.getAssists() - N), this.getAssists() + N);
		double blockScore = weights.get("BLK") * this.randnum.nextDouble(Math.max(0.0, this.getBlocks() - N), this.getBlocks() + N);
		double stealScore = weights.get("STL") * this.randnum.nextDouble(Math.max(0.0, this.getSteals() - N), this.getSteals() + N);
		
		return (int) Math.round(pointScore + reboundScore + assistScore + blockScore + stealScore);
	}
	
}
