class Solution {
    // 1 -> 1
    // 2 -> 2
    // 3 -> 3
    // 4 -> 5
    public int solution(int n) {
        int[] dp = new int[60_001];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1_000_000_007;
        }
        return dp[n];
    }
}