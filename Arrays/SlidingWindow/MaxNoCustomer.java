package Arrays.SlidingWindow;

public class MaxNoCustomer {
    public static int maxSatisfied(int[] customers,int[] grumpy,int minutes) {
        int total=0;

        for(int i=0; i<customers.length; i++){
            if(grumpy[i]==0){
                total+=customers[i];
            }
        }

        int windowSum=0;

        for(int i=0; i<minutes; i++){
            if(grumpy[i]==1){
                windowSum+=customers[i];
            }
        }

        int maxSum=windowSum;

        for(int i=minutes; i<customers.length; i++){
            if(grumpy[i]==1){
                windowSum+=customers[i];
            }

            if(grumpy[i-minutes] == 1){
                windowSum -= customers[i-minutes];
            }

            maxSum = Math.max(maxSum,windowSum);
        }

        return total+maxSum;
    }

    public static void main(String[] args) {
        int[] customers={1,0,1,2,1,1,7,5};
        int[] grumpy={0,1,0,1,0,1,0,1};
        int minutes=3;

        System.out.println(maxSatisfied(customers,grumpy,minutes));
    }
}
