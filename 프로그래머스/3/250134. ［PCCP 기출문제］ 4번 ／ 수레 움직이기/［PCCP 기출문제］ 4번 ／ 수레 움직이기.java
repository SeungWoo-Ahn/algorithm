class Solution {
    private int n, m, result = Integer.MAX_VALUE;
    private int[] rs, re, bs, be;
    private boolean[][] wall;
    private final int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    private void setProps(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        wall = new boolean[n][m];
        for (int x = 0; x < n; x++)
            for (int y = 0; y < m; y++) {
                int num = maze[x][y];
                if (num == 1) {
                    rs = new int[]{x, y};
                } else if (num == 2) {
                    bs = new int[]{x, y};
                } else if (num == 3) {
                    re = new int[]{x, y};
                } else if (num == 4) {
                    be = new int[]{x, y};
                } else if (num == 5) {
                    wall[x][y] = true;
                }
            }
    }
    
    private void backtracking(int depth, int rx, int ry, int bx, int by, boolean[][] rv, boolean[][] bv) {
        if (arrive(rx, ry, re) && arrive(bx, by, be)) { // 모두 도착
            if (depth < result) {
                result = depth;
            }
            return;
        }
        if (arrive(rx, ry, re)) { // red 도착
            for (int[] d : ds) {
                int nx = bx + d[0];
                int ny = by + d[1];
                if (oob(nx, ny) || bv[nx][ny] || wall[nx][ny]) continue;
                if (nx == rx && ny == ry) continue;
                bv[nx][ny] = true;
                backtracking(depth + 1, rx, ry, nx, ny, rv, bv);
                bv[nx][ny] = false;
            }
        } else if (arrive(bx, by, be)) { // blue 도착
            for (int[] d : ds) {
                int nx = rx + d[0];
                int ny = ry + d[1];
                if (oob(nx, ny) || rv[nx][ny] || wall[nx][ny]) continue;
                if (nx == bx && ny == by) continue;
                rv[nx][ny] = true;
                backtracking(depth + 1, nx, ny, bx, by, rv, bv);
                rv[nx][ny] = false;
            }
        } else { // 도착 X
            for (int[] rd : ds) { // red 먼저
                int rnx = rx + rd[0];
                int rny = ry + rd[1];
                if (oob(rnx, rny) || rv[rnx][rny] || wall[rnx][rny]) continue;
                if (rnx == bx && rny == by) continue;
                rv[rnx][rny] = true;
                for (int[] bd : ds) {
                    int bnx = bx + bd[0];
                    int bny = by + bd[1];
                    if (oob(bnx, bny) || bv[bnx][bny] || wall[bnx][bny]) continue;
                    if (rnx == bnx && rny == bny) continue;
                    bv[bnx][bny] = true;
                    backtracking(depth + 1, rnx, rny, bnx, bny, rv, bv);
                    bv[bnx][bny] = false;
                }
                rv[rnx][rny] = false;
            }
            for (int[] bd : ds) { // blue 먼저
                int bnx = bx + bd[0];
                int bny = by + bd[1];
                if (oob(bnx, bny) || bv[bnx][bny] || wall[bnx][bny]) continue;
                if (bnx == rx && bny == ry) continue;
                bv[bnx][bny] = true;
                for (int[] rd : ds) {
                    int rnx = rx + rd[0];
                    int rny = ry + rd[1];
                    if (oob(rnx, rny) || rv[rnx][rny] || wall[rnx][rny]) continue;
                    if (rnx == bnx && rny == bny) continue;
                    rv[rnx][rny] = true;
                    backtracking(depth + 1, rnx, rny, bnx, bny, rv, bv);
                    rv[rnx][rny] = false;
                }
                bv[bnx][bny] = false;
            }
        }
    }
    
    private boolean arrive(int x, int y, int[] dest) {
        return x == dest[0] && y == dest[1];
    }
    
    private boolean oob(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
    
    // 각 턴마다 모든 수레를 한 칸 움직여야 함
    // 자신이 방문한 칸으로 움직일 수 없음
    // 도착한 수레는 움직이지 않음
    // 동시에 같은 칸으로 움직일 수 없음
    // 서로 자리를 바꿀 수 없음
    // 퍼즐 푸는데 필요한 최소 턴, 풀 수 없으면 0
    public int solution(int[][] maze) {
        setProps(maze);
        boolean[][] rv = new boolean[n][m];
        boolean[][] bv = new boolean[n][m];
        rv[rs[0]][rs[1]] = true;
        bv[bs[0]][bs[1]] = true;
        backtracking(0, rs[0], rs[1], bs[0], bs[1], rv, bv);
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}