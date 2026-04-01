class Solution {
    public int solution(String[] arr) {
        int[] dp = new int[2];
        int acc = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                acc += Integer.parseInt(arr[i]);
                continue;
            }
            if (arr[i].equals("-")) {
                int min = dp[0];
                int max = dp[1];
                dp[0] = Math.min(-(max + acc), min - acc);
                dp[1] = Math.max(-(min + acc), max + acc - (Integer.parseInt(arr[i + 1]) << 1));
                acc = 0;
            }
        }
        return dp[1] + acc;
    }
}