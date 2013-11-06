package main.java.algorithms;

/**
 * 最大子数组
 * @author qiulp
 *
 */
public class MaxSubarrayDivideAndConquer {

	/**
	 * 分而治之法n^2
	 * @param a
	 * @return
	 */
	static int[] devideAndComquer(int[] a,int left,int right){
		if(left == right){
			return new int[]{left,right,a[left]};
		}
		int mid =(left+right)/2;
		int[] max_left = devideAndComquer(a,left,mid);
		int[] max_right = devideAndComquer(a,mid+1,right);
		int[] max_mid = findMaxSubarray(a,left,mid,right);
		if (max_left[2]>=max_right[2] && max_left[2]>=max_mid[2]) return max_left;
		if (max_right[2]>=max_left[2] && max_right[2]>=max_mid[2]) return max_right;
		if (max_mid[2]>=max_left[2] && max_mid[2]>=max_right[2]) return max_mid;
		return null;
	}
	
	static int[] findMaxSubarray(int[] a,int left,int mid,int right){
		int max_left = -1;
		int max_right = -1;
		int max_left_sum = 0;
		int max_right_sum = 0;
		int sum = 0;
		for (int i=mid;i>=left;i--){
			sum+=a[i];
			if(sum>max_left_sum){
				max_left_sum = sum;
				max_left = i;
			}
		}
		sum = 0;
		for(int j=mid+1;j<=right;j++){
			sum+=a[j];
			if(sum>max_right_sum){
				max_right_sum= sum;
				max_right = j;
			}
		}
		return new int[]{max_left,max_right,max_left_sum+max_right_sum};
	}
}
