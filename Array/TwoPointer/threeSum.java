package Arrays.TwoPointers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class threeSum {

	public static void main(String[] args) {
		 
		int[] arr = {-1,0,1,2,-1,-4};
		
		Arrays.sort(arr);

		Set<List<Integer>> arrList = new HashSet<>();
		
		for(int i=0; i<arr.length-2; i++) {
			
			int left = i+1;
			int right = arr.length-1;
			
			while(left < right) {
				
				int sum = arr[i] + arr[left] + arr[right];
				
				if(sum == 0) {
					arrList.add(Arrays.asList(arr[i],arr[left],arr[right]));
					
					left++;
					right--;
				}
				else if(sum < 0) {
					left++;
				}
				else if(sum > 0) {
					right--;
				}
			}	
		}

		System.out.println(arrList);
	}

}
