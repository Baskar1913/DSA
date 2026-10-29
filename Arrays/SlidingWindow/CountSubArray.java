package Arrays.SlidingWindow;

public class CountSubArray {
    public static long countSubArrays(int[] nums,long k) {
        int left=0;
        long sum=0,count=0;

        for(int right=0;right<nums.length;right++) {
            sum+=nums[right];

            while(sum*(right-left+1)>=k) {
                sum-=nums[left];
                left++;
            }

            count+=right-left+1;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums={2,1,4,3,5};
        long k=10;

        System.out.println(countSubArrays(nums,k));
    }
}
