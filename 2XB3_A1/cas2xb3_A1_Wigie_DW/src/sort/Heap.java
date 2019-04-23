package sort;

public class Heap {
	 
	//This code was taken from Lab Walkthrough 7
	private static boolean less(Comparable[] x, int i, int j) {
		return x[i - 1].compareTo(x[j - 1]) < 0;
	}
	
	//This code was taken from Lab Walkthrough 7
	private static void exch(Comparable[] x, int i, int j) {
		Comparable t = x[i-1];
		x[i-1] = x[j-1];
		x[j-1] = t;
	}
	
	//This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne
	//Taken from page 316
	private static void sink(Comparable[] x, int i, int n) {
        while (2*i <= n) {
            int j = 2*i;
            if (j < n && less(x, j, j+1))
            	j++;
            if (!less(x, i, j)) 
            	break;
            exch(x, i, j);
            i = j;
        }
    }
	/**
	 * Heap sort using Comparable.
	 * This code was taken from Algorithms, 4th Edition By Robert Sedgewick and Kevin Wayne.
	 * Taken from page 324.
	 * Sink method was taken from page 316.
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortHeap ( Comparable[] x, int n ) {
		int len = n;
		for (int i = len/2; i >= 1; i--) {
			sink(x, i, len);
		}
		while(len > 1) {
			exch(x, 1, len--);
			sink(x, 1, len);	
		}
	}
	
	/**
	 * A testing method to check if an array is sorted.
	 * This code was taken from Lab Walkthrough 7.
	 * @param x - the input array containing jobs to check if array is sorted.
	 */
	public static boolean isSorted(Comparable[] x) {
		for(int i = 1; i < x.length; i++) {
			if(less(x, i+1, i))
				return false;
		}
		return true;
	}
}
