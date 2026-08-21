class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[][] dp = new Boolean[s.length()][wordDict.size()];
        return breaks(s, 0, wordDict, 0, dp);
    }

    public boolean breaks(String s, int i, List<String> wordDict, int ind, Boolean[][] dp){
        if(i==s.length())
            return true;
        if(ind==wordDict.size()){
            return false;
        }

        if(dp[i][ind]!=null)
            return dp[i][ind];
        String word=wordDict.get(ind);
        if(i+ word.length()<=s.length() && s.substring(i, i+ word.length()).equals(word) && breaks(s, i+word.length(),wordDict,0, dp)){
           System.out.println(s.substring(i, i+word.length()));
           dp[i][ind]=true;
           return true;
        } else {
            dp[i][ind]= breaks(s, i, wordDict,ind+1, dp);
            return dp[i][ind];
        }
}
}
