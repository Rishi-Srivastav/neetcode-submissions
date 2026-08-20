class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(n->map.put(n, map.getOrDefault(n, 0)+1));
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)-> map.get(b)-map.get(a));
        for(int i: map.keySet()){
            pq.add(i);
        }
    int[] res = new int[k];
    int j=0;
    while(j<k){
        res[j]=pq.poll();
        j++;
    }
    return res;
}
}
