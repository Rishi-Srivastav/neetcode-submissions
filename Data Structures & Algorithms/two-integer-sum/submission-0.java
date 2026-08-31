class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n=nums.length;
        int[] res = new int[2];
        for(int i=0;i<n;i++){
            if(map.containsKey(target-nums[i])){
                if(i> map.get(target-nums[i])){
                    res[0]= map.get(target-nums[i]);
                    res[1]= i;
                } else {
                    res[0]= i;
                    res[1]= map.get(target-nums[i]);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}
