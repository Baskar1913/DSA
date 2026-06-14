package Arrays.TwoPointers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length - 3; i++) {

            for (int j = i + 1; j < nums.length - 2; j++) {

                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {

                    long sum = (long) nums[i]
                             + nums[j]
                             + nums[left]
                             + nums[right];

                    if (sum == target) {

                        set.add(Arrays.asList(
                                nums[i],
                                nums[j],
                                nums[left],
                                nums[right]
                        ));

                        left++;
                        right--;
                    }
                    else if (sum < target) {
                        left++;
                    }
                    else {
                        right--;
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        List<List<Integer>> result = fourSum(nums, target);

        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
}