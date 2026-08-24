import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] v = new boolean[n + 1];
        q.add(1);
        v[1] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int nxt : adj[cur]) {
                    if (v[nxt]) continue;
                    q.add(nxt);
                    v[nxt] = true;
                }
            }
            if (q.isEmpty()) {
                return size;
            }
        }
        return 0;
    }
}