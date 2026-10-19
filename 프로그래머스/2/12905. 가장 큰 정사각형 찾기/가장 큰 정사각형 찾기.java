class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] dp = new int[n][m];
        for (int y = 0; y < m; y++) {
            dp[0][y] = board[0][y];
        }
        for (int x = 0; x < n; x++) {
            dp[x][0] = board[x][0];
        }
        for (int x = 1; x < n; x++)
            for (int y = 1; y < m; y++)
                if (board[x][y] == 1)
                    dp[x][y] = Math.min(dp[x - 1][y - 1], Math.min(dp[x - 1][y], dp[x][y - 1])) + 1;
        int result = 0;
        for (int x = 0; x < n; x++)
            for (int y = 0; y < m; y++)
                if (dp[x][y] > result)
                    result = dp[x][y];
        return result * result;
    }
}