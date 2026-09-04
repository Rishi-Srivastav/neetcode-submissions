class Solution {
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> lists= new ArrayList<>();
        backtrack(nums, 0, lists);
        return lists;
    }

    public void backtrack(int[] nums, int index, ArrayList<List<Integer>> lists){
        if(index==nums.length-1){
            lists.add(Arrays.stream(nums).boxed().toList());
        }

        for(int i=index;i<nums.length;i++){
            swap(nums, index, i);
            backtrack(nums, index+1, lists);
            swap(nums, i, index);
        }
     }

    public void swap(int[] nums, int i, int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
