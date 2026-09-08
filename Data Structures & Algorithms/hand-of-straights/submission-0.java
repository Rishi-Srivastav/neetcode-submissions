
class Solution {
    public boolean isNStraightHand(int[] hand, int gs) {

        int n=hand.length;
        if(n%gs!=0) return false;
        int count=n/gs;
        
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(hand).forEach(h->map.put(h, map.getOrDefault(h, 0)+1));
        PriorityQueue<Integer> pq = new PriorityQueue<>(map.keySet());
        
        while(!pq.isEmpty()){
            int start=pq.peek();
            
            for(int i=0;i<gs;i++){
                int card=i+start;
                if(!map.containsKey(card) || map.get(card)==0)
                    return false;
                map.put(card, map.get(card)-1);
                if(map.get(card)==0){
                    map.remove(card);
                    if(pq.peek()!=card)
                        return false;
                    pq.poll();
                }
            }    
        }
        return true;
}
}