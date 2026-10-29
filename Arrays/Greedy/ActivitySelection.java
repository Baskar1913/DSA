package Arrays.Greedy;

public class ActivitySelection {

	static void activitySelector(int[] start, int[] finish) {
		
		int k = 0;
		
		System.out.print("Selected Activities: ");
		System.out.print("A"+(k+1));
		
		for(int i=1; i<start.length; i++) {
			
			if(start[i]>=finish[k]) {
				System.out.print(" A"+(i+1));
				k = i;
			}
		}
	}
	
	public static void main(String[] args) {
		
		int[] start = {1, 3, 0, 5, 3, 5, 6, 7, 8, 2, 12};
		int[] finish = {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
		
		activitySelector(start, finish);
	}
}
