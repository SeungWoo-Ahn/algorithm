import java.util.*;

class Solution {
    private int[] weight;
    private List<Integer>[] adj;
    private int[][] dp;
    
    private void dfs(int cur) {
        dp[cur][0] = 0;
        dp[cur][1] = weight[cur];
        if (adj[cur].size() == 0) return;
        
        int extra = Integer.MAX_VALUE;
        for (int child : adj[cur]) {
            dfs(child);
            
            if (dp[child][0] < dp[child][1]) {
                dp[cur][0] += dp[child][0]; // 팀장 X, 팀원 X -> 문제 발생
                dp[cur][1] += dp[child][0]; // 팀장 O, 팀원 X
                extra = Math.min(extra, dp[child][1] - dp[child][0]);
            } else {
                dp[cur][0] += dp[child][1]; // 팀장 X, 팀원 O
                dp[cur][1] += dp[child][1]; // 팀장 O, 팀원 O
                extra = 0;
            }
        }
        dp[cur][0] += extra;
    }
    
    public int solution(int[] sales, int[][] links) {
        int n = sales.length;
        weight = new int[n + 1];
        adj = new List[n + 1];
        dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            weight[i] = sales[i - 1];
            adj[i] = new ArrayList<>();
        }
        for (int[] link : links) {
            adj[link[0]].add(link[1]);
        }
        dfs(1);
        return Math.min(dp[1][0], dp[1][1]);
    }
}