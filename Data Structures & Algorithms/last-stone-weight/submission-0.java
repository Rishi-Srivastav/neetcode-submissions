class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq=new PriorityQueue<>(stones.length, (a,b)->b-a);
        for(int s: stones){
            pq.offer(s);
        }

        while(pq.size()!=1){
            int a=pq.poll();
            int b=pq.poll();
            pq.offer(a-b);
        }
        return pq.peek();
    }
}
