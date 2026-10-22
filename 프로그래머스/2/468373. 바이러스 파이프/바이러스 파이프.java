import java.util.*;

class Solution {
    private List<int[]>[] adj;
    private final int[] sorts = {1, 2, 3};
    
    private void setAdj(int n, int[][] edges) {
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }
    }
    
    private int perm(int k, int depth, boolean[] v) {
        if (depth == k) {
            return 0;
        }
        Queue<Integer> q = new LinkedList<>();
        int max = 0;
        for (int target : sorts) {
            boolean[] nv = v.clone();
            for (int i = 1; i < nv.length; i++) {
                if (!nv[i]) continue;
                for (int[] nxt : adj[i]) {
                    if (nxt[1] == target && !nv[nxt[0]]) {
                        q.add(nxt[0]);
                        nv[nxt[0]] = true;
                    }
                }
            }
            int cnt = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();
                cnt++;
                for (int[] nxt : adj[cur]) {
                    if (nxt[1] == target && !nv[nxt[0]]) {
                        q.add(nxt[0]);
                        nv[nxt[0]] = true;
                    }
                }
            }
            int res = perm(k, depth + 1, nv) + cnt;
            max = Math.max(max, res);
        }
        return max;
    }
    
    // 감염된 노드 중 target Edge만 사용함
    // 각 경로는 한 번만 사용하면 됨
    public int solution(int n, int infection, int[][] edges, int k) {
        setAdj(n, edges);
        boolean[] v = new boolean[n + 1];
        v[infection] = true;
        return perm(k, 0, v) + 1;
    }
}