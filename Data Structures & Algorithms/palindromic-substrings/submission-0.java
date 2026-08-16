class Solution {
    public int countSubstrings(String s) {
        int n=s.length(), count=0;
        for(int i=0;i<n;i++){
            count+=expandcentre(s,i,i);
            count+=expandcentre(s,i,i+1);
    }
    return count;
}
    public int expandcentre(String s, int start, int end){
        int n=s.length(), res=0;
        while(start>=0 && end<n && s.charAt(start)==s.charAt(end)){
            start--;
            end++;
            res++;
    }
    return res;
}
}

