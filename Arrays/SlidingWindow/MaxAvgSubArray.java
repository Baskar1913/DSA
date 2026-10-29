package Arrays.SlidingWindow;

public class MaxAvgSubArray {
	public static double findMaxAverage(int[] nums, int k) {
		double sum=0;
		
		for(int i=0; i<k; i++) {
			sum+=nums[i];
		}
		
		double maxAvg = sum/k;
		
		for(int i=k; i<nums.length; i++) {
			sum = sum - nums[i-k] + nums[i];
			
			double avg = sum/k;
			
			if(avg>maxAvg) {
				maxAvg=avg;
			}
		}
		return maxAvg;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;

        double result = findMaxAverage(nums, k);

        System.out.println("Maximum Average: " + result);
	}

}
