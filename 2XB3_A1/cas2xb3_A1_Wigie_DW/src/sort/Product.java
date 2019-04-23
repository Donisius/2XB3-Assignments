package sort;

public class Product implements Comparable<Product>{
	
	private String productID;
	private int salesAmount;
	
	/**
	 * Create and initiate a Product.
	 * @param productID - the ID of the product.
	 * @param salesAmount - the sales amount of the product.
	 */
	public Product(String productID, int salesAmount) {
		this.productID = productID;
		this.salesAmount = salesAmount;
	}
	
	/**
	 * Create and initiate a Product.
	 */
	public Product() {
		this.productID = "";
		this.salesAmount = 0;
	}

	/**
	 * A getter for the product ID.
	 * @return productID of the Producct.
	 */
	public String getID() {
		return productID;
	}
	
	/**
	 * A setter for the product ID.
	 */
	public void setID(String ID) {
		this.productID = ID;
	}
	
	/**
	 * A getter for the product sales amount.
	 * @return salesAmount of the Product.
	 */
	public int getAmount() {
		return salesAmount;
	}
	
	/**
	 * A setter for the product sales amount.
	 */
	public void setAmount(int Amount) {
		this.salesAmount = Amount;
	}
	
	
	/**
	 * Create a String representation of the Product.
	 * @return String representation of the product.
	 */
	public String toString() {
		return "{" + "productID='" + productID + "\'" + 
				", salesAmount=" + salesAmount + "}";
	}
	
	@Override
	public int compareTo(Product j)
	{
		if(this.getAmount() > j.getAmount()) {
			return 1;
		}
		else if(j.getAmount() > this.getAmount()) {
			return -1;
		}
		else if(this.getID().compareTo(j.getID()) < 0) {
			return -1;
		}
		else if(this.getID().compareTo(j.getID()) > 0) {
			return 1;
		}
		return 0;
	}
}
