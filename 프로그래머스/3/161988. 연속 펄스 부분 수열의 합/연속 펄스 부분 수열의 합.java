class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        long[][] dp = new long[n][2];
        dp[0][0] = sequence[0];
        dp[0][1] = -sequence[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], 0) + sequence[i];
            dp[i][1] = Math.max(dp[i - 1][0], 0) + -sequence[i];
        }
        long result = 0L;
        for (long[] d : dp) {
            result = Math.max(result, Math.max(d[0], d[1]));
        }
        return result;
    }
}