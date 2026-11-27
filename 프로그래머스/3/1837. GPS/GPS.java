import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        final int num;
        final int error;
        final int time;
        
        public Node(int num, int error, int time) {
            this.num = num;
            this.error = error;
            this.time = time;
        }
        
        @Override
        public int compareTo(Node o) {
            if (error != o.error) {
                return error - o.error;
            }
            return time - o.time;
        }
    }
    
    private List<Integer>[] getAdj(int n, int[][] edge_list) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            adj[i].add(i);
        }
        for (int[] edge : edge_list) {
            int u = edge[0];
            int v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        return adj;
    }
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<Integer>[] adj = getAdj(n, edge_list);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][][] v = new boolean[n + 1][k + 1][k + 1];
        pq.add(new Node(gps_log[0], 0, 0));
        v[gps_log[0]][0][0] = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int nextTime = cur.time + 1;
            int nextGpsNum = gps_log[nextTime];
            for (int nextNum : adj[cur.num]) {
                if (nextTime == k - 1 && nextNum == gps_log[k - 1]) {
                    return cur.error;
                }
                int nextError = nextNum == nextGpsNum ? cur.error : cur.error + 1;
                if (nextTime >= k - 1 || v[nextNum][nextError][nextTime]) {
                    continue;
                }
                pq.add(new Node(nextNum, nextError, nextTime));
                v[nextNum][nextError][nextTime] = true;
            }
        }
        return -1;
    }
}