class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount+1];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        int n = change(0, coins, amount, dp);
        return n==Integer.MAX_VALUE ? -1 : n;
    }

    public int change(int i, int[]coins, int amount, int[][] dp){
        if(i==coins.length || amount<0)
            return Integer.MAX_VALUE;
        if(amount==0) {
            return 0;
    } 
    if(dp[i][amount]!=-1){
        return dp[i][amount];
    }
       int take = change(i, coins, amount-coins[i], dp);
       if(take!=Integer.MAX_VALUE){
            take=1+take;
       }

        int leave = change(i+1, coins, amount,dp);
        dp[i][amount] =  Math.min(take, leave);
        return dp[i][amount] ;
}
}
