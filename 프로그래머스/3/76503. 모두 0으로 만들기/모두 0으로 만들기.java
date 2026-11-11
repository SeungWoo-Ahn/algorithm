import java.util.*;

class Solution {
    private long sum(int[] a) {
        long sum = 0;
        for (int w : a) {
            sum += w;
        }
        return sum;
    }
    
    private List<Integer>[] getAdj(int[][] edges) {
        List<Integer>[] adj = new List[edges.length + 1];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        return adj;
    }
    
    public long solution(int[] a, int[][] edges) {
        long sum = sum(a);
        if (sum != 0L) {
            return -1L;
        }
        int n = a.length;
        List<Integer>[] adj = getAdj(edges);
        Deque<Integer> dq = new ArrayDeque<>();
        int[] order = new int[n];
        int[] parent = new int[n];
        int idx = 0;
        dq.addLast(0);
        parent[0] = -1;
        while (!dq.isEmpty()) {
            int cur = dq.removeLast();
            order[idx++] = cur;
            for (int nxt : adj[cur]) {
                if (nxt == parent[cur]) continue;
                parent[nxt] = cur;
                dq.addLast(nxt);
            }
        }
        long[] weight = new long[n];
        for (int i = 0; i < n; i++) {
            weight[i] = a[i];
        }
        long result = 0L;
        for (int i = n - 1; i >= 0; i--) {
            int node = order[i];
            result += Math.abs(weight[node]);
            if (parent[node] != -1) {
                weight[parent[node]] += weight[node];
            }
        }
        return result;
    }
}