class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
     combSum(nums, target, 0, list, lists);
     return lists;
    }

    public void combSum(int[] num, int target, int index, List<Integer> list, List<List<Integer>> lists){
        if(target==0) {
             lists.add(new ArrayList<Integer>(list));
         }
        for(int i=index;i<num.length;i++){
            if(num[i]<=target){
                list.add(num[i]);
                combSum(num, target-num[i],i,list, lists);
                list.remove(list.size()-1);
            }    
        }
    }
}
