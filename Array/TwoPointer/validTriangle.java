package Arrays.TwoPointers;

import java.util.Arrays;

public class ValidTriangle {

    public static int triangleNumber(int[] nums) {

        int count = 0;

        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {

            int left = 0;
            int right = i - 1;

            while (left < right) {

                if (nums[left] + nums[right] > nums[i]) {

                    count += (right - left);
                    right--;
                }
                else {

                    left++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[] nums = {2, 2, 3, 4};

        int result = triangleNumber(nums);

        System.out.println("Valid Triangles = " + result);
    }
}
