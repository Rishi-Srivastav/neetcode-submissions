class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        getsubsets(nums, 0, new ArrayList<Integer>(), lists);
        return lists;
    }

    public void getsubsets(int[] nums, int ind, List<Integer> list, List<List<Integer>> lists){
        lists.add(new ArrayList<>(list));
            if(ind==nums.length){
            return;
        }
        for(int i=ind;i<nums.length;i++){
            list.add(nums[i]);
            getsubsets(nums, i+1, list, lists);
            list.remove(list.size()-1);
        }

    }

}
