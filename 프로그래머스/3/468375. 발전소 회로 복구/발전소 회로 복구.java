import java.util.*;

class Solution {
    private int n, m, k, NONE = -1;
    private int[] indegree;
    private int[][] panels, elevd, cached, dp, dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private String[] grid;
    private List<Integer>[] adj;
    
    private void setIndegree(int[][] seqs) {
        indegree = new int[k];
        adj = new List[k];
        for (int i = 0; i < k; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] seq : seqs) {
            int u = seq[0] - 1;
            int v = seq[1] - 1;
            indegree[v]++;
            adj[u].add(v);
        }
    }
    
    private void setElevd() {
        elevd = new int[n][m];
        int sx = 0, sy = 0;
        for (int x = 0; x < n; x++) {
            boolean found = false;
            for (int y = 0; y < m; y++) {
                if (grid[x].charAt(y) == '@') {
                    found = true;
                    sx = x;
                    sy = y;
                    break;
                }
            }
            if (found) break;
        }
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];
        dq.addLast(new int[]{sx, sy});
        v[sx][sy] = true;
        while (!dq.isEmpty()) {
            int[] cur = dq.removeFirst();
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];
                if (oob(nx, ny) || v[nx][ny]) continue;
                if (grid[nx].charAt(ny) == '#') continue;
                dq.addLast(new int[]{nx, ny});
                v[nx][ny] = true;
                elevd[nx][ny] = elevd[cur[0]][cur[1]] + 1;
            }
        }
    }
    
    private boolean oob(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
    
    private void setCached() {
        cached = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    cached[i][j] = NONE;
                }
            }
        }
    }
    
    private void setDp() {
        dp = new int[k][1 << k];
        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], NONE);
        }
    }
    
    private int dfs(int cur, int v) {
        if (v == (1 << k) - 1) {
            return 0;
        }
        if (dp[cur][v] != NONE) {
            return dp[cur][v];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            if (indegree[i] == 0 && (v & (1 << i)) == 0) {
                for (int nxt : adj[i]) {
                    indegree[nxt]--;
                }
                if (cached[cur][i] == NONE) {
                    int d = getd(cur, i);
                    cached[cur][i] = cached[i][cur] = d;
                }                
                min = Math.min(min, dfs(i, v | (1 << i)) + cached[cur][i]);
                for (int nxt : adj[i]) {
                    indegree[nxt]++;
                }
            }
        }
        return dp[cur][v] = min;
    }
    
    private int getd(int a, int b) {
        if (a == b) {
            return 0;
        }
        int[] from = panels[a];
        int[] to = panels[b];
        if (from[0] != to[0]) {
            return elevd[from[1] - 1][from[2] - 1] + 
                Math.abs(from[0] - to[0]) + 
                elevd[to[1] - 1][to[2] - 1];
        }
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];
        dq.addLast(new int[]{from[1] - 1, from[2] - 1});
        int d = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                int[] cur = dq.removeFirst();
                if (cur[0] == to[1] - 1 && cur[1] == to[2] - 1) {
                    return d;
                }
                for (int[] dir : dirs) {
                    int nx = cur[0] + dir[0];
                    int ny = cur[1] + dir[1];
                    if (oob(nx, ny) || v[nx][ny]) continue;
                    if (grid[nx].charAt(ny) == '#') continue;
                    dq.addLast(new int[]{nx, ny});
                    v[nx][ny] = true;
                }
            }
            d++;
        }
        return d;
    }
    
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        n = grid.length;
        m = grid[0].length();
        k = panels.length;
        this.panels = panels;
        this.grid = grid;
        setIndegree(seqs);
        setElevd();
        setCached();
        setDp();
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            if (indegree[i] != 0) continue;
            for (int nxt : adj[i]) {
                indegree[nxt]--;
            }
            if (cached[0][i] == NONE) {
                int d = getd(0, i);
                cached[0][i] = cached[i][0] = d;
            }
            result = Math.min(result, dfs(i, 1 << i) + cached[0][i]);
            for (int nxt : adj[i]) {
                indegree[nxt]++;
            }
        }
        return result;
    }
}