class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] out=new int[n];
        int[] left=new int[n];
        int[] right=new int[n];
        int l=1,r=1;
        for(int i=0;i<n;i++){
            left[i]= l*(i==0?1:nums[i-1]);
            l=left[i];
            right[n-i-1]=r*(i==0?1:nums[n-i]);
            r=right[n-i-1];
        }
        for(int i=0;i<n;i++){
            out[i]=left[i]*right[i];
        }
        return out;
    }
}  
