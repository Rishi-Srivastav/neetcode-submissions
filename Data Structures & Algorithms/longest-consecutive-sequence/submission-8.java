class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: nums)
            map.put(i, map.getOrDefault(i,0)+1);
        int count=1;
        int maxcount=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(map.keySet());
        int prev=Integer.MIN_VALUE;
        int curr=0;
        if(nums.length<=1) 
            return nums.length;
        while(!pq.isEmpty()){
            if(prev==Integer.MIN_VALUE){
                 prev=pq.poll();
            }
            if(pq.peek()==null){
                maxcount=Math.max(maxcount,count);
                break;
            }
             curr=pq.poll();
           // System.out.println("curr:"+curr);
            if(map.get(curr)==0){
                maxcount=Math.max(maxcount,count);
                prev=Integer.MIN_VALUE;
                count=1;
            //    System.out.println("curr==0"+curr);
            }
            if(curr==prev+1){
                count++;
                map.put(curr, map.get(curr)-1);
           //     System.out.println("prev+1=curr:"+curr);
                prev=curr;
            } else {
                prev=curr;
                maxcount=Math.max(maxcount,count);
           //     System.out.println("resert prev:"+prev);
                count=1;
            }
        }
        return maxcount>count?maxcount:count;
    }
}
