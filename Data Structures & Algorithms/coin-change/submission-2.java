class Solution {
    public int coinChange(int[] coins, int amount) {

        // dp[i] = minimum coins needed to make amount i
        int[] dp = new int[amount + 1];

        // Initialize with an impossible large value
        Arrays.fill(dp, amount + 1);

        // Base case
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {

            for (int coin : coins) {

                if (coin <= i) {
                    dp[i] = Math.min(
                        dp[i],
                        dp[i - coin] + 1
                    );
                }
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}

