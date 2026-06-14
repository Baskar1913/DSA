package Arrays.TwoPointers;

public class MoveZerosToEnd {

    public static void moveZeroes(int[] nums) {

        int pos = 0;

        for (int start = 0; start < nums.length; start++) {

            if (nums[start] != 0) {

                int temp = nums[pos];
                nums[pos] = nums[start];
                nums[start] = temp;

                pos++;
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};

        moveZeroes(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
