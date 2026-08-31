class Solution {
    public int numIslands(char[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int count=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1' && checkisland(grid, i, j)){
                    count++;
                }
            }
        }
        return count;
    }

    public boolean checkisland(char[][] grid, int i, int j){
        if(i<0 || i==grid.length ||
        j<0 || j==grid[0].length || grid[i][j]=='0')
            return true;
        if(grid[i][j]=='1'){
            grid[i][j]='0';
            return checkisland(grid, i+1,j) &&
            checkisland(grid, i, j+1) &&
            checkisland(grid,i-1,j) &&
            checkisland(grid, i, j-1);
        }
        return false;
}
}