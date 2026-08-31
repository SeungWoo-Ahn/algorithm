import java.util.*;

class Solution {
    private boolean[][][] map;
    
    private boolean correct(int x, int y, int target) {
        if (target == 0) {
            return correctGidung(x, y);
        }
        return correctBow(x, y);
    }
    
    private boolean correctGidung(int x, int y) {
        if (y == 0) return true;
        if (map[x][y][1]) return true;
        if (x > 0 && map[x - 1][y][1]) return true;
        return map[x][y - 1][0];
    }
    
    private boolean correctBow(int x, int y) {
        if (y > 0 && map[x][y - 1][0]) return true;
        if (y > 0 && map[x + 1][y - 1][0]) return true;
        return (x > 0 && map[x - 1][y][1]) && (x + 1 < map.length && map[x + 1][y][1]);
    }
    
    public List<int[]> solution(int n, int[][] build_frame) {
        map = new boolean[n + 1][n + 1][2];
        int[][] nearGidung = {{0, 1, 0}, {0, 1, 1}, {-1, 1, 1}};
        int[][] nearBow = {{0, 0, 0}, {1, 0, 0}, {-1, 0, 1}, {1, 0, 1}};
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];
            if (b == 0) {
                map[x][y][a] = false;
                int[][] near = a == 0 ? nearGidung : nearBow;
                for (int[] d : near) {
                    int nx = x + d[0];
                    int ny = y + d[1];
                    int na = d[2];
                    if (nx < 0 || nx >= map.length || ny < 0 || ny >= map.length) continue;
                    if (!map[nx][ny][na]) continue;
                    if (!correct(nx, ny, na)) {
                        map[x][y][a] = true;
                        break;
                    }
                }
            } else {
                map[x][y][a] = true;
                if (!correct(x, y, a)) {
                    map[x][y][a] = false;
                }
            }
        }
        List<int[]> result = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                for (int a = 0; a <= 1; a++) {
                    if (map[x][y][a]) {
                        result.add(new int[]{x, y, a});
                    }
                }
            }
        }
        return result;
    }
}