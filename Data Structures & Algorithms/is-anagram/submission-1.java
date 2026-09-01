class Solution {
    public boolean isAnagram(String s, String t) {
        int[] sc = new int[26];
        int[] st = new int[26];

        for(char c: s.toCharArray()){
            sc[c-'a'] ++;
        }
        for(char c: t.toCharArray()){
            st[c-'a'] ++;
        }
        
        for(int i=0;i<26;i++){
            if(sc[i]!=st[i]) return false;
        }
        return true;
    }
}
