import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    private static class Node implements Comparable<Node> {
        final int to;
        final int cost;
        
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
    
    private List<Node>[] adj;
    private static final int INF = 87_654_321;
    
    private void setAdj(int n, int[][] fares) {
        adj = new List[n + 1];        
        for (int i = 1; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] fare : fares) {
            adj[fare[0]].add(new Node(fare[1], fare[2]));
            adj[fare[1]].add(new Node(fare[0], fare[2]));
        }
    }
    
    private int[] dijkstra(int n, int st) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] d = new int[n + 1];
        Arrays.fill(d, INF);
        pq.add(new Node(st, 0));
        d[st] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.to] != cur.cost) continue;
            for (Node nxt : adj[cur.to]) {
                if (d[nxt.to] <= d[cur.to] + nxt.cost) continue;
                d[nxt.to] = d[cur.to] + nxt.cost;
                pq.add(new Node(nxt.to, d[nxt.to]));
            }
        }
        return d;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        setAdj(n, fares);
        int[] sd = dijkstra(n, s);
        int[] ad = dijkstra(n, a);
        int[] bd = dijkstra(n, b);
        int result = sd[a] + sd[b];
        for (int mid = 1; mid <= n; mid++) {
            int cost = sd[mid] + ad[mid] + bd[mid];
            if (cost < result) {
                result = cost;
            }
        }
        return result;
    }
}