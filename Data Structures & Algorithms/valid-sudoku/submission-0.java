class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        for(int i=0;i<9;i++){
            HashSet<Character> rset = new HashSet<>();
        HashSet<Character> cset = new HashSet<>();
        HashSet<Character> bset = new HashSet<>();
            for(int j=0;j<9;j++){
                if(board[i][j]!='.'){
                    if(rset.contains(board[i][j])){
                        return false;
                    }
                    rset.add(board[i][j]);
                }

            if(board[j][i]!='.'){
                    if(cset.contains(board[j][i])){
                        return false;
                    }
                    cset.add(board[j][i]);
                }

            int row=3* (i/3);
            int col=3* (i%3);
            int r= row+j/3;
            int c= col+j%3;
            if(board[r][c]!='.'){
                    if(bset.contains(board[r][c])){
                        return false;
                    }
                    bset.add(board[r][c]);
                }    
            }
        }
            return true;
    }
}
