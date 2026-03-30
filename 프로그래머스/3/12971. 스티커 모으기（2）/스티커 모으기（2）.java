class Solution {
    private int useFirst(int[] stickers) {
        int size = stickers.length - 1;
        int[] dp = new int[size];
        dp[0] = stickers[0];
        dp[1] = Math.max(stickers[0], stickers[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + stickers[i], dp[i - 1]);
        }
        return dp[size - 1];
    }
    
    private int useNotFirst(int[] stickers) {
        int size = stickers.length;
        int[] dp = new int[size];
        dp[1] = stickers[1];
        dp[2] = Math.max(stickers[1], stickers[2]);
        for (int i = 3; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + stickers[i], dp[i - 1]);
        }
        return dp[size - 1];
    }
    
    public int solution(int[] stickers) {
        if (stickers.length < 3) {
            int max = 0;
            for (int sticker : stickers) {
                max = Math.max(max, sticker);
            }
            return max;
        }
        return Math.max(useFirst(stickers), useNotFirst(stickers));
    }
}