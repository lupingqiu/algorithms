package main.java.algorithms;
/**
 * 矩阵相乘
 * @author qiulp
 *
 */
public class MatrixMultiply {
	
	static void printMatrix(double[][] a){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[i].length;j++){
				System.out.print(a[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * 构建随机矩阵
	 * @param i
	 * @param j
	 * @return
	 */
	static double[][] buildMatrix(int i,int j){
		double[][] m = new double[i][j];
		for(int p=0;p<m.length;p++){
			for(int q=0;q<m[p].length;q++){
				m[p][q]=(int)(Math.random()*10);
			}
		}
		return m;
	}

	/**
	 * 暴力法n^3
	 * @param a
	 * @param b
	 * @return
	 */
	static double[][] bruteforce(double[][] a,double[][] b){
		if(a[0].length!=b.length){
			System.out.println("error");
			return null;
		}
		double[][] c = new double[a.length][b[0].length];
		for(int i=0;i<a.length;i++){
			for(int j=0;j<b[0].length;j++){
				for(int k=0;k<a[i].length;k++){
					c[i][j] +=a[i][k]*b[k][j];
				}
			}
		}
		return c;
	}
	/**
	 * 矩阵相加
	 * @param a
	 * @param b
	 * @return
	 */
	static double[][] plusMatrix(double[][] a,double[][] b){
		double[][] c=new double[a.length][a[0].length];
		if(a.length!=b.length || a[0].length!=b[0].length){
			System.out.println("error");
			return null;
		}else{
			for(int i=0;i<a.length;i++){
				for(int j=0;j<a[0].length;j++){
					c[i][j]=a[i][j]+b[i][j];
				}
			}
			return c;
		}
	}
	/**
	 * 矩阵相减
	 * @param a
	 * @param b
	 * @return
	 */
	static double[][] minusMatrix(double[][] a,double[][] b){
		double[][] c=new double[a.length][a[0].length];
		if(a.length!=b.length || a[0].length!=b[0].length){
			System.out.println("error");
			return null;
		}else{
			for(int i=0;i<a.length;i++){
				for(int j=0;j<a[0].length;j++){
					c[i][j]=a[i][j]-b[i][j];
				}
			}
			return c;
		}
	}
	/**
	 * 分治法
	 * @return
	 */
	static double[][] matrixMultiplyRecursive(double[][] m,double[][] n,int arows,int arowe,int acols,int acole,int brows,int browe,int bcols,int bcole){
	
		double[][] c=new double[arowe-arows+1][bcole-bcols+1];
		if(arowe==arows){//a只有一行情况
			for(int i=bcols;i<=bcole;i++){
				for(int j=brows;j<=browe;j++){
					c[0][i-bcols]+=m[arows][acols+j-brows]*n[j][i];
				}
			}
			return c;
		}
		if(acole==acols){//a只有一列情况，brows=browe b只有一行
			for(int i=arows;i<=arowe;i++){
				for(int j=bcols;j<=bcole;j++){
					c[i-arows][j-bcols]=m[i][acole]*n[browe][j];
				}
			}
			return c;
		}
		if(bcole==bcols){//b只有一列情况
			for(int i=arows;i<=arowe;i++){
				for(int j=acols;j<=acole;j++){
					c[i-arows][0]+=m[i][j]*n[brows+j-acols][bcols];
				}
			}
			return c;
		}
		
		int arowmid=(int)Math.floor((arowe+arows)/2);//a行
		int acolmid=(int)Math.floor((acole+acols)/2);//a列
		int browmid = (int)Math.floor((browe+brows)/2);//b行
		int bcolmid=(int)Math.floor((bcole+bcols)/2);//b列
		double[][] c11=plusMatrix(matrixMultiplyRecursive(m,n,arows,arowmid,acols,acolmid,brows,browmid,bcols,bcolmid)
				                  ,matrixMultiplyRecursive(m,n,arows,arowmid,acolmid+1,acole,browmid+1,browe,bcols,bcolmid));
		
		double[][] c12=plusMatrix(matrixMultiplyRecursive(m,n,arows,arowmid,acols,acolmid,brows,browmid,bcolmid+1,bcole)
				                  ,matrixMultiplyRecursive(m,n,arows,arowmid,acolmid+1,acole,browmid+1,browe,bcolmid+1,bcole));
		
		double[][] c21=plusMatrix(matrixMultiplyRecursive(m,n,arowmid+1,arowe,acols,acolmid,brows,browmid,bcols,bcolmid)
				                  ,matrixMultiplyRecursive(m,n,arowmid+1,arowe,acolmid+1,acole,browmid+1,browe,bcols,bcolmid));
		
		double[][] c22=plusMatrix(matrixMultiplyRecursive(m,n,arowmid+1,arowe,acols,acolmid,brows,browmid,bcolmid+1,bcole)
				                  ,matrixMultiplyRecursive(m,n,arowmid+1,arowe,acolmid+1,acole,browmid+1,browe,bcolmid+1,bcole));
		//举证赋值，未找到合适的方法
		for(int i=0;i<c11.length;i++){
			for(int j=0;j<c11[0].length;j++){
				c[i][j]=c11[i][j];
			}
		}
		for(int i=0;i<c12.length;i++){
			for(int j=0;j<c12[0].length;j++){
				c[i][c11[0].length+j]=c12[i][j];
			}
		}
		for(int i=0;i<c21.length;i++){
			for(int j=0;j<c21[0].length;j++){
				c[c11.length+i][j]=c21[i][j];
			}
		}
		for(int i=0;i<c22.length;i++){
			for(int j=0;j<c22[0].length;j++){
				c[c11.length+i][c11[0].length+j]=c22[i][j];
			}
		}
		return c;
	}
	
	static int getN(double[][] a,double[][] b){
		int amax = Math.max(a.length, a[0].length);
		int bmax = Math.max(b.length, b[0].length);
		int max = Math.max(amax, bmax);
		return (int)Math.pow(2, Math.ceil(Math.log((double)max)/Math.log((double)2)));
	}
	
	static double[][] invertToNN(double[][] a,int n){
		int rlen=a.length;
		int clen=a[0].length;
		double[][] nn=new double[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i<rlen && j<clen){
					nn[i][j] = a[i][j];
				}else{
					nn[i][j] = 0;
				}
			}
		}
		return nn;
	}
	/**
	 * Strassen方法
	 * @param m
	 * @param n
	 * @param rows行起始位置
	 * @param rowe行结束位置
	 * @param cols列起始位置
	 * @param cole列结束位置
	 * @param rowlength行补零起始位置
	 * @param collength列补零起始位置
	 * @return
	 */
	static double[][] strassens(Matrix a,Matrix b){
		double[][] c=new double[a.getN()][a.getN()];
		if(a.getN()==1){
			c[0][0]=a.getA()[a.getRows()][a.getCols()]*b.getA()[b.getRows()][b.getCols()];
			return c;
		}
		Matrix a11 = new Matrix(a.getA(),a.getRows(),(a.getRows()+a.getRowe()+1)/2-1,a.getCols(),(a.getCols()+a.getCole()+1)/2-1,a.getRealrowlength(),a.getRealcollength());
		Matrix a12 = new Matrix(a.getA(),a.getRows(),(a.getRows()+a.getRowe()+1)/2-1,(a.getCols()+a.getCole()+1)/2,a.getCole(),a.getRealrowlength(),a.getRealcollength());
		Matrix a21 = new Matrix(a.getA(),(a.getRows()+a.getRowe()+1)/2,a.getRowe(),a.getCols(),(a.getCols()+a.getCole()+1)/2-1,a.getRealrowlength(),a.getRealcollength());
		Matrix a22 = new Matrix(a.getA(),(a.getRows()+a.getRowe()+1)/2,a.getRowe(),(a.getCols()+a.getCole()+1)/2,a.getCole(),a.getRealrowlength(),a.getRealcollength());
		
		Matrix b11 = new Matrix(b.getA(),b.getRows(),(b.getRows()+b.getRowe()+1)/2-1,b.getCols(),(b.getCols()+b.getCole()+1)/2-1,b.getRealrowlength(),b.getRealcollength());
		Matrix b12 = new Matrix(b.getA(),b.getRows(),(b.getRows()+b.getRowe()+1)/2-1,(b.getCols()+b.getCole()+1)/2,b.getCole(),b.getRealrowlength(),b.getRealcollength());
		Matrix b21 = new Matrix(b.getA(),(b.getRows()+b.getRowe()+1)/2,b.getRowe(),b.getCols(),(b.getCols()+b.getCole()+1)/2-1,b.getRealrowlength(),b.getRealcollength());
		Matrix b22 = new Matrix(b.getA(),(b.getRows()+b.getRowe()+1)/2,b.getRowe(),(b.getCols()+b.getCole()+1)/2,b.getCole(),b.getRealrowlength(),b.getRealcollength());
		double[][] p1=MatrixMultiply.minusMatrix(strassens(a11, b12),strassens(a11,b22));
		double[][] p2=MatrixMultiply.plusMatrix(strassens(a11, b22),strassens(a12,b22));
		double[][] p3=MatrixMultiply.plusMatrix(strassens(a21, b11),strassens(a22,b11));
		double[][] p4=MatrixMultiply.minusMatrix(strassens(a22, b21),strassens(a22,b11));
		double[][] p5=MatrixMultiply.plusMatrix(MatrixMultiply.plusMatrix(strassens(a11, b11),strassens(a11,b22)),
					  MatrixMultiply.plusMatrix(strassens(a22, b11),strassens(a22,b22)));
		double[][] p6=MatrixMultiply.minusMatrix(MatrixMultiply.plusMatrix(strassens(a12, b21),strassens(a12,b22)),
				      MatrixMultiply.plusMatrix(strassens(a22, b21),strassens(a22,b22)));
		double[][] p7=MatrixMultiply.minusMatrix(MatrixMultiply.plusMatrix(strassens(a11, b11),strassens(a11,b12)),
			          MatrixMultiply.plusMatrix(strassens(a21, b11),strassens(a21,b12)));
		double[][] c11=MatrixMultiply.minusMatrix(MatrixMultiply.plusMatrix(p5,p4),MatrixMultiply.minusMatrix(p2, p6));
		double[][] c12=MatrixMultiply.plusMatrix(p1,p2);
		double[][] c21=MatrixMultiply.plusMatrix(p3,p4);
		double[][] c22=MatrixMultiply.minusMatrix(MatrixMultiply.plusMatrix(p5,p1),MatrixMultiply.plusMatrix(p3, p7));
		//举证赋值，未找到合适的方法
		for(int i=0;i<c11.length;i++){
			for(int j=0;j<c11[0].length;j++){
				c[i][j]=c11[i][j];
			}
		}
		for(int i=0;i<c12.length;i++){
			for(int j=0;j<c12[0].length;j++){
				c[i][c11[0].length+j]=c12[i][j];
			}
		}
		for(int i=0;i<c21.length;i++){
			for(int j=0;j<c21[0].length;j++){
				c[c11.length+i][j]=c21[i][j];
			}
		}
		for(int i=0;i<c22.length;i++){
			for(int j=0;j<c22[0].length;j++){
				c[c11.length+i][c11[0].length+j]=c22[i][j];
			}
		}
		return c;
	}
	
}
class Matrix{
	private double[][] a;
	private int rows;
	private int rowe;
	private int cols;
	private int cole;
	private int realrowlength;
	private int realcollength;
	private int n;
	
	Matrix(double[][] a,int rows,int rowe,int cols,int cole,int rowlength,int collength){
		this.a = a;
		this.rows=rows;
		this.rowe=rowe;
		this.cols=cols;
		this.cole=cole;
		this.realrowlength=rowlength;
		this.realcollength=collength;
	}

	public int getN() {
		return this.rowe - this.rows + 1;
	}

	public void setN(int n) {
		this.n = n;
	}

	public double[][] getA() {
		return a;
	}

	public void setA(double[][] a) {
		this.a = a;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getRowe() {
		return rowe;
	}

	public void setRowe(int rowe) {
		this.rowe = rowe;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getCole() {
		return cole;
	}

	public void setCole(int cole) {
		this.cole = cole;
	}

	public int getRealrowlength() {
		return realrowlength;
	}

	public void setRealrowlength(int realrowlength) {
		this.realrowlength = realrowlength;
	}

	public int getRealcollength() {
		return realcollength;
	}

	public void setRealcollength(int realcollength) {
		this.realcollength = realcollength;
	}
	
}
