class Solution {
    private int selectFirst(int[] stickers) {
        int n = stickers.length;
        int[] dp = new int[n];
        dp[0] = stickers[0];
        dp[1] = Math.max(stickers[0], stickers[1]);
        for (int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + stickers[i]);
        }
        return dp[n - 2];
    }
    
    private int notSelectFirst(int[] stickers) {
        int n = stickers.length;
        int[] dp = new int[n];
        dp[1] = stickers[1];
        dp[2] = Math.max(stickers[1], stickers[2]);
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + stickers[i]);
        }
        return dp[n - 1];
    }
    
    public int solution(int[] stickers) {
        if (stickers.length < 3) {
            int max = 0;
            for (int s : stickers) {
                max = Math.max(max, s);
            }
            return max;
        }
        return Math.max(selectFirst(stickers), notSelectFirst(stickers));
    }
}