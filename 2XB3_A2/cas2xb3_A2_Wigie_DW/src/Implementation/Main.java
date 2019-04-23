package Implementation;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

/**
 * Uses the Bag and Stack data structure from the external library "algs4.jar" from 
 * https://algs4.cs.princeton.edu/code/
 * 
 * @author Donisius Wigie MacID: Donisius Wigie
 *
 */
public class Main {
	
	//PLEASE NOTE THAT THIS ONLY WORKS WITH LOWERCASE LETTERS FOR THE CITIES
	
	public static void main(String[] args) throws IOException {
		
		//This section is to initialize the adjacency lists in the unidirectional graph
        Scanner scanner = new Scanner(new File("USCities.csv"));
        scanner.useDelimiter(",");
        scanner.nextLine();
        
        Bag<String> cities = new Bag<String>();
        
        while(scanner.hasNextLine()){
        	scanner.next();
        	scanner.next();
        	scanner.next();
            cities.add(scanner.next().toLowerCase());
            scanner.nextLine();
        }
        scanner.close();
        
        //This section is to create the edges in the unidirectional graph
        CityGraph cityPaths = new CityGraph (cities);    
        
        Scanner scannerPaths = new Scanner(new File("connectedCities.txt"));
        scannerPaths.useDelimiter(",|\\n");
        
        //This ArrayList will contain all the connections between the cities.
        ArrayList<String> cityConnections = new ArrayList<String>();
        while(scannerPaths.hasNext()) {
        	cityConnections.add(scannerPaths.next().toLowerCase().trim());
        }
        scannerPaths.close();
        
        for(int i = 0; i < cityConnections.size() - 1; i += 2) {
        	String from = cityConnections.get(i);
        	String to = cityConnections.get(i + 1);
        	cityPaths.addEdge(from, to);
        }
        
        //This section is to find a path from Boston to Minneapolis using DepthFirstPaths
        System.out.println("Path found from Boston to Minneapolis through DepthFirstPaths:");
        DepthFirstPaths findPathDFS = new DepthFirstPaths(cityPaths, "boston", cities);
        Stack<String> pathDFS = (Stack<String>) findPathDFS.pathTo("minneapolis");
        
        for(String i : pathDFS) {
        	System.out.print(i);
        	if(i != "minneapolis") {
        		System.out.print("  ->  ");
        	}
        }
        System.out.println();
        
        //This section is to find a path from Boston to Minneapolis using BreadthFirstPaths
        System.out.println("Path found from Boston to Minneapolis through BreadthFirstPaths:");
        BreadthFirstPaths findPathBFS = new BreadthFirstPaths(cityPaths, "houston", cities);
        Stack<String> pathBFS = (Stack<String>) findPathBFS.pathTo("seattle");
        
        for(String i : pathBFS) {
        	System.out.print(i);
        	if(i != "minneapolis") {
        		System.out.print("  ->  ");
        	}
        }
        System.out.println();
        
        //This section is to set up the menu for the three restaurants
        Scanner scannerRestaurant = new Scanner(new File("menu.csv"));
        scannerRestaurant.useDelimiter(",");
        scannerRestaurant.nextLine();
        
        //Temporary Array Lists to store information regarding food items to create the menu classes.
        ArrayList<FoodItem> McDMenu = new ArrayList<FoodItem>();
        ArrayList<FoodItem> BKMenu = new ArrayList<FoodItem>();
        ArrayList<FoodItem> wendysMenu = new ArrayList<FoodItem>();
        
        while(scannerRestaurant.hasNextLine()){
        	String restaurantName = scannerRestaurant.next();
        	String itemName = scannerRestaurant.next();
        	double price = Double.parseDouble(scannerRestaurant.next().substring(1));
        	FoodItem item = new FoodItem(itemName, price);
        	if(restaurantName.contains("McDonald")) {
        		McDMenu.add(item);
        	}
        	else if(restaurantName.contains("Burger King")) {
        		BKMenu.add(item);
        	}
        	else if(restaurantName.contains("Wendy")) {
        		wendysMenu.add(item);
        	}
        	scannerRestaurant.nextLine();
        }
        scannerRestaurant.close();
        
        Menu WendysMenu = new Menu(wendysMenu);
        Menu BurgerKingMenu = new Menu(BKMenu);
        Menu McDonaldsMenu = new Menu(McDMenu);
        
        //Initialize the cities with information regarding latitude and longitude
        //and put info in a HashMap to calculate distances between cities.
        
        //For making it easy to calculate distances between cities and restaurants.
        HashMap<String, City> cityInfo = new HashMap<String, City>();
        
        //For filling in cityBag and cityInfo above
        Scanner scannerCityInfo = new Scanner(new File("USCities.csv"));
        scannerCityInfo.useDelimiter(",|\r");
        scannerCityInfo.nextLine();
        while(scannerCityInfo.hasNextLine()) {
        	scannerCityInfo.next();
        	scannerCityInfo.next();
        	scannerCityInfo.next();
        	String cityName = scannerCityInfo.next().toLowerCase().trim();
        	double lat = Double.parseDouble(scannerCityInfo.next());
        	double lon = Double.parseDouble(scannerCityInfo.next());
        	City newCity = new City(cityName, lat, lon);
        	cityInfo.put(cityName, newCity);
        	if(scannerCityInfo.hasNextLine()) {
        		scannerCityInfo.nextLine();
        	}
        }
        scannerCityInfo.close();
        
        //Initialize the EdgeWeightedCityGraph
        EdgeWeightedCityGraph weightedCityGraph = new EdgeWeightedCityGraph(cities);
        
        //Find the edges and calculate the distance and put them in weightedCityGraph
        for(int i = 0; i < cityConnections.size() - 1; i += 2) {
        	City from = cityInfo.get(cityConnections.get(i));
        	City to = cityInfo.get(cityConnections.get(i + 1));
        	
        	double distBetween = distanceCalculator(from.getLatitude(), from.getLongitude(), to.getLatitude(), to.getLongitude());
        	
        	DirectedEdge newDirEdge = new DirectedEdge(cityConnections.get(i), cityConnections.get(i + 1), distBetween);
        	weightedCityGraph.addEdge(newDirEdge);
        }
        
        //This section is to find the shortest path based on distances calculated between the cities
        //and uses Dijkstra's shortest path algorithm to find the shortest path based on distance
        //calculated using the Haversine method.    
        DijkstraSP shortestPath = new DijkstraSP(weightedCityGraph, "boston", cities);
        
        //An ArrayList to keep track of the shortest path found through Dijkstra's shortest path algorithm.
        ArrayList<String> shortestPathList = new ArrayList<String>();
        
        Stack<DirectedEdge> pathToDestination = (Stack<DirectedEdge>) shortestPath.pathTo("minneapolis");
        System.out.println("Path found from Boston to Minneapolis through DijkstraSP:");
        int destinationCounter = 0;
        for(DirectedEdge e : pathToDestination) {
        	if(destinationCounter > 0) {
        		System.out.print(e.from());
        		shortestPathList.add(e.from());
        		System.out.print("  ->  ");
        	}
        	if(destinationCounter == pathToDestination.size() - 1) {
        		System.out.println(e.to());
        		shortestPathList.add(e.to());
        	}
        	destinationCounter++;
        }

        //Put a restaurant to a city if a restaurant is close to it. It looks neater
        //and would be a lot easier to make restaurant classes for each restaurant on the list 
        //but that would take too much space and resources.
        
        //within range is used to calculate the distance that within 0.5 latitude and longitude is using the Haversine
        //function.
        double withinRange = distanceCalculator(1, 1, 0.5, 0.5);
        for(String city : cities) {
        	
        	//This part of the code segment parses through burgerking.csv puts a burgerking
        	//in a city object if there is at least one within 0.5 of longitude and latitude
        	//of the city.
        	Scanner burgerkingScanner = new Scanner(new File("burgerking.csv"));
        	
        	burgerkingScanner.useDelimiter(",");
        	burgerkingScanner.nextLine();
        	
        	while(burgerkingScanner.hasNextLine()) {
        		double lo = Double.parseDouble(burgerkingScanner.next());
        		double la = Double.parseDouble(burgerkingScanner.next());
        		
        		if(distanceCalculator(la, lo, cityInfo.get(city).getLatitude(), cityInfo.get(city).getLongitude()) <= withinRange) {
        			cityInfo.get(city).putRestaurant(burgerkingScanner.next().substring(1));
        		}
        		
        		burgerkingScanner.nextLine();
        	}
        	
        	burgerkingScanner.close();
        	
        	//This part of the code segment parses through wendys.csv puts a wendys
        	//in a city object if there is at least one within 0.5 of longitude and latitude
        	//of the city.
        	Scanner wendysScanner = new Scanner(new File("wendys.csv"));
        	
        	wendysScanner.useDelimiter(",");
        	wendysScanner.nextLine();
        	
        	while(wendysScanner.hasNextLine()) {
        		double lo = Double.parseDouble(wendysScanner.next());
        		double la = Double.parseDouble(wendysScanner.next());
        		
        		if(distanceCalculator(la, lo, cityInfo.get(city).getLatitude(), cityInfo.get(city).getLongitude()) <= withinRange) {
        			cityInfo.get(city).putRestaurant(wendysScanner.next().substring(1));
        		}
        		
        		wendysScanner.nextLine();
        	}
        	
        	wendysScanner.close();
        	
        	//This part of the code segment parses through mcdonalds.csv puts a mcdonalds
        	//in a city object if there is at least one within 0.5 of longitude and latitude
        	//of the city.
        	Scanner mcdonaldsScanner = new Scanner(new File("mcdonalds.csv"));
        	
        	mcdonaldsScanner.useDelimiter(",");
        	mcdonaldsScanner.nextLine();
        	
        	while(mcdonaldsScanner.hasNextLine()) {
        		double lo = Double.parseDouble(mcdonaldsScanner.next());
        		double la = Double.parseDouble(mcdonaldsScanner.next());
        		
           		if(distanceCalculator(la, lo, cityInfo.get(city).getLatitude(), cityInfo.get(city).getLongitude()) <= withinRange) {
        			cityInfo.get(city).putRestaurant(mcdonaldsScanner.next().substring(1));
        		}
        		
        		mcdonaldsScanner.nextLine();
        	}
        	
        	mcdonaldsScanner.close();
        }
        
        //This stack is used to keep track of food items ordered from the previous city and 
        //makes sure that food items are not ordered back to back.
        restaurantPicker foodChoices = new restaurantPicker(shortestPathList, cityInfo, McDonaldsMenu, WendysMenu, BurgerKingMenu);
        foodChoices.pickMeals();
        double totalCost = foodChoices.getTotalCost();
        System.out.print("Total cost for meals on the trip: $ ");
        System.out.println(totalCost);
	}

	//Implementation of the Haversine method to find distance from two points using latitude and longitude
	//https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
	private static double distanceCalculator(double la1, double lo1, double la2, double lo2) {
	    final int R = 6371; // Radius of the earth

	    double lat = Math.toRadians(la2 - la1);
	    double lon = Math.toRadians(lo2 - lo1);
	    double a = Math.sin(lat / 2) * Math.sin(lat / 2)
	            + Math.cos(Math.toRadians(la1)) * Math.cos(Math.toRadians(la2))
	            * Math.sin(lon / 2) * Math.sin(lon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    distance = Math.pow(distance, 2);

	    return Math.sqrt(distance);
	}
}
