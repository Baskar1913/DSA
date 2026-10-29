package Arrays.TwoPointers;

public class TwoSum {

	public static void main(String[] args) {
		
		int[] arr = {1,2,13,24,26,38,40};
		
		int target = 51;
		
		int left = 0;
		int right = arr.length-1;
		
		while(left < right) {
			
			int sum = arr[left] + arr[right];
			
			if(sum == target) {
				System.out.println("Found Indices: "+left+" "+right);
				return;
			}
			else if(sum < target) {
				left++;
			}
			else if(sum > target) {
				right--;
			}
		}

	}

}
