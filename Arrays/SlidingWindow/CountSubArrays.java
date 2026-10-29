package Arrays.SlidingWindow;

public class CountSubArrays {
	
	public static long countSubarrays(int[] nums, int k) {
        
		int max=0;

        for(int num : nums) {
            max=Math.max(max,num);
        }

        int left=0;
        int count=0;
        long result=0;

        for(int right=0; right<nums.length; right++) {
            if(nums[right]==max) {
                count++;
            }

            while(count>=k) {
                result += nums.length-right;

                if(nums[left]==max) {
                    count--;
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums={1,3,2,3,3};
        int k=2;

        System.out.println(countSubarrays(nums,k));
    }
}
