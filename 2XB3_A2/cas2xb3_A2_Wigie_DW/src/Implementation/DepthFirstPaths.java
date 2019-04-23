package Implementation;
import java.util.HashMap;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

/**
 * Implementation of DepthFirstPaths to find a path 
 * in a non-weighted directed graph. 
 * Inspired by the DepthFirstPaths implementation on page 536
 * of the Algorithms, 4th Edition textbook by Robert Sedgewick and Kevin Wayne
 * but implemented with hashmaps to mark city by their names.
 * Uses the Bag and Stack data structure from the external library "algs4.jar" from 
 * https://algs4.cs.princeton.edu/code/
 * 
 * @author Donisius Wigie MacID: wigied
 * 
 */
public class DepthFirstPaths {
	
	private HashMap<String, Boolean> marked;
	private HashMap<String, String> edgeTo;
	private final String s;
	
	/*
	 * Constructor for DepthFirstPaths. Takes in a bag data structure from the external library "algs4.jar" from 
	 * https://algs4.cs.princeton.edu/code/
	 * 
	 * @param G Directed graph representing the city
	 * @param s Source city, where the path begins.
	 * @param cities Bag of the city names used to represent the nodes of the graph for convenience.
	 */
	public DepthFirstPaths(CityGraph G, String s, Bag<String> cities) {
		this.s = s;
		this.marked = new HashMap<String, Boolean>();
		this.edgeTo = new HashMap<String, String>();
		for(String city : cities) {
			marked.put(city, false);
			edgeTo.put(city, null);
		}
		dfs(G, s);
	}
	
	/*
	 * Implementation of dfs using HashMaps inspired by the 
	 * BreadthFirstPaths implementation on page 536
	 * of the Algorithms, 4th Edition textbook by Robert Sedgewick and Kevin Wayne.
	 * 
	 * @param G Directed graph representing the city
	 * @param v Source city, where the path begins
	 * 
	 */
	private void dfs(CityGraph G, String v) {
		marked.replace(v, true);
		for(String w : G.adj(v)) {
			if(!marked.get(w)) {
				edgeTo.replace(w, v);
				dfs(G, w);
			}
		}
	}
	
	/*
	 * Returns true if source city has a path to v, false if not.
	 *
	 * @param v Destination city, where the path needs to go.
	 * 
	 * @return true if path is available, false if not.
	 * 
	 */
	public boolean hasPathTo(String v) {
		return marked.get(v);
	}
	
	/*
	 * Returns the shortest path from source to destination.
	 * Inspired by the BreadthFirstPaths implementation on page 536
     * of the Algorithms, 4th Edition textbook by Robert Sedgewick and Kevin Wayne
     * but implemented using hashmaps. uses the stack data structure from the external library "algs4.jar" from 
     * https://algs4.cs.princeton.edu/code/
	 *
	 * @param v Destination city, where the path needs to go.
	 * 
	 * @return null if there exists no path to v from source city, and an iterable 
	 * return type of the shortest path from source city to v.
	 * 
	 */
	public Iterable<String> pathTo(String v){
		if(!hasPathTo(v)) {
			return null;
		}
		
		Stack<String> path = new Stack<String>();
		
		String x = v; 
		while(x != s) {
			path.push(x);
			x = edgeTo.get(x);
		}
		path.push(s);
		return path;
	}
}
