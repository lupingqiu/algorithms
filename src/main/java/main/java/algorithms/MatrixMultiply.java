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
	 * 暴力发n^3
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
	 * 分治法
	 * @return
	 */
	static double[][] matrixMultiplyRecursive(double[][] m,double[][] n,int arows,int arowe,int acols,int acole,int brows,int browe,int bcols,int bcole){
	
		double[][] c=new double[arowe-arows+1][bcole-bcols+1];
		if(arowe==arows){
			for(int i=bcols;i<=bcole;i++){
				for(int j=brows;j<=browe;j++){
					c[0][i-bcols]+=m[arows][acols+j-brows]*n[j][i];
				}
			}
			return c;
		}
		if(acole==acols){//brows=browe
			for(int i=arows;i<=arowe;i++){
				for(int j=bcols;j<=bcole;j++){
					c[i-arows][j-bcols]=m[i][acole]*n[browe][j];
				}
			}
			return c;
		}
		if(bcole==bcols){
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
