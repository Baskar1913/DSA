package Arrays.TwoPointers;

import java.util.Arrays;

public class SquaresOfSortedArray {

    public static int[] sortedSquares(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        int[] result = new int[nums.length];

        int k = nums.length - 1;

        while (left <= right) {

            if (Math.abs(nums[left]) > Math.abs(nums[right])) {

                result[k] = nums[left] * nums[left];
                left++;
            }
            else {

                result[k] = nums[right] * nums[right];
                right--;
            }

            k--;
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {-4, -1, 0, 3, 10};

        int[] result = sortedSquares(nums);

        System.out.println(Arrays.toString(result));
    }
}
