class Solution {
    public int orangesRotting(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        Queue<int[]> queue=new LinkedList<int[]>();
        int fresh=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==2){

                    queue.offer(new int[]{i,j});
                } else if(grid[i][j]==1)
                    fresh++;
            }
        }
        if (fresh == 0) return 0;
        return compute(grid, queue, fresh);
    }

    public int compute(int[][] grid, Queue<int[]> queue, int fresh)     {
        int[][] dir=new int[][]{{0,1}, {1,0},{0,-1},{-1,0}};
        int minutes=0;
        while(!queue.isEmpty() && fresh>0){
        int size=queue.size();
        for(int i=0;i<size;i++){
            int[] q=queue.poll();
            for(int[] d: dir){
                if(q[0]+d[0]>=0 
                && q[1]+d[1]>=0 
                && q[1]+d[1]<grid[0].length 
                && q[0]+d[0] <grid.length 
                &&  grid[q[0]+d[0]][q[1]+d[1]]==1){
                    grid[q[0]+d[0]][q[1]+d[1]]=2;
                    queue.offer(new int[]{q[0]+d[0], q[1]+d[1]});
                    fresh--;
                }    
            }
        }
        minutes++;
    }
    return fresh == 0 ? minutes : -1;
    }
}
