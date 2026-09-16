class Solution {
    public boolean isPalindrome(String str) {
        int l=0;
        boolean res=true;
        str=str.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for(char c: str.toCharArray()){
            if(checkChar(c)){
                sb.append(c);
            }
        }
        String s=new String(sb);
        System.out.println("result : "+sb);
        int r=s.length()-1;
        while(l<r){ 
            if(s.charAt(l)==s.charAt(r)){
                l++;
                r--;
            } else {
                return false;
            }
        }
        return res;
    }

    public boolean checkChar(char c){
        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
            return false;
    }
    return true;
}
}
