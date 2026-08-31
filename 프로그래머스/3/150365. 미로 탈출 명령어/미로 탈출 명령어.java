class Solution {
    private int n, m, r, c, k;
    private int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private char[] commands = {'d', 'l', 'r', 'u'};
    private String result = "";
    
    private boolean dfs(int x, int y, String path) {
        if (path.length() == k) {
            boolean found = x == r && y == c;
            if (found) {
                result = path;
            }
            return found;
        }
        int remain = k - path.length();
        int d = Math.abs(x - r) + Math.abs(y - c);
        if (d > remain || (remain - d) % 2 != 0) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
            if (dfs(nx, ny, path + commands[i])) {
                return true;
            }
        }
        return false;
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        return dfs(x, y, "") ? result : "impossible";
    }
}