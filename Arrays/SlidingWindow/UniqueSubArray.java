package Arrays.SlidingWindow;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class UniqueSubArray {
	
	public static long maxSum(List<Integer> nums, int m, int k) {
        int left=0;
        long sum=0;
        long maxSum=0;
        
        HashMap<Integer,Integer> map=new HashMap<>();

        for(int right=0; right<nums.size(); right++) {
            int num = nums.get(right);
            sum += num;
            map.put(num, map.getOrDefault(num, 0)+1);

            if(right-left+1 > k) {
                int removed = nums.get(left);
                sum -= removed;
                map.put(removed, map.get(removed)-1);

                if(map.get(removed)==0) {
                    map.remove(removed);
                }
                left++;
            }

            if(right-left+1==k && map.size()>=m) {
                maxSum = Math.max(maxSum,sum);
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
    	
        List<Integer> nums=Arrays.asList(2,6,7,3,1,7);
        int m=3,k=4;

        System.out.println(maxSum(nums,m,k));
    }
}
