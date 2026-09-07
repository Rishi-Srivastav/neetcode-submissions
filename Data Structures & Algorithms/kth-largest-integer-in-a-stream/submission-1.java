class KthLargest {

    PriorityQueue<Integer> pq;
    int l;
    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<Integer>(k);
        l=k;
        for(int i: nums){
            pq.offer(i);
            if(pq.size()>k){
                pq.poll();
            }
                
        }
    }
    
    public int add(int val) {
         pq.offer(val);
         if(pq.size()>l){
                int a=pq.poll();
                System.out.println("removing : "+a);
                
            }
        
        int res=pq.peek();
        System.out.println(l+ "th largest is : "+res);
        return res;
    }
}
