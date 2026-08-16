class Solution {
    public boolean isAnagram(String s, String t) {
        int[] s1=new int[26];
        int[] t1=new int[26];

        for(char c: s.toCharArray()){
            s1[c-'a']+=1;
        }
        for(char c: t.toCharArray()){
            t1[c-'a']+=1;
        }
        for(int i=0;i<26;i++){
            if(s1[i]!=t1[i])
                return false;
        }
        return true;
    }
}
