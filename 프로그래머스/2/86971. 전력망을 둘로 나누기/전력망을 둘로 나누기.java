import java.util.List;
import java.util.ArrayList;

class Solution {
    private List<Integer>[] getAdj(int n, int[][] wires) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            adj[v1].add(v2);
            adj[v2].add(v1);
        }
        return adj;
    }
    
    private int dfs(int cur, boolean[] visited, List<Integer>[] adj) {
        int count = 1;
        for (int nxt : adj[cur]) {
            if (visited[nxt]) continue;
            visited[nxt] = true;
            count += dfs(nxt, visited, adj);
        }
        return count;
    }
    
    public int solution(int n, int[][] wires) {
        List<Integer>[] adj = getAdj(n, wires);
        int result = n + 1;
        for (int[] wire : wires) {
            Integer v1 = Integer.valueOf(wire[0]);
            Integer v2 = Integer.valueOf(wire[1]);
            adj[v1].remove(v2);
            adj[v2].remove(v1);
            List<Integer> counts = new ArrayList<>();
            boolean[] visited = new boolean[n + 1];
            for (int st = 1; st <= n; st++) {
                if (visited[st]) continue;
                visited[st] = true;
                counts.add(dfs(st, visited, adj));
            }
            if (counts.size() == 2) {
                int diff = Math.abs(counts.get(0) - counts.get(1));
                result = Math.min(result, diff);
            }
            adj[v1].add(v2);
            adj[v2].add(v1);
        }
        return result;
    }
}