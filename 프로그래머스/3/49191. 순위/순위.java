import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private List<Integer>[] adj;
    private List<Integer>[] rAdj;
    
    private void setAttrs(int n, int[][] results) {
        adj = new List[n + 1];
        rAdj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            rAdj[i] = new ArrayList<>();
        }
        for (int[] result : results) {
            int win = result[0];
            int lose = result[1];
            adj[win].add(lose);
            rAdj[lose].add(win);
        }
    }
    
    private int bfs(int st, List<Integer>[] adj) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.length];
        q.offer(st);
        visited[st] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : adj[cur]) {
                if (visited[nxt]) continue;
                q.offer(nxt);
                visited[nxt] = true;
                cnt++;
            }
        }
        return cnt;
    }
    
    public int solution(int n, int[][] results) {
        setAttrs(n, results);
        int[] knownCnt = new int[n + 1];
        for (int st = 1; st <= n; st++) {
            knownCnt[st] += bfs(st, adj);
            knownCnt[st] += bfs(st, rAdj);
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (knownCnt[i] == n - 1) {
                result++;
            }
        }
        return result;
    }
}