package Implementation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * A class for representing the menu for a particular restaurant.
 * 
 * @author Donisius Wigie MacID: Wigied
 *
 */
public class Menu {
	
	private ArrayList<FoodItem> menuItems;
	
	/**
	 * A constructor for the menu items. Sorts the food items provided in ascending order based on price.
	 * 
	 * @param items ArrayList of food items.
	 */
	public Menu(ArrayList<FoodItem> items) {
		Collections.sort(items, new Comparator<FoodItem>() {
			public int compare(FoodItem item1, FoodItem item2) {
				return Double.valueOf(item1.getPrice()).compareTo(item2.getPrice());
			}
		});
		
		menuItems = items;
	}
	
	/**
	 * Getter for the available food items available in a particular restaurant.
	 * 
	 * @return Iterable data type for the food items available in a restaurant in ascending order based on price.
	 */
	public Iterable<FoodItem> getMenuItems(){
		return this.menuItems;
	}
}
