package main.java.algorithms;

/**
 * 最大子数组
 * @author qiulp
 *
 */
public class MaxSubarrayBruteForce {

	/**
	 * 暴力法n^2
	 * @param a
	 * @return
	 */
	static int[] bruteForce(int[] a){
		if(a.length == 1){
			return new int[]{0,0,a[0]};
		}
		if(a.length == 0){
			return null;
		}
		int max_sum = 0;
		int max_left = 0;
		int max_right = 0;
		for(int i=0;i<a.length;i++){
			int sum = a[i];
			for(int j=i+1;j<a.length;j++){
				sum += a[j];
				if(sum > max_sum){
					max_sum = sum;
					max_left = i;
					max_right = j;
				}
			}
		}
		return new int[]{max_left,max_right,max_sum};
	}
	
}
