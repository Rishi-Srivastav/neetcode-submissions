class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a,b)->b[2]-a[2]);
        for(int[] p : points){
             System.out.println("add :"+p[0]+", "+p[1]);
            int dist= (p[0]*p[0] + p[1]*p[1]);
            pq.offer(new int[]{p[0], p[1], dist});
            if(pq.size()>k){
                pq.poll();
            }
        }
        int[][] res = new int[pq.size()][2];
        pq.stream().forEach(e-> System.out.println(Arrays.toString(e)));
        System.out.println("pq: "+pq.size());
        int size=pq.size();
        for(int i=0;i<k;i++){
            int[] r=pq.poll();
            res[i][0]=r[0];
            res[i][1]=r[1];
    }
    return res;
}
}