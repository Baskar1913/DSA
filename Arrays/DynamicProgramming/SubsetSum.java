package Arrays.DynamicProgramming;

public class SubsetSum {

    static boolean subsetSum(int arr[], int target) {

        int n = arr.length;

        boolean dp[][] = new boolean[n + 1][target + 1];

        for (int i = 0; i <= n; i++) {

            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= target; j++) {

                dp[i][j] = dp[i - 1][j];

                if (arr[i - 1] <= j) {

                    dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        return dp[n][target];
    }

    public static void main(String[] args) {

        int arr[] = {3, 34, 4, 12, 5, 2};

        int target = 9;

        System.out.println(subsetSum(arr, target));
    }
}