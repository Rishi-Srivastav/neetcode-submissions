class Solution {
    public boolean canJump(int[] nums) {
        if(nums.length <= 1) return true;
        int ind=0,maxpoint=0;
        for(int i=0;i<nums.length;i++){
            maxpoint=Math.max(maxpoint, nums[i]+i);
            if(maxpoint<=i && i < nums.length - 1) return false;
            if(maxpoint>=nums.length-1)
                return true;
        }
        return false;
    }
}
