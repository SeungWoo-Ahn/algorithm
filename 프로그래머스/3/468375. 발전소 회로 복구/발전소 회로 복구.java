import java.util.*;

class Solution {
    private int h, n, m, k;
    private int[] indegree;
    private int[][] elevd, paneld, cache;
    private List<Integer>[] children;
    private final int NONE = -1;
    private final int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    private void setElevd(String[] grid) {
        int[] elevPos = findElev(grid);
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];
        int cost = 0;
        elevd = new int[n][m];
        dq.addLast(elevPos);
        v[elevPos[0]][elevPos[1]] = true;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                int[] cur = dq.removeFirst();
                elevd[cur[0]][cur[1]] = cost;
                for (int[] d : ds) {
                    int nx = cur[0] + d[0];
                    int ny = cur[1] + d[1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if (v[nx][ny] || grid[nx].charAt(ny) == '#') continue;
                    dq.addLast(new int[]{nx, ny});
                    v[nx][ny] = true;
                }
            }
            cost++;
        }
    }
    
    private int[] findElev(String[] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length(); y++) {
                if (grid[x].charAt(y) == '@') {
                    return new int[]{x, y};
                }
            }
        }
        throw new IllegalStateException("No Elevator");
    }
    
    private void setPaneld(String[] grid, int[][] panels) {
        paneld = new int[k][k];
        for (int i = 0; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                int[] u = panels[i];
                int[] v = panels[j];
                int[] from = new int[]{u[1] - 1, u[2] - 1};
                int[] to = new int[]{v[1] - 1, v[2] - 1};
                int cost;
                if (u[0] != v[0]) {
                    cost = elevd[from[0]][from[1]] + Math.abs(u[0] - v[0]) + elevd[to[0]][to[1]];
                } else {
                    cost = distance(from, to, grid);
                }
                paneld[i][j] = cost;
                paneld[j][i] = cost;
            }
        }
    }
    
    private int distance(int[] from, int[] to, String[] grid) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];
        dq.addLast(from);
        v[from[0]][from[1]] = true;
        int cost = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                int[] cur = dq.removeFirst();
                if (cur[0] == to[0] && cur[1] == to[1])
                    return cost;
                for (int[] d : ds) {
                    int nx = cur[0] + d[0];
                    int ny = cur[1] + d[1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if (v[nx][ny] || grid[nx].charAt(ny) == '#') continue;
                    dq.addLast(new int[]{nx, ny});
                    v[nx][ny] = true;
                }
            }
            cost++;
        }
        return cost;
    }
    
    private void setChildren(int[][] seqs) {
        children = new List[k];
        indegree = new int[k];
        for (int i = 0; i < k; i++) {
            children[i] = new ArrayList<>();
        }
        for (int[] seq : seqs) {
            int a = seq[0] - 1;
            int b = seq[1] - 1;
            children[a].add(b);
            indegree[b]++;
        }
    }
    
    private void setCache() {
        cache = new int[k][1 << k];
        for (int i = 0; i < k; i++) {
            Arrays.fill(cache[i], NONE);
        }
    }
    
    private int dfs(int cur, int v) {
        if (v == (1 << k) - 1) {
            return 0;
        }
        if (cache[cur][v] != NONE) {
            return cache[cur][v];
        }
        int min = Integer.MAX_VALUE;
        for (int nxt = 0; nxt < k; nxt++) {
            if (indegree[nxt] == 0 && (v & (1 << nxt)) == 0) {
                for (int child : children[nxt]) {
                    indegree[child]--;
                }
                int cost = dfs(nxt, v | (1 << nxt)) + paneld[cur][nxt];
                min = Math.min(min, cost);
                for (int child : children[nxt]) {
                    indegree[child]++;
                }
            }
        }
        return cache[cur][v] = min;
    }
    
    // 패널: f층 r행 c열, 기술자는 1번에서 시작
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        this.h = h;
        this.n = grid.length;
        this.m = grid[0].length();
        this.k = panels.length;
        setElevd(grid);
        setPaneld(grid, panels);
        setChildren(seqs);
        setCache();
        int result = Integer.MAX_VALUE;
        for (int nxt = 0; nxt < k; nxt++) {
            if (indegree[nxt] != 0) continue;
            for (int child : children[nxt]) {
                indegree[child]--;
            }
            int cost = dfs(nxt, 1 << nxt) + paneld[0][nxt];
            result = Math.min(result, cost);
            for (int child : children[nxt]) {
                indegree[child]++;
            }
        }
        return result;
    }
}