package Implementation;

import java.util.HashMap;

import edu.princeton.cs.algs4.Bag;

/**
 * A class of the city in an edge weighted graph representation inspired by the 
 * edge-weighted digraph data type on page 643 of Algorithms, 4th Edition 
 * textbook by Robert Sedgewick and Kevin Wayne. Uses the bag data structure 
 * from the external library "algs4.jar" from https://algs4.cs.princeton.edu/code/
 * Uses the Bag data structure from the external library "algs4.jar" from 
 * https://algs4.cs.princeton.edu/code/
 * @author Donis
 *
 */
public class EdgeWeightedCityGraph {

	private final int V; 
	private int E;
	private HashMap<String, Bag<DirectedEdge>> adj;
	
	/**
	 * A constructor for the EdgeWeightedCityGraph class. Takes in a bag data structure 
	 * from the external library "algs4.jar" from https://algs4.cs.princeton.edu/code/
	 * 
	 * @param cities Bag of the city names used to represent the nodes of the graph for convenience.
	 */
	public EdgeWeightedCityGraph(Bag<String> cities) {
		this.V = cities.size();
		adj = new HashMap<String, Bag<DirectedEdge>>();
		for(String city : cities) {
			Bag<DirectedEdge> adjCities = new Bag<DirectedEdge>(); 
			adj.put(city, adjCities);
		}	
	}
	
	/**
	 * Getter for the number of vertices present in the edge weighted directed graph.
	 *  
	 * @return The number of vertices present in the graph.
	 */
	public int V() {
		return this.V;
	}
	
	/**
	 * Getter for the number of edges present in the edge weighted directed graph.
	 * 
	 * @return The number of edges present in the graph.
	 */
	public int E() {
		return this.E;
	}
	
	/**
	 * A method to add a weighted edge to the edge weighted directed graph.
	 * 
	 * @param e A weighed directed edge to the added to the graph.
	 */
	public void addEdge(DirectedEdge e) {
		adj.get(e.from()).add(e);
		E++;
	}
	
	/**
	 * Returns all the edges adjacent to the input city.
	 * 
	 * @param v The city of which to return all adjacent cities.
	 * @return An iterable type of all the cities which are adjacent to the input city.
	 */
	public Iterable<DirectedEdge> adj(String v){
		return adj.get(v);
	}
	
	/**
	 * Returns the edges that are in the edge weighted directed graph. Uses the bag data structure 
	 * from the external library "algs4.jar" from https://algs4.cs.princeton.edu/code/
	 * 
	 * @return Iterable data type representing the edges in the edge weighted directed graph.
	 */
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for(int v = 0; v < V; v++) {
			for(DirectedEdge e : adj.get(v)) {
				bag.add(e);
			}
		}
		return bag;
	}
}
