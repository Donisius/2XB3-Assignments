package sort;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testMergeTD {

	//Instantiate a new class for each of the 5 input arrays given.
	Product[] products = new Product[16];
	Product[] products2 = new Product[64];
	Product[] products3 = new Product[256];
	Product[] products4 = new Product[1024];
	Product[] products5 = new Product[4096];
	Product[] products6 = new Product[16384];
	Product[] products7 = new Product[65536];
	@Before
	public void setUp() throws Exception {
		
		//Instantiate members of each array of products.
		for(int i = 0; i <products.length; i++) {
			products[i] = new Product();
		}
		for(int i = 0; i <products2.length; i++) {
			products2[i] = new Product();
		}
		for(int i = 0; i <products3.length; i++) {
			products3[i] = new Product();
		}
		for(int i = 0; i <products4.length; i++) {
			products4[i] = new Product();
		}
		for(int i = 0; i <products5.length; i++) {
			products5[i] = new Product();
		}
		for(int i = 0; i <products6.length; i++) {
			products6[i] = new Product();
		}
		for(int i = 0; i <products7.length; i++) {
			products7[i] = new Product();
		}
		
		File f = new File("data/a1_in.txt");
		Scanner scan = new Scanner(f);
		scan.useDelimiter("[,\n]");
		
		//Fill in 2^4 array
		int j = 0;
		for(int i = 0; i < 31; i++) {
			String text = scan.next();
			if(i%2 == 0) {
				text = text.substring(1);
				products[j].setID(text);
			}
			if(i%2 != 0) {
				text = text.substring(0, text.length() - 1);
				int Amount = Integer.parseInt(text);
				products[j].setAmount(Amount);
				j ++;
			}
		}
		String text = scan.next();
		text = text.substring(0, text.length() - 2);
		int Amount = Integer.parseInt(text);
		products[j].setAmount(Amount);
		
		//Fill in 2^6 array
		j = 0;
		for(int i = 0; i < 127; i++) {
			String text2 = scan.next();
			if(i%2 == 0) {
				text2 = text2.substring(1);
				products2[j].setID(text2);
			}
			if(i%2 != 0) {
				text2 = text2.substring(0, text2.length() - 1);
				int Amount2 = Integer.parseInt(text2);
				products2[j].setAmount(Amount2);
				j ++;
			}
		}
		
		text = scan.next();
		text = text.substring(0, text.length() - 2);
		Amount = Integer.parseInt(text);
		products2[j].setAmount(Amount);
		
		//Fill in 2^8 array
		j = 0;
		for(int i = 0; i < 511; i++) {
			String text3 = scan.next();
			if(i%2 == 0) {
				text3 = text3.substring(1);
				products3[j].setID(text3);
			}
			if(i%2 != 0) {
				text3 = text3.substring(0, text3.length() - 1);
				int Amount3 = Integer.parseInt(text3);
				products3[j].setAmount(Amount3);
				j ++;
			}
		}
		
		text = scan.next();
		text = text.substring(0, text.length() - 2);
		Amount = Integer.parseInt(text);
		products3[j].setAmount(Amount);
		
		//Fill in s^10 array
		j = 0;
		for(int i = 0; i < 2047; i++) {
			String text4 = scan.next();
			if(i%2 == 0) {
				text4 = text4.substring(1);
				products4[j].setID(text4);
			}
			if(i%2 != 0) {
				text4 = text4.substring(0, text4.length() - 1);
				int Amount4 = Integer.parseInt(text4);
				products4[j].setAmount(Amount4);
				j ++;
			}
		}
		
		text = scan.next();
		text = text.substring(0, text.length() - 2);
		Amount = Integer.parseInt(text);
		products4[j].setAmount(Amount);
		
		//Fill in 2^12 array
		j = 0;
		for(int i = 0; i < 8191; i++) {
			String text5 = scan.next();
			if(i%2 == 0) {
				text5 = text5.substring(1);
				products5[j].setID(text5);
			}
			if(i%2 != 0) {
				text5 = text5.substring(0, text5.length() - 1);
				int Amount5 = Integer.parseInt(text5);
				products5[j].setAmount(Amount5);
				j ++;
			}
		}
		
		text = scan.next();
		text = text.substring(0, text.length() - 2);
		Amount = Integer.parseInt(text);
		products5[j].setAmount(Amount);
		
		//Fill in 2^14 array
		j = 0;
		for(int i = 0; i < 32767; i++) {
			String text6 = scan.next();
			if(i%2 == 0) {
				text6 = text6.substring(1);
				products6[j].setID(text6);
			}
			if(i%2 != 0) {
				text6 = text6.substring(0, text6.length() - 1);
				int Amount6 = Integer.parseInt(text6);
				products6[j].setAmount(Amount6);
				j ++;
			}
		}
		
		text = scan.next();
		text = text.substring(0, text.length() - 2);
		Amount = Integer.parseInt(text);
		products6[j].setAmount(Amount);
		
		//Fill in 2^16 array
		j = 0;
		for(int i = 0; i < 131071; i++) {
			String text7 = scan.next();
			if(i%2 == 0) {
				text7 = text7.substring(1);
				products7[j].setID(text7);
			}
			if(i%2 != 0) {
				text7 = text7.substring(0, text7.length() - 1);
				int Amount7 = Integer.parseInt(text7);
				products7[j].setAmount(Amount7);
				j ++;
			}
		}
		
		scan.close();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Merge a = new Merge();
		
		System.out.println("Timings for Top Down Merge Sort (in microseconds): ");
		
		long startTime = System.nanoTime();
		a.sortMergeTD(products, products.length);
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		assertTrue(a.isSorted(products));
		
		startTime = System.nanoTime();
		a.sortMergeTD(products2, products2.length);
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		assertTrue(a.isSorted(products2));
		
		startTime = System.nanoTime();
		a.sortMergeTD(products3, products3.length);
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		assertTrue(a.isSorted(products3));
		
		startTime = System.nanoTime();
		a.sortMergeTD(products4, products4.length);
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		assertTrue(a.isSorted(products4));
		
		startTime = System.nanoTime();
		a.sortMergeTD(products5, products5.length);
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		assertTrue(a.isSorted(products5));
		
		startTime = System.nanoTime();
		a.sortMergeTD(products6, products6.length);
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		assertTrue(a.isSorted(products6));
		
		startTime = System.nanoTime();
		a.sortMergeTD(products7, products7.length);
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		assertTrue(a.isSorted(products7));
	}

}