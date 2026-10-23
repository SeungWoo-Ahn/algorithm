import java.util.*;

class Solution {
    private final Map<Integer, Integer> outdegree = new HashMap<>();
    private final Map<Integer, Integer> indegree = new HashMap<>();
    
    private int createdNode() {
        int node = 0;
        int total = 0;
        for (int key : outdegree.keySet()) {
            if (indegree.get(key) == null) {
                int size = outdegree.get(key);
                if (size > total) {
                    node = key;
                    total = size;
                }
            }
        }
        return node;
    }
    
    public int[] solution(int[][] edges) {
        for (int[] e : edges) {
            outdegree.put(e[0], outdegree.getOrDefault(e[0], 0) + 1);
            indegree.put(e[1], indegree.getOrDefault(e[1], 0) + 1);
        }
        int createdNode = createdNode();
        int[] result = new int[4];
        result[0] = createdNode;
        for (int node : indegree.keySet()) {
            if (outdegree.get(node) == null) {
                result[2]++;
                continue;
            }
            int edge = indegree.get(node) + outdegree.get(node);
            if (edge >= 4) {
                result[3]++;
            }
        }
        result[1] = outdegree.get(createdNode) - result[2] - result[3];
        return result;
    }
}