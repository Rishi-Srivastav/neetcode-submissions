class Solution {
    public int jump(int[] nums) {
        int n=nums.length;
        int maxreach=0;
        int minstep=0;
        int end=0;
        if(n==1) return 0;
        for(int i=0;i<n;i++){
            maxreach=Math.max(maxreach, i+nums[i]);
            if(end==i){
                minstep++;
                end=maxreach;
            } 
            if(end>=n-1)
                return minstep;
        }
        return minstep;
    }
}
