import java.util.*;

class Solution {
    private char[][] b;
    private int[][] dirs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    private static final char EMPTY = '-';
    
    private int remove(int n, int m) {
        List<int[]> removed = new ArrayList<>();
        for (int x = 0; x < n - 1; x++) {
            for (int y = 0; y < m - 1; y++) {
                if (b[x][y] != EMPTY && 
                    b[x + 1][y] == b[x][y] && 
                    b[x][y + 1] == b[x][y] && 
                    b[x + 1][y + 1] == b[x][y]) {
                    removed.add(new int[]{x, y});
                }
            }
        }
        int cnt = 0;
        for (int[] pos : removed) {
            for (int[] d : dirs) {
                int x = pos[0] + d[0];
                int y = pos[1] + d[1];
                if (b[x][y] != EMPTY) {
                    b[x][y] = EMPTY;
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    private void fall(int n, int m) {
        for (int y = 0; y < m; y++) {
            int mx = 0;
            for (int x = n - 1; x >= 0; x--) {
                if (b[x][y] == EMPTY) {
                    mx = x;
                    break;
                }
            }
            List<Character> li = new ArrayList<>();
            for (int x = mx - 1; x >= 0; x--) {
                if (b[x][y] != EMPTY) {
                    li.add(b[x][y]);
                    b[x][y] = EMPTY;
                }
            }
            for (char ch : li) {
                b[mx--][y] = ch;
            }
        }
    }
    
    public int solution(int n, int m, String[] board) {
        b = new char[n][m];
        for (int i = 0; i < n; i++) {
            b[i] = board[i].toCharArray();
        }
        int result = 0;
        while (true) {
            int removed = remove(n, m);
            if (removed == 0) break;
            result += removed;
            fall(n, m);
        }
        return result;
    }
}