package Arrays.SlidingWindow;

import java.util.HashMap;

public class DistinctSubArray {
	public static long maximumSubarraySum(int[] nums, int k) {
        int left=0;
        long sum=0;
        long maxSum=0;
        HashMap<Integer,Integer> map=new HashMap<>();

        for(int right=0; right<nums.length; right++) {
            sum += nums[right];
            map.put(nums[right], map.getOrDefault(nums[right], 0)+1);

            if(right-left+1 > k) {
                sum-=nums[left];
                map.put(nums[left], map.get(nums[left])-1);

                if(map.get(nums[left])==0) {
                    map.remove(nums[left]);
                }
                left++;
            }

            if(right-left+1==k && map.size()==k) {
                maxSum=Math.max(maxSum,sum);
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums={1,5,4,2,9,9,9};
        int k=3;

        System.out.println(maximumSubarraySum(nums,k));
    }
}
