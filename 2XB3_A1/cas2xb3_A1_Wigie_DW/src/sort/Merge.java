package sort;

public class Merge {
	
	//This code was taken from Lab Walkthrough 7
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	private static Comparable[] aux;
	
	//This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne
	//Taken from page 271
	private static void merge(Comparable[] x, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;
		for(int k = lo; k <= hi; k++) {
			aux[k] = x[k];
		}
		for(int k = lo; k <= hi; k++) {
			if (i > mid) {
				x[k] = aux[j++];
			}
			else if (j > hi) {
				x[k] = aux[i++];
				
			}
			else if(less(aux[j], aux[i])){
				x[k] = aux[j++];
			}
			else {
				x[k] = aux[i++];
			}
		}
	}
	
	//This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne
	//Taken from page 273
	private static void sortMergeTD(Comparable[] x, int lo, int hi) {
		if(hi <= lo)
			return;
		int mid = lo + (hi - lo)/2;
		sortMergeTD(x, lo, mid);
		sortMergeTD(x, mid + 1, hi);
		merge(x, lo, mid, hi);
	}
	
	/**
	 * Top-down merge sort using Comparable.
	 * This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne.
	 * Taken from page 273.
	 * Merge was taken from page 271.
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeTD (Comparable[] x, int n ) {
		aux = new Comparable[n];
		sortMergeTD(x, 0, n - 1);
	}
	
	/**
	 * Bottom-up merge sort using Comparable.
	 * This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne.
	 * Taken from page 278.
	 * Merge was taken from page 271.
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeBU (Comparable[] x, int n) {
		aux = new Comparable[n];
		for(int sz = 1; sz < n; sz = sz+sz) {
			for(int lo = 0; lo < n - sz; lo += sz+sz) {
				merge(x, lo, lo+sz-1, Math.min(lo+sz+sz-1,  n-1));
			}
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
}
