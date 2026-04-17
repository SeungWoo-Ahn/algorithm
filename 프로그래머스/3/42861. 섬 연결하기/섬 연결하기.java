import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    private static class Edge implements Comparable<Edge> {
        final int u;
        final int v;
        final int cost;
        
        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
    
    private int[] parent;
    
    private void setParent(int n) {
        parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }
    
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    private boolean isDiffGroup(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return false;
        }
        if (rootX < rootY) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
        }
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        List<Edge> edges = new ArrayList<>();
        for (int[] cost : costs) {
            edges.add(new Edge(cost[0], cost[1], cost[2]));
        }
        Collections.sort(edges);
        setParent(n);
        int cnt = 0;
        int result = 0;
        for (Edge edge : edges) {
            if (!isDiffGroup(edge.u, edge.v)) continue;
            cnt++;
            result += edge.cost;
            if (cnt == n - 1) break;
        }
        return result;
    }
}