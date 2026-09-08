class Solution {
    int m, n;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        m = board.length;
        n = board[0].length;

        // Visit boundary cells
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }

        // Convert surrounded O -> X
        // Restore safe cells (# -> O)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r >= m || c >= n || board[r][c] != 'O') {
            return;
        }

        board[r][c] = '#'; // mark as safe

        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }
}