import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    private static class Road implements Comparable<Road> {
        final int to;
        final int cost;
        
        public Road(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Road o) {
            return cost - o.cost;
        }
    }
    
    private List<Road>[] getAdj(int n, int[][] roads) {
        List<Road>[] adj = new List[n + 1];
        for (int i = 1; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            int cost = road[2];
            adj[a].add(new Road(b, cost));
            adj[b].add(new Road(a, cost));
        }
        return adj;
    }
    
    private int[] dijkstra(List<Road>[] adj) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        int[] d = new int[adj.length];
        for (int i = 0; i < d.length; i++) {
            d[i] = 500_001;
        }
        pq.add(new Road(1, 0));
        d[1] = 0;
        while (!pq.isEmpty()) {
            Road cur = pq.poll();
            if (cur.cost != d[cur.to]) continue;
            for (Road nxt : adj[cur.to]) {
                if (d[nxt.to] <= d[cur.to] + nxt.cost) continue;
                d[nxt.to] = d[cur.to] + nxt.cost;
                pq.add(new Road(nxt.to, d[nxt.to]));
            }
        }
        return d;
    }
    
    public int solution(int n, int[][] roads, int k) {
        List<Road>[] adj = getAdj(n, roads);
        int[] d = dijkstra(adj);
        int result = 0;
        for (int distance : d) {
            if (distance <= k) {
                result++;
            }
        }
        return result;
    }
}