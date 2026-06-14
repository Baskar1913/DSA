package Arrays.TwoPointers;

public class RemoveDuplicatesII {

    public static int removeDuplicates(int[] nums) {

        if (nums.length <= 2) {
            return nums.length;
        }

        int pos = 2;

        for (int i = 2; i < nums.length; i++) {

            if (nums[i] != nums[pos - 2]) {
                nums[pos] = nums[i];
                pos++;
            }
        }

        return pos;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};

        int k = removeDuplicates(nums);

        System.out.print("Array = ");

        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}