class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        boolean[][] isPuddle = new boolean[n][m];
        for (int[] puddle : puddles) {
            isPuddle[puddle[1] - 1][puddle[0] - 1] = true;
        }
        dp[0][0] = 1;
        for (int x = 0; x < dp.length; x++) {
            for (int y = 0; y < dp[x].length; y++) {
                if (isPuddle[x][y]) continue;
                if (x == 0 && y == 0) continue;
                int top = x > 0 ? dp[x - 1][y] : 0;
                int left = y > 0 ? dp[x][y - 1] : 0;
                dp[x][y] = (top + left) % 1_000_000_007;
            }
        }
        return dp[n - 1][m - 1];
    }
}