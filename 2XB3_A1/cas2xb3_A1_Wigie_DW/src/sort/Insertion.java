package sort;

public class Insertion {
	
	//This code was taken from Lab Walkthrough 7
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	//This code was taken from Lab Walkthrough 7
	private static void exch(Comparable[] x, int i, int j) {
		Comparable t = x[i];
		x[i] = x[j];
		x[j] = t;
	}
	
	//This code inspired by code from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne.
	//Taken from page 380.
	//Modified to make it so that it returns the best place to place an element.
	//Only change was to make base case "if(lo == hi)" instead of "if(hi < lo)" and make the
	//upper bound if amount is less that x[mid] to just be mid instead of mid-1..
	private static int binSearch(Comparable[] x, Comparable amount, int lo, int hi) {
		if (lo == hi)
			return lo;
		int mid = lo + ((hi - lo) / 2);
		if (less(amount, x[mid]))
			return binSearch(x, amount, lo, mid);
		else if(less(x[mid], amount))
			return binSearch(x, amount, mid + 1, hi);
		return mid;
	}
	
	/**
	 * Regular insertion sort.
	 * This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne.
	 * Taken from page 251.
	 * @param x - the input array containing products that need to be sorted.
	 */
	public static void sortInsert( Product[] x ) {
		int n = x.length;
		for(int i = 1; i < n; i++) {
			for(int j = i; j >0 && x[j].getAmount() < x[j-1].getAmount(); j--) {
				Product t = x[j];
				x[j] = x[j-1];
				x[j - 1] = t;		
			}
		}
	}
	
	
	/**
	 * Insertion sort using Comparable.
	 * This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne.
	 * Taken from page 251.
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortComparable ( Comparable[] x, int n ) {
		for(int i = 1; i < n; i++) {
			for(int j = i; j > 0 && less(x[j], x[j-1]); j--) {
				exch(x, j, j-1);
			}
		}
	}
	
	/**
	 * Optimized insertion sort.
	 * Binary search used from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne.
	 * Taken from page 380.
	 * Minor changes to make the binary search return best spot instead of finding element.
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortBinary ( Comparable[] x, int n ) {
		int i = 0;
		while(i < n) {
			int place = binSearch(x, x[i], 0, i);
			Comparable temp = x[i];
			int j = i - 1;
			while(j >= place) {
				x[j + 1] = x[j];
				j--;
			}
			x[place] = temp;
			i++;
		}
	}
	
	/**
	 * A testing method to check if an array is sorted.
	 * This code was taken from Lab Walkthrough 7.
	 * @param x - the input array containing jobs to check if array is sorted.
	 */
	public static boolean isSorted(Comparable[] x) {
		for(int i = 1; i < x.length; i++) {
			if(less(x[i], x[i-1]))
				return false;
		}
		return true;
	}
	
	/**
	 * A testing method to check if an array of Products is sorted.
	 * @param x - the input array containing Products to check if array is sorted.
	 */
	public static boolean isSortedBasic(Product[] x) {
		for(int i = 1; i < x.length; i++) {
			if(x[i].getAmount() < x[i-1].getAmount())
				return false;
		}
		return true;
	}
}
