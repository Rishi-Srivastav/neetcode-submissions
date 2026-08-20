class Solution {
    public int numDecodings(String s) {
        Integer[] dp = new Integer[s.length()];
        return count(s, 0, dp);
    }

    public int count(String s,int i, Integer[] dp){
        if(s.length()==i)
            return 1;
         if(i>=s.length())
            return 0;

      
        int one = 0;

        if(dp[i]!=null) return dp[i];

        if(s.charAt(i)!='0')
              one =count(s,i+1,dp);
        int two=0;
        if (i + 1 < s.length() &&
            (s.charAt(i) == '1' ||
            (s.charAt(i) == '2' && s.charAt(i + 1) <= '6'))) {
            two=count(s,i+2,dp);
        }
        dp[i] = one+two;
         return dp[i];
     }
}
