class Solution {
    private static final int MOD = 20170805;
    
    public int solution(int n, int m, int[][] cityMap) {
        if (n == 1 && m == 1) {
            return 1;
        }
        int[][][] dp = new int[n][m][2];
        for (int y = 1; y < m; y++) {
            if (cityMap[0][y] == 1) break;
            dp[0][y][1] = 1;
        }
        for (int x = 1; x < n; x++) {
            if (cityMap[x][0] == 1) break;
            dp[x][0][0] = 1;
        }
        for (int x = 1; x < n; x++) {
            for (int y = 1; y < m; y++) {
                if (cityMap[x][y] == 1) continue;
                if (cityMap[x - 1][y] != 1) {
                    dp[x][y][0] = dp[x - 1][y][0];
                    if (cityMap[x - 1][y] == 0) {
                        dp[x][y][0] = (dp[x][y][0] + dp[x - 1][y][1]) % MOD;
                    } 
                }
                if (cityMap[x][y - 1] != 1) {
                    dp[x][y][1] = dp[x][y - 1][1];
                    if (cityMap[x][y - 1] == 0) {
                        dp[x][y][1] = (dp[x][y][1] + dp[x][y - 1][0]) % MOD;
                    }
                }
            }
        }
        return (dp[n - 1][m - 1][0] + dp[n - 1][m - 1][1]) % MOD; 
    }
}