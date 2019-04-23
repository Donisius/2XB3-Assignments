package Implementation;

/**
 * A class representing a weighted edge data structure inspired by the directed weighted
 * edge data type on page 642 of the Algorithms, 4th Edition textbook by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Donisius Wigie MacID: wigied
 *
 */
public class DirectedEdge {
	
	private final String v;
	private final String w;
	private final double weight;
	
	/**
	 * A constructor for the directed weighted edge data type which creates an edge 
	 * with a weighting.
	 * 
	 * @param v The city the directed edge begins from.
	 * @param w The city the directed edge points towards.
	 * @param weight The weight of the edge connecting the two input cities.
	 */
	public DirectedEdge(String v, String w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = (10000000 - weight)/10000;
	}
	
	/**
	 * A getter for the weight of the edge.
	 * 
	 * @return The weight of the edge.
	 */
	public double weight() {
		return weight;
	}
	
	/**
	 * A getter for the city the directed edge begins from.
	 * 
	 * @return The city the directed edge begins from.
	 */
	public String from() {
		return this.v;
	}
	
	/**
	 * A getter for the city the directed edge points towards.
	 * 
	 * @return The city the directed edge points towards.
	 */
	public String to() {
		return this.w;
	}
}
