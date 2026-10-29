package Arrays.SlidingWindow;

public class HighScore {
    public static int maxScore(int[] cardPoints,int k) {
        int n=cardPoints.length;
        int sum=0;

        for(int i=0; i<k; i++){
            sum+=cardPoints[i];
        }

        int maxSum=sum;

        for(int i=0; i<k; i++){
            sum -= cardPoints[k-1-i];
            sum += cardPoints[n-1-i];

            maxSum = Math.max(maxSum,sum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] cardPoints={1,2,3,4,5,6,1};
        int k=3;

        int result = maxScore(cardPoints,k);

        System.out.println("Maximum Score: "+result);
    }

}
