package player;

import java.util.HashMap;
import java.util.Map;

public class PF extends Player{
	
	private static final Map<String, Double> weights;
	
	public PF(String name, String position, double points, double rebounds, 
			double assists, double blocks, double steals) {
		super(name, position, points, rebounds, assists, blocks, steals);
	}
	
	static {
		weights = new HashMap<>();
		weights.put("PTS", 0.30);
		weights.put("TRB", 0.25);
		weights.put("AST", 0.10);
		weights.put("BLK", 0.20);
		weights.put("STL", 0.15);
	}
	
	public Map<String, Double> getWeights(){
		return weights;
	}
	
}
