package Arrays.TwoPointers;

import java.util.Arrays;

public class boatsToSavePeople {

    public static int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;

        int count = 0;

        while (left <= right) {

            if (people[left] + people[right] <= limit) {
                left++;
            }

            right--;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {

        int[] people = {3, 2, 2, 1};
        int limit = 3;

        int result = numRescueBoats(people, limit);

        System.out.println("Minimum Boats = " + result);
    }
}
