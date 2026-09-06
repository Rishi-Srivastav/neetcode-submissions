class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(candidates);
        find(candidates, target, 0, new ArrayList<Integer>(), lists);
        return lists;
    }

    public void find(int[] cand, int target, int ind, List<Integer> list , List<List<Integer>> lists){
        if(target==0){
            lists.add(new ArrayList<>(list));
            //list.stream().forEach(e-> System.out.print(e+ ", "));
            return;
        }
        if(ind==cand.length){
            System.out.println("ind : "+ind);
             return;
        }

         for(int i=ind;i<cand.length;i++){
            if(i>ind && cand[i]==cand[i-1])
                continue;
            if(target-cand[i]>=0){
                list.add(cand[i]);
               // System.out.println("i : "+cand[i]);
                find(cand, target-cand[i], i+1, list, lists);
                list.remove(list.size()-1);
                }
            if(cand[i]>target)
                break;    
        }  
        //System.out.println("outside : "+cand[i]);
        //find(cand, target, i+1, list, lists);
        }
     } 
