package main.java.algorithms;

/**
 * 最大子数组
 * @author qiulp
 *
 */
public class MaxSubarrayLinearTime {

	/**
	 * n^2
	 * @param a
	 * @return
	 */
	static int[] notlinearTime(int[] a){
		if(a.length == 1){
			return new int[]{0,0,a[0]};
		}
		if(a.length == 0){
			return null;
		}
		int[] max_array = new int[]{0,0,a[0]};
		for(int i=1;i<a.length;i++){
			int max_sum = 0;
			int sum =0;
			int left = -1;
			for(int j=i;j>0;j--){
				sum+=a[j];
				if(sum>max_sum){
					max_sum=sum;
					left = j;
				}
			}
			if(max_sum>max_array[2]){
				max_array = new int[]{left,i,max_sum};
			}
		}
		return max_array;
	}
	
	/**
	 * 最优线性算法
	 * n
	 * @param a
	 * @return
	 */
	static int[] linearTime(int[] a){
		if(a.length == 1){
			return new int[]{0,0,a[0]};
		}
		if(a.length == 0){
			return null;
		}
		int[] max_array = new int[]{0,0,a[0]};
		int left = -1;
		int max_tmp = a[0];
		for(int i=1;i<a.length;i++){
			max_tmp+=a[i];
			if(max_tmp<0){
				max_tmp=0;
				left = i;
			}else if(max_tmp>max_array[2]){
				max_array = new int[]{left+1,i,max_tmp};
			}
			
		}
		return max_array;
	}
}
