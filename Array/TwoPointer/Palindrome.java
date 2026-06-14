package Arrays.TwoPointers;

public class Palindrome {
	
	public static boolean checkPal(String str) {
		
		int left = 0;
		int right = str.length()-1;
		
		while(left<right) {
			if(str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
	public static void main(String[] args) {
		String str = "malayalam";
		System.out.println(checkPal(str));
	}

}
