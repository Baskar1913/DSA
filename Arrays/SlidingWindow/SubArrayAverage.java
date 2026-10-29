package Arrays.SlidingWindow;

import java.util.Arrays;

public class SubArrayAverage {
	public int[] getAverages(int[] nums,int k) {
        int n=nums.length;
        int[] avgs=new int[n];
        Arrays.fill(avgs,-1);

        int size = 2*k+1;

        if(size>n){
            return avgs;
        }

        long sum=0;

        for(int i=0; i<size; i++){
            sum+=nums[i];
        }

        avgs[k] = (int)(sum/size);

        for(int i=size; i<n; i++){
            sum+=nums[i]-nums[i-size];
            
            avgs[i-k]=(int)(sum/size);
        }

        return avgs;
    }

    public static void main(String[] args) {
        int[] nums={7,4,3,9,1,8,5,2,6};
        int k=3;

        SubArrayAverage obj=new SubArrayAverage();
        int[] result=obj.getAverages(nums,k);

        System.out.println(Arrays.toString(result));
    }
}
