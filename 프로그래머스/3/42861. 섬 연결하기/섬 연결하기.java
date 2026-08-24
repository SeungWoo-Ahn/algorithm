import java.util.*;

class Solution {
    private int[] parent;
    
    private void setParent(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    private boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;
        if (rootX < rootY) parent[rootY] = rootX;
        else parent[rootX] = rootY;
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        setParent(n);
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        int cnt = 0;
        int result = 0;
        for (int[] cost : costs) {
            if (!union(cost[0], cost[1])) continue;
            result += cost[2];
            if (++cnt == n - 1) break;
        }
        return result;
    }
}