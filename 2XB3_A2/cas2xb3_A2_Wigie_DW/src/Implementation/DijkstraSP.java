package Implementation;

import java.util.HashMap;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * Implementation of Dijkstra's shortest path algorithm to find the shortest path 
 * in a weighted directed graph. 
 * Inspired by the DijkstraSP implementation on page 655
 * of the Algorithms, 4th Edition textbook by Robert Sedgewick and Kevin Wayne
 * but implemented with hashmaps to mark city by their names.
 * Uses the Bag, IndexMinPQ, and Stack data structure from the external library "algs4.jar" from 
 * https://algs4.cs.princeton.edu/code/
 * 
 * @author Donisius Wigie MacID: wigied
 * 
 */
public class DijkstraSP {
	
	private HashMap<String, Integer> nameToInt;
	private HashMap<Integer, String> intToName;
	private HashMap<String, DirectedEdge> edgeTo;
	private HashMap<String, Double> distTo;
	private IndexMinPQ<Double> pq;
	
	/*
	 * Constructor for DijkstraSP. Takes in a bag data structure from the external library "algs4.jar" from 
	 * https://algs4.cs.princeton.edu/code/
	 * 
	 * @param G Edge-Weighted Directed graph representing the city
	 * @param s Source city, where the path begins.
	 * @param cities Bag of the city names used to represent the nodes of the graph for convenience.
	 */
	public DijkstraSP(EdgeWeightedCityGraph G, String s, Bag<String> cities) {
		
		this.nameToInt = new HashMap<String, Integer>();
		this.intToName = new HashMap<Integer, String>();
		this.edgeTo = new HashMap<String, DirectedEdge>();
		this.distTo = new HashMap<String, Double>();
		this.pq = new IndexMinPQ<Double>(G.V());
		
		int i = 0;
		
		
		for(String city : cities) {
			this.nameToInt.put(city, i);
			this.intToName.put(i, city);
			this.edgeTo.put(city, null);
			this.distTo.put(city, Double.POSITIVE_INFINITY);
			i++;
		}
		
		distTo.replace(s, 0.0);
		pq.insert(nameToInt.get(s), 0.0);
		
		while(!pq.isEmpty()) {
			relax(G, pq.delMin());
		}
 	}
	
	/*
	 * Implementation of relax using HashMaps inspired by the 
	 * relax implementation on page 655
	 * of the Algorithms, 4th Edition textbook by Robert Sedgewick and Kevin Wayne.
	 * Uses the IndexMinPQ data structure from the external library "algs4.jar" from 
	 * https://algs4.cs.princeton.edu/code/
	 * 
	 * @param G Edge-Weighted Directed graph representing the city
	 * @param v Source city, where the path begins
	 * 
	 */
	private void relax(EdgeWeightedCityGraph G, int v) {
		for(DirectedEdge e : G.adj(intToName.get(v))) {
			String w = e.to();
			if(distTo.get(w) > distTo.get(intToName.get(v)) + e.weight()) {
				distTo.replace(w, distTo.get(intToName.get(v)) + e.weight());
				edgeTo.replace(w, e);
				if(pq.contains(nameToInt.get(w))) {
					pq.changeKey(nameToInt.get(w), distTo.get(w));
				}
				else {
					pq.insert(nameToInt.get(w), distTo.get(w));
				}
			}
		}
	}
	
	/**
	 * Finds the distacne between the source city and the input city.
	 * 
	 * @param v The city to get the distance to from the source city.
	 * @return The distance from the cource city to the input city.
	 */
	public double distTo(String v) {
		return distTo.get(v);
	}
	
	/**
	 * Checks to see if there is a path from the source city to the input city.
	 * 
	 * @param v The city to check to see if the source city has a path to.
	 * @return true if a path exists from the source city to the input city, false if not.
	 */
	public boolean hasPathTo(String v) {
		return distTo.get(v) < Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Returns an iterable type of the closest path between the source city and the input city based
	 * on the edge weights.
	 * 
	 * @param v The city from the source city that the shortest path must return.
	 * @return The shortest path between the source city to the input city.
	 */
	public Iterable<DirectedEdge> pathTo(String v){
		if(!hasPathTo(v)) {
			return null;
		}
		
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		
		DirectedEdge e = edgeTo.get(v); 
		while(e != null) {
			path.push(e);
			e = edgeTo.get(e.from());
		}
		path.push(e);
		return path;
	}
}
