import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        final int num;
        final int cost;
        
        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Node>[] adj = new List[n + 1];
        int[] minCost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            minCost[i] = 10_000_001;
        }
        for (int[] path : paths) {
            adj[path[0]].add(new Node(path[1], path[2]));
            adj[path[1]].add(new Node(path[0], path[2]));
        }
        
        boolean[] isSummit = new boolean[n + 1];
        for (int num : summits) {
            isSummit[num] = true;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int gate : gates) {
            pq.add(new Node(gate, 0));
            minCost[gate] = 0;
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost != minCost[cur.num]) continue;
            if (isSummit[cur.num]) continue;
            
            for (Node nxt : adj[cur.num]) {
                int nxtCost = Math.max(cur.cost, nxt.cost);
                if (nxtCost < minCost[nxt.num]) {
                    pq.add(new Node(nxt.num, nxtCost));
                    minCost[nxt.num] = nxtCost;
                }
            }
        }
        
        Arrays.sort(summits);
        int[] result = {0, 10_000_001};
        for (int num : summits) {
            if (minCost[num] < result[1]) {
                result[0] = num;
                result[1] = minCost[num];
            }
        }
        return result;
    }
}