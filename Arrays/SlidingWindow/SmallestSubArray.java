package Arrays.SlidingWindow;

public class SmallestSubArray {
	
	public static void main(String[] args) {
		 
		int target = 9;

	    int arr[] = {2,3,1,2,4,3};

	    int result = smallestSubarray(target, arr);

	    System.out.println(result);
	}
	
	static int smallestSubarray(int target, int arr[]) {
		
		int left = 0;
		int sum = 0;
		int minLen = Integer.MAX_VALUE;
		
		for(int right=0; right<arr.length; right++) {
			
			sum += arr[right];
			
			while(sum >= target) {
				
				minLen = Math.min(minLen, right-left+1);
				
				sum -= arr[left];
				
				left++;
			}
		}
		
		if(minLen == Integer.MAX_VALUE)
			return 0;
		
		return minLen;
	}
}
