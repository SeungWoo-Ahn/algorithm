import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] adj = new List[n + 1];
        int[] cost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = -1;
        }
        for (int[] road : roads) {
            adj[road[0]].add(road[1]);
            adj[road[1]].add(road[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] v = new boolean[n + 1];
        q.add(destination);
        v[destination] = true;
        cost[destination] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : adj[cur]) {
                if (v[nxt]) continue;
                q.add(nxt);
                v[nxt] = true;
                cost[nxt] = cost[cur] + 1;
            }
        }
        int[] result = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            result[i] = cost[sources[i]];
        }
        return result;
    }
}