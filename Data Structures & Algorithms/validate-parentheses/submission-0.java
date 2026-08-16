class Solution {
    public boolean isValid(String s) {
        HashMap<Character, Character> charmap=new HashMap();
        charmap.put('(',')');
        charmap.put('{','}');
        charmap.put('[',']');
        Stack<Character> stack = new Stack();
        for(char c: s.toCharArray()){
            if(charmap.containsKey(c)){
                stack.push(c);

            }
            else if(c==')' || c==']' || c=='}'){
                if (stack.isEmpty()) return false;
                char a=stack.pop();
                if(c!=charmap.get(a)) return false;
            }
        }
        if(stack.isEmpty()) return true;
        return false;
    }
}
