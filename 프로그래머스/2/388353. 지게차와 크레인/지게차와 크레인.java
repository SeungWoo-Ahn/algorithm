import java.util.*;

class Solution {
    private int n, m;
    private char[][] map;
    private boolean[][] outer;
    private final int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final char EMPTY = '.';
    
    private void setMap(String[] storage) {
        map = new char[n][m];
        for (int x = 0; x < n; x++)
            for (int y = 0; y < m; y++)
                map[x][y] = storage[x].charAt(y);
    }
    
    private void setOuter() {
        outer = new boolean[n][m];
    }
    
    private int useCart(char target) {
        List<int[]> pos = new ArrayList<>();
        for (int x = 0; x < n; x++)
            for (int y = 0; y < m; y++)
                if (map[x][y] == target && adjOuter(x, y)) {
                    map[x][y] = EMPTY;
                    pos.add(new int[]{x, y});
                }
        for (int[] p : pos) {
            spread(p[0], p[1]);
        }
        return pos.size();
    }
    
    private boolean adjOuter(int x, int y) {
        for (int[] d : ds) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || outer[nx][ny])
                return true;
        }
        return false;
    }
    
    private int useCrane(char target) {
        List<int[]> pos = new ArrayList<>();
        for (int x = 0; x < n; x++)
            for (int y = 0; y < m; y++)
                if (map[x][y] == target) {
                    map[x][y] = EMPTY;
                    pos.add(new int[]{x, y});
                }
        for (int[] p : pos) {
            if (!outer[p[0]][p[1]] && adjOuter(p[0], p[1]))
                spread(p[0], p[1]);
        }
        return pos.size();
    }
    
    private void spread(int sx, int sy) {
        if (outer[sx][sy]) return;
        Deque<int[]> q = new ArrayDeque<>();
        outer[sx][sy] = true;
        q.add(new int[]{sx, sy});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int[] d : ds) {
                int nx = p[0] + d[0], ny = p[1] + d[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (map[nx][ny] == EMPTY && !outer[nx][ny]) {
                    outer[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        setMap(storage);
        setOuter();
        int result = n * m;
        for (String r : requests) {
            char target = r.charAt(0);
            if (r.length() == 1) {
                result -= useCart(target);
            } else {
                result -= useCrane(target);
            }
        }
        return result;
    }
}