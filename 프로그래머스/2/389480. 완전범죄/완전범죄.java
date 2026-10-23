import java.util.*;

class Solution {
    private static final int INF = 10_000;
    
    // dp[i]: B 흔적이 i개일 때, A 흔적의 최소값
    public int solution(int[][] info, int n, int m) {
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int i = 0; i < info.length; i++) {
            int a = info[i][0];
            int b = info[i][1];
            int[] next = new int[m];
            Arrays.fill(next, INF);
            for (int j = 0; j < m; j++) {
                if (dp[j] == INF) continue;
                if (dp[j] + a < n) {
                    next[j] = Math.min(next[j], dp[j] + a);
                }
                if (j + b < m) {
                    next[j + b] = Math.min(next[j], dp[j]);
                }
            }
            dp = next;
        }
        int result = INF;
        for (int a : dp) {
            result = Math.min(result, a);
        }
        return result == INF ? -1 : result;
    }
}