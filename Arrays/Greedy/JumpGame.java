package Arrays.Greedy;

public class JumpGame {

    static boolean canJump(int[] nums) {

        int far = 0;

        for (int i = 0; i < nums.length; i++) {
        	
            if (i > far) {
                return false;
            }

            far = Math.max(far, i + nums[i]);
           
            if (far >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int[] nums = {1,2,4,1,1,0,2,5};

        System.out.println(canJump(nums));
    }
}