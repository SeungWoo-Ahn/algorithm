import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private boolean[] v;
    
    private void bfs(int st) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(st);
        v[st] = true;
        while (!dq.isEmpty()) {
            int cur = dq.removeFirst();
            for (int nxt : adj[cur]) {
                if (v[nxt]) continue;
                dq.addLast(nxt);
                v[nxt] = true;
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        adj = new List[n];
        v = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        int result = 0;
        for (int st = 0; st < n; st++) {
            if (v[st]) continue;
            bfs(st);
            result++;
        }
        return result;
    }
}