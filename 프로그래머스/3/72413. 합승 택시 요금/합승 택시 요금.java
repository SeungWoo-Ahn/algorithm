class Solution {
    private static final int INF = Integer.MAX_VALUE >> 1;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] cost = new int[n + 1][n + 1];
        for (int x = 1; x <= n; x++)
            for (int y = 1; y <= n; y++) {
                if (x == y) continue;
                cost[x][y] = INF;
            }
        for (int[] fare : fares) {
            cost[fare[0]][fare[1]] = fare[2];
            cost[fare[1]][fare[0]] = fare[2];
        }
        for (int k = 1; k <= n; k++)
            for (int u = 1; u <= n - 1; u++)
                for (int v = u + 1; v <= n; v++)
                    if (cost[u][k] + cost[k][v] < cost[u][v])
                        cost[u][v] = cost[v][u] = cost[u][k] + cost[k][v];
        int result = INF;
        for (int k = 1; k <= n; k++) {
            if (cost[s][k] == INF || cost[k][a] == INF || cost[k][b] == INF) continue;
            result = Math.min(result, cost[s][k] + cost[k][a] + cost[k][b]);
        }
        return result;
    }
}