import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

class Solution {
    private List<Integer>[] getAdj(int n, int[][] computers) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < computers.length - 1; i++) {
            for (int j = i + 1; j < computers.length; j++) {
                if (computers[i][j] == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        return adj;
    }
    
    private void bfs(int st, List<Integer>[] adj, boolean[] visited) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(st);
        visited[st] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : adj[cur]) {
                if (visited[nxt]) continue;
                q.offer(nxt);
                visited[nxt] = true;
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        List<Integer>[] adj = getAdj(n, computers);
        boolean[] visited = new boolean[n];
        int result = 0;
        for (int st = 0; st < n; st++) {
            if (visited[st]) continue;
            bfs(st, adj, visited);
            result++;
        }
        return result;
    }
}