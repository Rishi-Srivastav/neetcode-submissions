class Solution {
    public int maxProduct(int[] nums) {
        int maxhere=nums[0];
        int minhere=nums[0];
        int res=nums[0];
        for(int i=1;i<nums.length;i++){

            int prevmax=maxhere;
            int prevmin=minhere;

            maxhere=Math.max(nums[i], Math.max(prevmax*nums[i],         prevmin*nums[i]));
            minhere=Math.min(nums[i], Math.min(prevmax*nums[i],         prevmin*nums[i]));
            res=Math.max(res, Math.max(maxhere, minhere));
            
        }
        return res;
    }
}
