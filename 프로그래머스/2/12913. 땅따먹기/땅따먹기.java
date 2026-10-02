class Solution {
    public int solution(int[][] land) {
        int n = land.length;
        int[][] dp = new int[n][4];
        dp[0] = land[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (j == k) continue;
                    int sum = land[i][j] + dp[i - 1][k];
                    if (sum > dp[i][j]) {
                        dp[i][j] = sum;
                    }
                }
            }
        }
        int result = 0;
        for (int score : dp[n - 1]) {
            if (score > result) {
                result = score;
            }
        }
        return result;
    }
}