class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        gen(nums, 0, new ArrayList<Integer>(), lists);
        return lists;
    }


    public void gen(int[] nums,int index, List<Integer> list, List<List<Integer>> lists){
        lists.add(new ArrayList<Integer>(list));
        for(int i=index;i<nums.length;i++){
            if(i>index && nums[i]==nums[i-1]){
                continue;
            }
            list.add(nums[i]);
            gen(nums, i+1, list, lists);
            list.remove(list.size()-1);
        }
    }
}
