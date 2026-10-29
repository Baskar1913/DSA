package Arrays.PrefixSum;

public class RangeSum {
	public static void main(String[] args) {
		
		int[] arr = {2,4,1,5,3};
		
		int n = arr.length;
		
		int[] prefix = new int[n];
		
		prefix[0] = arr[0];
		
		for(int i=1; i<arr.length; i++) {
			prefix[i] = prefix[i-1] + arr[i];
		}
		
		//Range sum
		int left = 2;
		int right = 3;
		
		int sum;
		
		if(left==0) {
			sum = prefix[right];
		} else {
			sum = prefix[right] - prefix[left-1];
		}
		
		System.out.println("Sub Array Sum from index "+left+" to "+right+": "+sum);
	}
}
