class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] win = new boolean[n + 1][n + 1];
        for (int[] result : results) {
            win[result[0]][result[1]] = true;
        }
        for (int k = 1; k <= n; k++)
            for (int x = 1; x <= n; x++)
                for (int y = 1; y <= n; y++) {
                    if (win[x][k] && win[k][y]) {
                        win[x][y] = true;
                    }
                }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (win[i][j] || win[j][i]) {
                    cnt++;
                }
            }
            if (cnt == n - 1) {
                result++;
            }
        }
        return result;    
    }
}