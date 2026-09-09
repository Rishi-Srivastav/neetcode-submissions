class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) { return res; }
        HashMap<Integer, String> map = new HashMap<>();
        map.put(2,"abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

         backtrack(digits, 0, map, res, "");
         return res;
    }

    public void backtrack(String digits, int i, HashMap<Integer, String> map, List<String> res, String str){
        if(i==digits.length()){
            res.add(str);
            return;
        }
        String chr =map.get(Integer.valueOf(digits.charAt(i)-'0'));
        for(int j=0;j<chr.length();j++){
            str=str+chr.charAt(j);
            backtrack(digits, i+1, map, res, str);
            str=str.substring(0, str.length()-1);
        }
    }
}
