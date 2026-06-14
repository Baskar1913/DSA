package Arrays.TwoPointers;

import java.util.Arrays;

public class NextPermutation {

    public static void nextPermutation(int[] nums) {

        int pos = -1;

        for (int i = nums.length - 2; i >= 0; i--) {

            if (nums[i] < nums[i + 1]) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {

            for (int i = nums.length - 1; i > pos; i--) {

                if (nums[i] > nums[pos]) {

                    int temp = nums[i];
                    nums[i] = nums[pos];
                    nums[pos] = temp;

                    break;
                }
            }
        }

        int left = pos + 1;
        int right = nums.length - 1;

        while (left < right) {

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};

        nextPermutation(nums);

        System.out.println(Arrays.toString(nums));
    }
}