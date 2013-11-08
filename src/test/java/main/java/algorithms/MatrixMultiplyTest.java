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
	
	public void testStrassens(){
		a=MatrixMultiply.buildMatrix(512, 512);
		b=MatrixMultiply.buildMatrix(512, 512);
		int n = MatrixMultiply.getN(a, b);
		double[][] an=MatrixMultiply.invertToNN(a, n);
		double[][] bn=MatrixMultiply.invertToNN(b, n);
		Matrix am = new Matrix(an,0,an.length-1,0,an[0].length-1,a.length,a[0].length);
		Matrix bm = new Matrix(bn,0,bn.length-1,0,bn[0].length-1,b.length,b[0].length);
		long t1=System.currentTimeMillis();
		double[][] cc = MatrixMultiply.strassens(am, bm);
		long t2=System.currentTimeMillis();
		double[][] c = MatrixMultiply.bruteforce(a, b);
		long t3=System.currentTimeMillis();
		MatrixMultiply.printMatrix(an);
		MatrixMultiply.printMatrix(bn);
		MatrixMultiply.printMatrix(c);
		MatrixMultiply.printMatrix(cc);
		System.out.println(t2-t1);
		System.out.println(t3-t2);
	}
}
