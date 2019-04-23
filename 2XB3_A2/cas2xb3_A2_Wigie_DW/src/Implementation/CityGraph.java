package Implementation;
import java.util.HashMap;

import edu.princeton.cs.algs4.Bag;

/**
 * Directed graph representation of the cities and the connections (paths) 
 * between the cities. Similar to the directed graph (digraph) data type on page 
 * 569 in the Algorithms, 4th Edition textbook by Robert Sedgewick and Kevin Wayne.
 * Modified to make it more convenient to represent the paths between the cities.
 * 
 * @author Donisius Wigie MacID: wigied
 * 
 */
public class CityGraph {
	
	private final int V;
	private int E;
	private HashMap<String, Bag<String>> adj;
	
	/**
	 * Directed graph representation of the cities and the connections (paths) 
	 * between the cities. Takes in a bag data structure from the external library "algs4.jar" from 
	 * https://algs4.cs.princeton.edu/code/
	 * 
	 * @param cities Bag of the city names used to represent the nodes of the graph for convenience.
	 * 
	 */
	public CityGraph(Bag<String> cities) {
		this.V = cities.size();
		adj = new HashMap<String, Bag<String>>();
		for(String city : cities) {
			Bag<String> adjCities = new Bag<String>(); 
			adj.put(city, adjCities);
		}
	}
	
	/**
	 * Getter for number of vertices in the graph.
	 *
	 * @return Number of vertices on the graph.
	 */
	public int V() {
		return V;
	}
	
	/**
	 * Getter for number of edges in the graph.
	 *
	 * @return Number of edges on the graph.
	 */
	public int E() {
		return E;
	}
	
	/**
	 * Adds an edge connecting one city to another city, and allows travel only from the first city 
	 * to the other but not the other way around. Increments the number of edges by one every time an
	 * edge is added.
	 * 
	 * @param v The starting city.
	 * @param w The city the starting city points towards.
	 */
	public void addEdge(String v, String w) {
		adj.get(v).add(w);
		E++;
	}
	
	/**
	 * returns the cities which are adjacent to the input city.
	 * 
	 * @param city The city key for the hash map containing all the cities adjacent to the input city.
	 * @return The cities which are adjacent to the input city.
	 */
	public Iterable<String> adj(String city){
		return adj.get(city);
	}
}
