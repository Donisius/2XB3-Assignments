package Implementation;

/**
 * Class to represent a food item.
 * 
 * @author Donisius Wigie MacID: Wigied
 *
 */
public class FoodItem implements Comparable<FoodItem>{

	private final String itemName;
	private final double price;
	
	/**
	 * Constructor for the food item class.
	 * 
	 * @param itemName Name of the food item.
	 * @param price Price of the food item.
	 */
	public FoodItem(String itemName, double price) {
		this.itemName = itemName;
		this.price = price;
	}
	
	/**
	 * Getter for the food item name.
	 * 
	 * @return The name of the food item.
	 */
	public String getItemName() {
		return this.itemName;
	}
	
	/**
	 * Getter for the price of the food item.
	 * 
	 * @return The price of the food item.
	 */
	public double getPrice() {
		return this.price;
	}

	public int compareTo(FoodItem that) {
		if(this.price < that.getPrice()) return -1;
		if(this.price > that.getPrice()) return 1;
		return 0;
	}
}
