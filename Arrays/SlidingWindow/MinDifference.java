package Arrays.SlidingWindow;

import java.util.Arrays;

public class MinDifference {
	
	public static int minDiff(int[] nums,int k) {
        Arrays.sort(nums);

        int min=Integer.MAX_VALUE;

        for(int i=0; i<=nums.length-k; i++) {
            int temp = nums[i+k-1]-nums[i];
            min = Math.min(min, temp);
        }

        return min;
    }

    public static void main(String[] args) {

        int[] nums={9,4,1,7};
        int k=2;

        System.out.println(minDiff(nums, k));
    }
}
