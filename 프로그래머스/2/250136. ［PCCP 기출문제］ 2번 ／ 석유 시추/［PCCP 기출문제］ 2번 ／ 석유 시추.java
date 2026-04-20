import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private static class SearchData {
        final int st;
        final int en;
        final int amount;
        
        public SearchData(int st, int en, int amount) {
            this.st = st;
            this.en = en;
            this.amount = amount;
        }
    }
    
    private static class Pos {
        final int x;
        final int y;
        
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    private SearchData bfs(Pos start, int[][] land, boolean[][] visited) {
        Queue<Pos> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;
        int st = visited[0].length;
        int en = 0;
        int amount = 0;
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            if (pos.y < st) {
                st = pos.y;
            }
            if (pos.y > en) {
                en = pos.y;
            }
            amount++;
            for (int[] dir : dirs) {
                int nx = pos.x + dir[0];
                int ny = pos.y + dir[1];
                if (outOfBoundary(nx, ny, land) || visited[nx][ny] || land[nx][ny] == 0) continue;
                q.add(new Pos(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return new SearchData(st, en, amount);
    }
    
    private boolean outOfBoundary(int x, int y, int[][] land) {
        return x < 0 || x >= land.length || y < 0 || y >= land[x].length;
    }
    
    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] prefixSum = new int[m + 1];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (land[x][y] == 1 && !visited[x][y]) {
                    SearchData data = bfs(new Pos(x, y), land, visited);
                    prefixSum[data.st] += data.amount;
                    prefixSum[data.en + 1] -= data.amount;
                }
            }
        }
        int result = prefixSum[0];
        for (int i = 1; i < m; i++) {            
            prefixSum[i] += prefixSum[i - 1];
            if (prefixSum[i] > result) {
                result = prefixSum[i];
            }
        }
    
        return result;
    }
}