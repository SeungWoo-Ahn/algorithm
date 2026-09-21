class Solution {
    private boolean isFit(int score, int target, boolean sOrb, int[][] dp) {
        return dp[target - score][0] + 1 < dp[target][0] ||
            (dp[target - score][0] + 1 == dp[target][0] &&
            dp[target - score][1] + (sOrb ? 1: 0) > dp[target][1]);
    }
    
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2]; // [횟수, 싱글/불 개수]
        for (int score = 1; score <= target; score++) {
            dp[score][0] = 1_000_000;
        }
        for (int t = 1; t <= target; t++) {
            for (int mul = 1; mul <= 3; mul++) {
                for (int s = 1; s <= 20; s++) {
                    int score = s * mul;
                    if (score > t) break;
                    if (isFit(score, t, mul == 1, dp)) {
                        dp[t][0] = dp[t - score][0] + 1;
                        dp[t][1] = dp[t - score][1] + (mul == 1 ? 1: 0);
                    }
                }
            }
            if (t >= 50 && isFit(50, t, true, dp)) {
                dp[t][0] = dp[t - 50][0] + 1;
                dp[t][1] = dp[t - 50][1] + 1;
            }
        }
        return dp[target];
    }
}