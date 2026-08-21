class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        boolean[][] isPuddle = new boolean[n][m];
        for (int[] puddle : puddles) {
            isPuddle[puddle[1] - 1][puddle[0] - 1] = true;
        }
        dp[0][0] = 1;
        for (int y = 0; y < m; y++) {
            if (isPuddle[0][y]) break;
            dp[0][y] = 1;
        }
        for (int x = 0; x < n; x++) {
            if (isPuddle[x][0]) break;
            dp[x][0] = 1;
        }
        for (int x = 1; x < n; x++) {
            for (int y = 1; y < m; y++) {
                if (isPuddle[x][y]) continue;
                dp[x][y] = (dp[x - 1][y] + dp[x][y - 1]) % 1_000_000_007;
            }
        }
        return dp[n - 1][m - 1];
    }
}