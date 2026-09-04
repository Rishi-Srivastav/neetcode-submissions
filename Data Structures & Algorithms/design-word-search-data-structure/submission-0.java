class WordDictionary {
  private TrieNode root;

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    public WordDictionary() {
        root=new TrieNode();
    }

    public void addWord(String word) {
         TrieNode current = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode curr=root;
        return dfs(word, 0, curr);
    }

    public boolean dfs(String word, int index, TrieNode node){
          // Entire word processed
        if (index == word.length()) {
            return node.isEnd;
        }

        char c = word.charAt(index);

        // Normal character
        if (c != '.') {
            TrieNode child = node.children[c - 'a'];

            if (child == null) {
                return false;
            }

            return dfs(word, index + 1, child);
        }

        // Wildcard '.'
        for (TrieNode child : node.children) {
            if (child != null && dfs(word, index + 1, child)) {
                return true;
            }
        }

        return false;
    }
}
