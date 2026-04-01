class Solution {
    private int robFirst(int[] money) {
        int size = money.length - 1;
        int[] dp = new int[size];
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        for (int i = 2; i < size; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }
        return dp[size - 1];
    }
    
    private int notRobFirst(int[] money) {
        int size = money.length;
        int[] dp = new int[size];
        dp[1] = money[1];
        for (int i = 2; i < size; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }
        return dp[size - 1];
    }
    
    public int solution(int[] money) {
        return Math.max(robFirst(money), notRobFirst(money));
    }
}