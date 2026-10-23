import java.util.*;

class Solution {
    public List<Integer> solution(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        List<Integer> result = new ArrayList<>();
        boolean[][][] v = new boolean[n][m][4];
        int[][] ds = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int x = 0; x < n; x++)
            for (int y = 0; y < m; y++)
                for (int k = 0; k < 4; k++) {
                    if (v[x][y][k]) continue;
                    int cx = x;
                    int cy = y;
                    int dir = k;
                    int cnt = 0;
                    while (!v[cx][cy][dir]) {
                        v[cx][cy][dir] = true;
                        int nx = cx + ds[dir][0];
                        int ny = cy + ds[dir][1];
                        if (nx == -1 || nx == n) {
                            nx = (nx + n) % n;
                        }
                        if (ny == -1 || ny == m) {
                            ny = (ny + m) % m;
                        }
                        char ch = grid[nx].charAt(ny);
                        int ndir = dir;
                        if (ch == 'L') {
                            ndir = (dir + 1) % 4;
                        } else if (ch == 'R') {
                            ndir = (dir + 3) % 4;
                        }
                        cx = nx;
                        cy = ny;
                        dir = ndir;
                        cnt++;
                    }
                    result.add(cnt);
                }
        Collections.sort(result);
        return result;
    }
}