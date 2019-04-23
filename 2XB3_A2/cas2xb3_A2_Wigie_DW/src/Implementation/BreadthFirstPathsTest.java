package Implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

// Uses the Bag, Queue, and Stack data structure from the external library "algs4.jar" from 
// https://algs4.cs.princeton.edu/code/
class BreadthFirstPathsTest {

	@Test
	void test() throws FileNotFoundException {
		
		//Contain all the cities the key is able to reach.
		HashMap<String, ArrayList<String>> adj = new HashMap<String, ArrayList<String>>();
		
		//Obtain all city names
	    Scanner scanner = new Scanner(new File("USCities.csv"));
	    scanner.useDelimiter(",");
	    scanner.nextLine();
	    
	    Bag<String> cities = new Bag<String>();
	   	    
	    while(scanner.hasNextLine()){
	    	scanner.next();
	    	scanner.next();
	    	scanner.next();
	        cities.add(scanner.next().toLowerCase().trim());
	        scanner.nextLine();
	    }
	    scanner.close();
	    
	    for(String city : cities) {
	    	ArrayList<String> newCityList = new ArrayList<String>();
	    	adj.put(city, newCityList);
	    }
	    
	    //Finds all adjacent cities to every city.
        for(String city: cities) {
            Scanner scannerPaths = new Scanner(new File("connectedCities.txt"));
            scannerPaths.useDelimiter(",|\\n");
        	while(scannerPaths.hasNextLine()) {
        		String source = scannerPaths.next().trim().toLowerCase();
        		String destination = scannerPaths.next().trim().toLowerCase();
        		if(city.equalsIgnoreCase(source)) {
        			adj.get(city).add(destination);
        		}
        		scannerPaths.nextLine();
        	}
        	scannerPaths.close();
        }
        
        CityGraph cityPaths = new CityGraph (cities);    
        
        Scanner scannerPath = new Scanner(new File("connectedCities.txt"));
        scannerPath.useDelimiter(",|\\n");
        
        //This ArrayList will contain all the connections between the cities.
        ArrayList<String> cityConnections = new ArrayList<String>();
        while(scannerPath.hasNext()) {
        	cityConnections.add(scannerPath.next().toLowerCase().trim());
        }
        scannerPath.close();
        
        for(int i = 0; i < cityConnections.size() - 1; i += 2) {
        	String from = cityConnections.get(i);
        	String to = cityConnections.get(i + 1);
        	cityPaths.addEdge(from, to);
        }
        
        //Checks the adjacency list created and makes sure hasPathTo function from BreadthFirstPaths
        //works as intended. This test will validate the hasPathTo method so that it can be used
        //in the test to make sure all possible path combinations are valid.
        for(String city : cities) {
        	for(String city2 : adj.get(city)) {
        		BreadthFirstPaths testPath = new BreadthFirstPaths(cityPaths, city, cities);
        		assert(testPath.hasPathTo(city2));
        	}
        }
        System.out.println("hasPathTo method isvalid to use in future test cases for DFS.");
        
        
        Scanner routeScanner = new Scanner(new File("connectedCities.txt"));
        routeScanner.useDelimiter(",|\\n");
        
        ArrayList<String> startingPoints = new ArrayList<String>();
        ArrayList<String> destinationPoints = new ArrayList<String>();
        
        while(routeScanner.hasNextLine()) {
        	String startPoint = routeScanner.next().trim().toLowerCase();
        	String destPoint = routeScanner.next().trim().toLowerCase();
        	if(!startingPoints.contains(startPoint)) {
        		startingPoints.add(startPoint);
        	}
        	if(!destinationPoints.contains(destPoint)){
        		destinationPoints.add(destPoint);
        	}
       	
        	routeScanner.nextLine();
        }
        routeScanner.close();
        
        for(String e : destinationPoints) {
        	System.out.println(e);
        }
        
        //Find all possible pathways using hasPathTo since hasPathTo had been confirmed 
        //to work from the above test case. Will test every possible path from every possible 
        //city combinations and check the validity of each combination and path against the adjacency 
        //list to make sure the path is possible.
        for (String startCity : startingPoints) {
        	for(String destinationCity : destinationPoints) {
	        	BreadthFirstPaths testBFS = new BreadthFirstPaths(cityPaths, "boston", cities);
	        	if(testBFS.hasPathTo(destinationCity)) {
	        		Stack<String> pathBFS = (Stack<String>) testBFS.pathTo(destinationCity);
	        		ArrayList<String> testPathBFS = new ArrayList<String>();
	        		for(String e : pathBFS) {
	        			testPathBFS.add(e);
	        		}
	        		System.out.print("BFS path found from: ");
	        		System.out.print(startCity);
	        		System.out.print(" to ");
	        		System.out.print(destinationCity);
	        		System.out.println(":");
	        		System.out.println("**************************************************");
	        		for(String i : testPathBFS) {
	        			System.out.println(i);
	        		}
	        		for(int i = 1; i < testPathBFS.size(); i += 2) {
	        			String startPeg = testPathBFS.get(i - 1);
	        			String endPeg = testPathBFS.get(i);
	        			assert(adj.get(startPeg).contains(endPeg));
	        		}
	        		System.out.println("TEST PATH AGAINST ADJACENCY LIST, BFS PATH VALID");
	        		System.out.println("**************************************************");
	        	}
	        	
        	}
        }
        
	}
}
