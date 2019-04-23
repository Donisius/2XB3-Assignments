package sort;

public class Quick {
	
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
	
	//This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne
	//Taken from page 291
	private static int partition(Product[] x, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Product v = x[lo];
		while(true) {
			while(x[++i].getAmount() < v.getAmount()) {
				if(i == hi)
					break;
			}
			while(v.getAmount() < x[--j].getAmount()) {
				if(j == lo)
					break;
			}
			if(i >= j)
				break;
			Product t = x[i];
			x[i] = x[j];
			x[j] = t;
		}
		Product t = x[lo];
		x[lo] = x[j];
		x[j] = t;
		return j;
	}
	
	//This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne
	//Taken from page 289
	private static void sortBasicQuick(Product[] x, int lo, int hi) {
		if(hi <= lo) return;
		int j = partition(x, lo, hi);
		sortBasicQuick(x, lo, j - 1);
		sortBasicQuick(x, j+1, hi);
		
	}
	
	/**
	 * Basic quick sort.
	 * This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne.
	 * Taken from page 289.
	 * Partition was taken from page 291.
	 * @param x - the input array containing products that need to be sorted.
	 */
	public static void sortBasicQuick (  Product[] x ) {
		sortBasicQuick(x, 0, x.length - 1);
	}
	
	//This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne
	//Taken from page 299
	private static void sortThreePartition( Comparable[] x, int lo, int hi) {
		if(hi <= lo)
			return;
		int lt = lo, i = lo+1, gt = hi;
		Comparable v = x[lo];
		while(i <= gt) {
			int cmp = x[i].compareTo(v);
			if (cmp < 0) 
				exch(x, lt++, i++);
			else if (cmp > 0) 
				exch(x, i, gt--);
			else
				i++;
		}
		sortThreePartition(x, lo, lt - 1);
		sortThreePartition(x, gt + 1, hi);
	}
	
	/**
	 * Three partition quick sort using Comparable.
	 * This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne.
	 * Taken from page 299.
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortThreePartition ( Comparable[] x, int n ) {
		sortThreePartition(x, 0, n - 1);
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
