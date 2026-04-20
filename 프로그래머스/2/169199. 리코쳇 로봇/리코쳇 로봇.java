import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    private static class Pos {
        final int x;
        final int y;
        
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private Pos start, goal;
    private boolean[][] map;
    
    private void setAttrs(int n, int m, String[] board) {
        map = new boolean[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                char ch = board[x].charAt(y);
                if (ch == 'R') {
                    start = new Pos(x, y);
                } else if (ch == 'G') {
                    goal = new Pos(x, y);
                }
                map[x][y] = ch != 'D';
            }
        }
    }
    
    private int[][] getDis(int n, int m) {
        int[][] dis = new int[n][m];
        for (int[] arr : dis) {
            Arrays.fill(arr, 100_000);
        }
        return dis;
    }
    
    private Pos getNextPos(Pos cur, int[] dir) {
        int x = cur.x;
        int y = cur.y;
        while (true) {
            int nx = x + dir[0];
            int ny = y + dir[1];            
            if (outOfBoundary(nx, ny) || !map[nx][ny]) break;
            x = nx;
            y = ny;
        }
        return new Pos(x, y);
    }
    
    private boolean outOfBoundary(int x, int y) {
        return x < 0 || x >= map.length || y < 0 || y >= map[x].length;
    }
    
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        setAttrs(n, m, board);
        Queue<Pos> q = new LinkedList<>();
        int[][] dis = getDis(n, m);
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        q.add(start);
        dis[start.x][start.y] = 0;
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (cur.x == goal.x && cur.y == goal.y) {
                return dis[cur.x][cur.y];
            }
            for (int[] dir : dirs) {
                Pos next = getNextPos(cur, dir);
                if (dis[next.x][next.y] <= dis[cur.x][cur.y] + 1) continue;
                q.add(next);
                dis[next.x][next.y] = dis[cur.x][cur.y] + 1;
            }
        }
        return -1;
    }
}