import java.util.*;

class Solution {
    private final Map<Integer, List<Integer>> adj = new HashMap<>();
    
    private void setAdj(int[][] edges) {
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
    }
    
    private void grouping(int node, int parent, List<Integer> li) {
        li.add(node);
        List<Integer> children = adj.getOrDefault(node, List.of());
        for (int child : children) {
            if (child == parent) continue;
            grouping(child, node, li);
        }
    }
    
    private boolean correctTree(int node, int parent) {
        int n = node % 2;
        List<Integer> children = adj.getOrDefault(node, List.of());
        int cs = parent > 0 && children.size() > 0 ? children.size() - 1 : children.size();
        int c = cs % 2;
        if (n != c) {
            return false;
        }
        for (int child : children) {
            if (child == parent) continue;
            if (!correctTree(child, node)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean reverseTree(int node, int parent) {
        int n = node % 2;
        List<Integer> children = adj.getOrDefault(node, List.of());
        int cs = parent > 0 && children.size() > 0 ? children.size() - 1 : children.size();
        int c = cs % 2;
        if (n == c) {
            return false;
        }
        for (int child : children) {
            if (child == parent) continue;
            if (!reverseTree(child, node)) {
                return false;
            }
        }
        return true;
    }
    
    // 홀수: 노드 홀수, 자식수 홀수
    // 짝수: 노드 짝수, 자식수 짝수
    // 역홀수: 노드 홀수, 자식수 짝수
    // 역짝수: 노드 짝수, 자식수 홀수
    // 홀짝 트리: 홀수 노드 + 짝수 노드
    // 역홀짝 트리: 역홀수 노드 + 역짝수 노드
    // [홀짝 트리, 역홀짝 트리] 반환
    public int[] solution(int[] nodes, int[][] edges) {
        setAdj(edges);
        Set<Integer> v = new HashSet<>();
        int[] result = new int[2];
        for (int node : nodes) {
            if (v.contains(node)) continue;
            List<Integer> li = new ArrayList<>();
            grouping(node, -1, li);
            boolean correctTree = false;
            boolean reverseTree = false;
            for (int root : li) {
                if (!correctTree) {
                    correctTree = correctTree(root, -1);
                }
                if (!reverseTree) {
                    reverseTree = reverseTree(root, -1);
                }
                if (correctTree && reverseTree) break;
            }
            if (correctTree) {
                result[0]++;
            }
            if (reverseTree) {
                result[1]++;
            }
            v.addAll(li);
        }
        return result;
    }
}