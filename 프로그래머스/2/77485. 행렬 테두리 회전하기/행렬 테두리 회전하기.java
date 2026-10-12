class Solution {
    private int[][] makeBoard(int n, int m) {
        int[][] board = new int[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                board[x][y] = x * m + y + 1;
            }
        }
        return board;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] board = makeBoard(rows, columns);
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;
            int prev = board[x1][y1];
            int min = prev;
            for (int y = y1 + 1; y <= y2; y++) {
                int cur = board[x1][y];
                board[x1][y] = prev;
                prev = cur;
                if (prev < min) {
                    min = prev;
                }
            }
            for (int x = x1 + 1; x <= x2; x++) {
                int cur = board[x][y2];
                board[x][y2] = prev;
                prev = cur;
                if (prev < min) {
                    min = prev;
                }
            }
            for (int y = y2 - 1; y >= y1; y--) {
                int cur = board[x2][y];
                board[x2][y] = prev;
                prev = cur;
                if (prev < min) {
                    min = prev;
                }
            }
            for (int x = x2 - 1; x >= x1; x--) {
                int cur = board[x][y1];
                board[x][y1] = prev;
                prev = cur;
                if (prev < min) {
                    min = prev;
                }
            }
            result[i] = min;
        }
        return result;
    }
}