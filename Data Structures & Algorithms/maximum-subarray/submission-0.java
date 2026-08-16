class Solution {
    public int maxSubArray(int[] nums) {
        int maxsum=0, mhere=0, maxnum=Integer.MIN_VALUE;
        boolean haspositive=false;
        for(int i=0;i<nums.length;i++){
            maxnum=Math.max(maxnum, nums[i]);
            if(nums[i]>=0) haspositive=true;
            mhere+=nums[i];
            if(mhere<0)
                mhere=0;
            maxsum=Math.max(maxsum,mhere);
        }
        return haspositive?maxsum:maxnum;
    }
}
