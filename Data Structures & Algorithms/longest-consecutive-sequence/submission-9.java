class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int n: nums)
            set.add(n);

        int count=0, maxcount=0;
        int prev=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(!set.contains(nums[i]-1)){
                int curr=nums[i];
                while(set.contains(curr)){
                    count++;
                    curr++;
                }
            }
            maxcount=Math.max(maxcount, count);
            count=0;
        }    
        return maxcount;
    }
}
