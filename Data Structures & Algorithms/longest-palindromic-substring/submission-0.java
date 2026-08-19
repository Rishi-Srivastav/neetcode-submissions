class Solution {
    public String longestPalindrome(String s) {
        int n=s.length();
        int longest=0;
        String longstr="";
        for(int i=0;i<n;i++){
           String a= expand(s,i,i);
           String b = expand(s,i,i+1);
           String slong= a.length()>b.length() ? a:b;
           longstr=longstr.length()>slong.length()?longstr:slong;
        }
        return longstr;
    }

    public String expand(String s, int i, int j){
        int leng = 0;
while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
    i--;
    j++;
}
        
        return s.substring(i+1, j);
    }
}
