class Solution {
    public int lengthOfLIS(int[] nums) {
        int prev=-1;
        Integer[][] dp=new Integer[nums.length+1][nums.length+1];
        dp[0][0]=0;
        return lis(nums, 0,prev, dp);
    }

    public int lis(int[] nums, int i, int prev, Integer[][] dp){
        if(i==nums.length)
            return 0;

        int take=0;
        if(dp[i+1][prev+1]!=null)
            return dp[i+1][prev+1];
            
        if(prev==-1 || nums[i]>nums[prev]){
            take=1+lis(nums, i+1, i, dp);
        }
        int notake = lis(nums, i+1, prev, dp);
        dp[i+1][prev+1] = Math.max(take, notake);
        return dp[i+1][prev+1];
    }
}
