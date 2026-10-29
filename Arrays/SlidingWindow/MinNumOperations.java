package Arrays.SlidingWindow;

import java.util.Arrays;

public class MinNumOperations {
	
	public static int minOperations(int[] nums) {
        
		int n=nums.length;
        Arrays.sort(nums);

        int[] unique = new int[n];
        int size=0;

        for(int i=0; i<n; i++) {
            if(i==0 || nums[i] != nums[i-1]) {
                unique[size]=nums[i];
                size++;
            }
        }

        int left=0;
        int maxCount=0;

        for(int right=0; right<size; right++) {
            while(unique[right]-unique[left] >= n) {
                left++;
            }

            maxCount = Math.max(maxCount, right-left+1);
        }

        return n-maxCount;
    }
	
	public static void main(String[] args) {
 
        int[] nums={1,2,3,5,6};

        System.out.println(minOperations(nums));
    }
}
