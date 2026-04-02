import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private List<Integer>[] getAdj(int n, int[][] edges) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        return adj;
    }
    
    private int[] bfs(List<Integer>[] adj) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.length];
        int[] d = new int[adj.length];
        q.offer(1);
        visited[1] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : adj[cur]) {
                if (visited[nxt]) continue;
                q.offer(nxt);
                visited[nxt] = true;
                d[nxt] = d[cur] + 1;
            }
        }
        return d;
    }
    
    private int getMax(int[] arr) {
        int max = 0;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    
    private int getCnt(int target, int[] arr) {
        int cnt = 0;
        for (int num : arr) {
            if (num == target) {
                cnt++;
            }
        }
        return cnt;
    }
    
    public int solution(int n, int[][] edges) {
        List<Integer>[] adj = getAdj(n, edges);
        int[] d = bfs(adj);
        int max = getMax(d);
        return getCnt(max, d);
    }
}