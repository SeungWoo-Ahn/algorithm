import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        boolean[][] map = new boolean[101][101];
        for (int[] r : rectangle) {
            int x1 = r[0] * 2;
            int y1 = r[1] * 2;
            int x2 = r[2] * 2;
            int y2 = r[3] * 2;
            for (int x = x1; x <= x2; x++) {
                map[x][y1] = true;
                map[x][y2] = true;
            }
            for (int y = y1; y <= y2; y++) {
                map[x1][y] = true;
                map[x2][y] = true;
            }
        }
        for (int[] r : rectangle) {
            int x1 = r[0] * 2;
            int y1 = r[1] * 2;
            int x2 = r[2] * 2;
            int y2 = r[3] * 2;
            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    map[x][y] = false;
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[101][101];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, - 1}};
        int cost = 0;
        q.add(new int[]{characterX * 2, characterY * 2});
        v[characterX * 2][characterY * 2] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                if (cur[0] == itemX * 2 && cur[1] == itemY * 2) {
                    return cost / 2;
                }
                for (int[] dir : dirs) {
                    int nx = cur[0] + dir[0];
                    int ny = cur[1] + dir[1];
                    if (nx < 0 || nx > 100 || ny < 0 || ny > 100) continue;
                    if (!map[nx][ny] || v[nx][ny]) continue;
                    q.add(new int[]{nx, ny});
                    v[nx][ny] = true;
                }
            }
            cost++;
        }
        return -1;
    }
}