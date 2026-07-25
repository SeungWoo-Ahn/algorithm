import java.util.*;

class Solution {
    private Map<Integer, List<Integer>> adj = new HashMap<>();
    
    private boolean holzzak(int cur, int parent) {
        int children = adj.get(cur).size();
        if (parent > 0) children--;
        if (cur % 2 == 0 && children % 2 == 1) return false;
        if (cur % 2 == 1 && children % 2 == 0) return false;
        for (int child : adj.get(cur)) {
            if (child == parent) continue;
            if (!holzzak(child, cur)) return false;
        }
        return true;
    }
    
    private boolean eukholzzak(int cur, int parent) {
        int children = adj.get(cur).size();
        if (parent > 0) children--;
        if (cur % 2 == 0 && children % 2 == 0) return false;
        if (cur % 2 == 1 && children % 2 == 1) return false;
        for (int child : adj.get(cur)) {
            if (child == parent) continue;
            if (!eukholzzak(child, cur)) return false;
        }
        return true;
    }
    
    public int[] solution(int[] nodes, int[][] edges) {
        for (int node : nodes) {
            adj.put(node, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        int[] result = new int[2];
        for (int root : nodes) {
            if (holzzak(root, 0)) result[0]++;
            if (eukholzzak(root, 0)) result[1]++;
        }
        return result;
    }
}