class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] ps = new int[n + 1][m + 1];
        for (int[] s : skill) {
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int amount = (s[0] == 1 ? -1 : 1) * s[5];
            ps[r1][c1] += amount;
            ps[r1][c2 + 1] -= amount;
            ps[r2 + 1][c1] -= amount;
            ps[r2 + 1][c2 + 1] += amount;
        }
        for (int x = 0; x < n; x++) {
            for (int y = 1; y < m; y++) {
                ps[x][y] += ps[x][y - 1];
            }
        }
        for (int y = 0; y < m; y++) {
            for (int x = 1; x < n; x++) {
                ps[x][y] += ps[x - 1][y];
            }
        }
        int result = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (board[x][y] + ps[x][y] >= 1) {
                    result++;
                }
            }
        }
        return result;
    }
}