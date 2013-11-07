package main.java.algorithms;

import junit.framework.TestCase;

public class MatrixMultiplyTest extends TestCase {

	static double[][] a=null;
	static double[][] b=null;
	static{
		a = new double[2][2];
		a[0][0]=1;
		a[0][1]=1;
		a[1][0]=1;
		a[1][1]=1;
		b = new double[2][2];
		b[0][0]=1;
		b[0][1]=1;
		b[1][0]=1;
		b[1][1]=1;
	}
	
	public void testForce(){
		System.out.println((1-0)/2);
		a=MatrixMultiply.buildMatrix(30, 20);
		b=MatrixMultiply.buildMatrix(20, 30);
		double[][] c = MatrixMultiply.bruteforce(a, b);
		MatrixMultiply.printMatrix(a);
		MatrixMultiply.printMatrix(b);
		MatrixMultiply.printMatrix(c);
		double[][] cc = MatrixMultiply.matrixMultiplyRecursive(a,b, 0, 29, 0, 19, 0, 19, 0, 29);
		MatrixMultiply.printMatrix(cc);
	}
}
