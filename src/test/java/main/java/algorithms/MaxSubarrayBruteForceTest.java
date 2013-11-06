package main.java.algorithms;

import junit.framework.TestCase;

public class MaxSubarrayBruteForceTest extends TestCase {

	public void testForce(){
		int[] a = new int[]{13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		int[] b= MaxSubarrayBruteForce.bruteForce(a);
		System.out.println(b[0]+" "+b[1]+" "+b[2]);
	}
}
