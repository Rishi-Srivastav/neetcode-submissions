class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        HashSet<List<Integer>> lists= new HashSet<>();
        for(int i=0;i<n;i++){
            int target = (-1)*nums[i];
            int j=i+1, k=n-1;
            while(j<k){
                if(nums[j]+nums[k]==target){
                    List<Integer> list =List.of(nums[i], nums[j], nums[k]);
                    lists.add(list);
                    j++;
                    k--;
                    continue;
                } else if(nums[j]+nums[k]>target){
                    k--;
            } else {
                j++;
            }
        }
    }
    return new ArrayList<List<Integer>>(lists);
}
}