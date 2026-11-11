import java.util.*;

class Solution {
    // node n개, edge n-1개 -> 트리
    // 연결된 두 node 중 하나는 선택해야 함
    // 선택한 node의 최소값
    // dp[node][0]: node 불 켜짐, node 루트인 서브트리에서의 최소값
    // dp[node][1]: node 불 꺼짐, node 루트인 서비트리에서의 최소값
    public int solution(int n, int[][] lighthouse) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : lighthouse) {
            int u = e[0];
            int v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        Deque<Integer> dq = new ArrayDeque<>();
        int[][] dp = new int[n + 1][2];
        boolean[] v = new boolean[n + 1];
        dq.addLast(1);
        while (!dq.isEmpty()) {
            int node = dq.removeLast();
            if (node > 0) {
                dq.addLast(-node);
                v[node] = true;
                for (int child : adj[node]) {
                    if (v[child]) continue;
                    dq.addLast(child);
                }
            } else {
                node = -node;
                for (int child : adj[node]) {
                    if (dp[child][0] > 0) {
                        dp[node][0] += Math.min(dp[child][0], dp[child][1]);
                        dp[node][1] += dp[child][0];
                    }
                }
                dp[node][0]++;
            }
        }
        return Math.min(dp[1][0], dp[1][1]);
    }
}