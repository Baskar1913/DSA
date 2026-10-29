package Arrays.SlidingWindow;

import java.util.Arrays;

public class MostFreqElement {
	public static int maxFrequency(int[] nums, int k) {
        
		Arrays.sort(nums);

        int left=0;
        long sum=0;
        int maxFreq=0;

        for(int right=0; right<nums.length; right++) {
            sum += nums[right];

            long temp = (long) nums[right]*(right-left+1)-sum;

            while(temp>k) {
                sum -= nums[left];
                left++;

                temp = (long) nums[right]*(right-left+1)-sum;
            }

            maxFreq = Math.max(maxFreq, right-left+1);
        }

        return maxFreq;
    }
	
	public static void main(String[] args) {

        int[] nums={1,2,4};
        int k=5;

        System.out.println(maxFrequency(nums,k));
    }
}
