package Implementation;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to represent a city and its information.
 * This includes the location of the city based on its longitude and latitude,
 * the name of the city, and the list of restaurants that are located within the city.
 * 
 * 
 * @author Donisius Wigie MacID: wigied
 * 
 */
public class City {
	
	private final String cityName;
	private final double latitude;
	private final double longitude;
	private ArrayList<String> restaurants;
	
	public City(String cityName, double latitude, double longitude) {
		this.cityName = cityName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.restaurants = new ArrayList<String>();
	}
	
	/*
	 * Getter for the name of the city.
	 */
	public String getName() {
		return this.cityName;
	}
	
	/*
	 * Getter for the latitude of the city.
	 */
	public double getLatitude() {
		return this.latitude;
	}
	
	/*
	 * Getter for the longitude of the city.
	 */
	public double getLongitude() {
		return this.longitude;
	}
	
	/*
	 * Getter for the list of restaurants available in the city.
	 */
	public Iterable<String> getRestaurants(){
		return this.restaurants;
	}
	
	/*
	 * Places a restaurant that is within 0.5 longitude and latitude of the city.
	 */
	public void putRestaurant(String s) {
		
		//These loops will at most run 3 times.
		int size = this.restaurants.size();
		if(s.contains("McDonalds")) {
			for(int i = 0; i < size; i++) {
				if(this.restaurants.get(i).contains("McDonalds")) {
					return;
				}
			}
			this.restaurants.add(s);
		}
		
		if(s.contains("BurgerKing")) {
			for(int i = 0; i < size; i++) {
				if(this.restaurants.get(i).contains("BurgerKing")) {
					return;
				}
			}
			this.restaurants.add(s);
		}
		
		if(s.contains("Wendy")) {
			for(int i = 0; i < size; i++) {
				if(this.restaurants.get(i).contains("Wendy")) {
					return;
				}
			}
			this.restaurants.add(s);
		}
	}
}
