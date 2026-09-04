class PrefixTree {

    TrieNode root;
    static class TrieNode{
        TrieNode[] children=new TrieNode[26];
        boolean isEnd;
    }
    

    public PrefixTree() {
         root=new TrieNode();
    }

    public void insert(String word) {
         TrieNode current = root;

        for(char c: word.toCharArray()){
            if(current.children[c-'a']==null)
                current.children[c-'a']=new TrieNode();
            current=current.children[c-'a'];    
        }
        current.isEnd=true;
    }

    public boolean search(String word) {
      TrieNode current = root;
      for(char c: word.toCharArray()){
            if(current.children[c-'a']==null)
                return false;
            current=current.children[c-'a'];                  
    }
        if(current.isEnd==false)
            return false;
        return true;    
    }

    public boolean startsWith(String word) {

      TrieNode current = root;
      for(char c: word.toCharArray()){
            if(current.children[c-'a']==null)
                return false;
            current=current.children[c-'a'];                  
    }
        return true;    
    }
}
