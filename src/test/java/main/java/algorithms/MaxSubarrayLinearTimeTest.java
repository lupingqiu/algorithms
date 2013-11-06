package main.java.algorithms;

import junit.framework.TestCase;

public class MaxSubarrayLinearTimeTest extends TestCase {

	public void testlinear(){
		int[] a = new int[]{13,-3,-25,20,-3,-16,-23,18,20,-7,12,1,-5,-22,15,-4,7};
		int[] b= MaxSubarrayLinearTime.notlinearTime(a);
		int[] c= MaxSubarrayLinearTime.linearTime(a);
		System.out.println(c[0]+" "+c[1]+" "+c[2]);
	}
}
