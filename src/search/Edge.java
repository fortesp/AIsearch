package search;

public class Edge {
	
	private int cost;
	private State state;
	
	public Edge(State state, int cost) {
		
		this.state = state;
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
 
	public State getState() {
		return state;
	}
	
}
