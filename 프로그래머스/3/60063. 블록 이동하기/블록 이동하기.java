import java.util.*;

class Solution {
    private boolean arrive(int n, int[] pos) {
        return (pos[0] == n - 1 && pos[1] == n - 1) ||
            (pos[2] == n - 1 && pos[3] == n - 1);
    }
    
    private boolean oob(int n, int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
    
    public int solution(int[][] board) {
        int n = board.length;
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][][][] v = new boolean[n][n][n][n];
        int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int seq = 0;
        dq.add(new int[]{0, 0, 0, 1});
        v[0][0][0][1] = true;
        v[0][1][0][0] = true;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                int[] cur = dq.removeFirst();
                if (arrive(n, cur)) {
                    return seq;
                }
                // 이동
                for (int[] d : ds) {
                    int nx1 = cur[0] + d[0];
                    int ny1 = cur[1] + d[1];
                    int nx2 = cur[2] + d[0];
                    int ny2 = cur[3] + d[1];
                    if (oob(n, nx1, ny1) || oob(n, nx2, ny2)) continue;
                    if (v[nx1][ny1][nx2][ny2] || v[nx2][ny2][nx1][ny1]) continue;
                    if (board[nx1][ny1] == 1 || board[nx2][ny2] == 1) continue;
                    dq.addLast(new int[]{nx1, ny1, nx2, ny2});
                    v[nx1][ny1][nx2][ny2] = true;
                    v[nx2][ny2][nx1][ny1] = true;
                }
                // 회전
                if (cur[0] == cur[2]) { // 가로 방향
                    for (int mode = 0; mode < 2; mode++) {
                        int sx = mode == 0 ? cur[0] : cur[2];
                        int sy = mode == 0 ? cur[1] : cur[3];
                        int cy = mode == 0 ? cur[3] : cur[1];
                        for (int i = 0; i < 2; i++) {
                            int tx = sx + ds[i][0];
                            int ty = sy;
                            if (oob(n, tx, ty)) continue;
                            if (board[tx][ty] == 1 || board[tx][cy] == 1) continue;
                            if (v[sx][sy][tx][ty] || v[tx][ty][sx][sy]) continue;
                            dq.addLast(new int[]{sx, sy, tx, ty});
                            v[sx][sy][tx][ty] = true;
                            v[tx][ty][sx][sy] = true;
                        }
                    }
                } else { // 세로 방향
                    for (int mode = 0; mode < 2; mode++) {
                        int sx = mode == 0 ? cur[0] : cur[2];
                        int sy = mode == 0 ? cur[1] : cur[3];
                        int cx = mode == 0 ? cur[2] : cur[0];
                        for (int i = 2; i < 4; i++) {
                            int tx = sx;
                            int ty = sy + ds[i][1];
                            if (oob(n, tx, ty)) continue;
                            if (board[tx][ty] == 1 || board[cx][ty] == 1) continue;
                            if (v[sx][sy][tx][ty] || v[tx][ty][sx][sy]) continue;
                            dq.addLast(new int[]{sx, sy, tx, ty});
                            v[sx][sy][tx][ty] = true;
                            v[tx][ty][sx][sy] = true;
                        }
                    }
                }
            }
            seq++;
        }
        return -1;
    }
}