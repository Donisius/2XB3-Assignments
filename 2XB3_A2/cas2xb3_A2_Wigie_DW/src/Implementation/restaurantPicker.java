package Implementation;

import java.util.ArrayList;
import java.util.HashMap;

import edu.princeton.cs.algs4.Stack;

/**
 * A class to pick the best possible meals to optimize the cost of food for the trip.
 * Uses the Stack data structure from the external library "algs4.jar" from 
 * https://algs4.cs.princeton.edu/code/
 * 
 * @author Donisius Wigie MacID: Wigied
 *
 */
public class restaurantPicker {

	private HashMap<String, City> cityList;
	private ArrayList<String> sequencePath;
	private ArrayList<FoodItem> mcdonaldsItems;
	private ArrayList<FoodItem> burgerkingItems;
	private ArrayList<FoodItem> wendysItems;
	private Stack<FoodItem> foodTracker;	
	private HashMap<String, FoodItem> restaurantChoices; 
	private HashMap<String, String> restaurantNames;
	
	/**
	 * Constructor for the restaurant picker class to choose the best possible meal choices to 
	 * optimize the choice of food items to minimize the cost of food.
	 * 
	 * @param sequencePath ArrayList containing the sequence of cities in the path.
	 * @param cityList HashMap containing all city information.
	 * @param mcdonalds Menu for McDonald's.
	 * @param wendys Menu for Wendy's.
	 * @param burgerking Menu for BurgerKing.
	 */
	public restaurantPicker(ArrayList<String> sequencePath, HashMap<String, City> cityList, 
			Menu mcdonalds, Menu wendys, Menu burgerking) {
		this.cityList = cityList;
		this.sequencePath = sequencePath;
		this.restaurantChoices = new HashMap<String, FoodItem>();
		this.restaurantNames = new HashMap<String, String>();
		this.mcdonaldsItems = new ArrayList<FoodItem>();
		this.burgerkingItems = new ArrayList<FoodItem>();
		this.wendysItems = new ArrayList<FoodItem>();
		this.foodTracker = new Stack<FoodItem>();
		ArrayList<FoodItem> mcdonaldsList = (ArrayList<FoodItem>) mcdonalds.getMenuItems();
		ArrayList<FoodItem> burgerkingList = (ArrayList<FoodItem>) burgerking.getMenuItems();
		ArrayList<FoodItem> wendysList = (ArrayList<FoodItem>) wendys.getMenuItems();	
		
		for(FoodItem food : mcdonaldsList) {
			this.mcdonaldsItems.add(food);
		}
		
		for(FoodItem food : burgerkingList) {
			this.burgerkingItems.add(food);
		}
		
		for(FoodItem food : wendysList) {
			this.wendysItems.add(food);
		}
	}
	
	/**
	 * Picks meals based on the available restaurants in a city and chooses a food item which will not repeat 
	 * for consecutive cities. 
	 * 
	 */
	public void pickMeals() {
	
		for(String city : sequencePath) {
			this.restaurantChoices.put(city,  null);
			this.restaurantNames.put(city, null);
			ArrayList<String> cityRestaurants = (ArrayList<String>) cityList.get(city).getRestaurants();
			for(String restaurants : cityRestaurants) {
				this.restaurantChoices.replace(city, mcdonaldsItems.get(mcdonaldsItems.size() - 1));
				if(city == sequencePath.get(0)) {
					this.restaurantChoices.replace(city, mcdonaldsItems.get(mcdonaldsItems.size() - 1));
					this.foodTracker.push(mcdonaldsItems.get(mcdonaldsItems.size() - 1));
				}
				if(restaurants.contains("McDonalds")) {
					int i = 0;
					while(i < 3) {
						if(!this.foodTracker.isEmpty() && this.foodTracker.peek().getItemName() != mcdonaldsItems.get(i).getItemName()
								&& this.restaurantChoices.get(city).getPrice() > mcdonaldsItems.get(i).getPrice()) {
							this.restaurantChoices.replace(city, mcdonaldsItems.get(i));
							this.restaurantNames.replace(city, restaurants);
							break;
						}
						i++;
					}
				}
				if(restaurants.contains("BurgerKing")) {
					int i = 0;
					while(i < 3) {
						if(!this.foodTracker.isEmpty() && this.foodTracker.peek().getItemName() != burgerkingItems.get(i).getItemName()
								&& this.restaurantChoices.get(city).getPrice() > burgerkingItems.get(i).getPrice()){
							this.restaurantChoices.replace(city,  burgerkingItems.get(i));
							this.restaurantNames.replace(city, restaurants);
							break;
						}
						i++;
					}
				}
				if(restaurants.contains("Wendy")) {
					int i = 0;
					while(i < 3) {
						if(!this.foodTracker.isEmpty() && this.foodTracker.peek().getItemName() != wendysItems.get(i).getItemName()
								&& this.restaurantChoices.get(city).getPrice() > wendysItems.get(i).getPrice()){
							this.restaurantChoices.replace(city, wendysItems.get(i));
							this.restaurantNames.replace(city, restaurants);
							break;
						}
						i++;
					}
				}
			}
			this.foodTracker.push(restaurantChoices.get(city));
		}
	}
	
	/**
	 * Get the total cost of the meal choices and prints out each meal choice, and restaurant name at each city.
	 * 
	 * @return Total cost of all meal choices.
	 */
	public double getTotalCost(){
		double costCounter = 0;
		System.out.print("Begin with ");
		System.out.print(sequencePath.get(0));
		System.out.println(", visit no restaurant here.");
		for(String city : sequencePath) {
			if(city != sequencePath.get(0)) {
				System.out.println("*******************************************************************************");
				System.out.print(city);
				System.out.println(" restaurant choice and meal choice:");
				System.out.print("Restaurant Choice: ");
				System.out.println(restaurantNames.get(city));
				System.out.print("Choice of meal: ");
				System.out.println(restaurantChoices.get(city).getItemName());
				System.out.print("Cost of meal: $ ");
				System.out.println(restaurantChoices.get(city).getPrice());
				System.out.println("*******************************************************************************");
				costCounter += restaurantChoices.get(city).getPrice();
			}
		}
		return costCounter;
	}
	
}
