import java.util.*;

class Solution {
    private List<int[]> rotate(int m, List<int[]> keys) {
        List<int[]> nkeys = new ArrayList<>();
        for (int[] key : keys) {
            nkeys.add(new int[]{key[1], m - key[0] - 1});
        }
        return nkeys;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int[] firstHole = new int[2];
        int holeCnt = 0;
        for (int x = 0; x < n; x++)
            for (int y = 0; y < n; y++)
                if (lock[x][y] == 0) {
                    if (holeCnt == 0) {
                        firstHole[0] = x;
                        firstHole[1] = y;
                    }
                    holeCnt++;
                }
        if (holeCnt == 0) {
            return true;
        }
        int m = key.length;
        List<int[]> keys = new ArrayList<>();
        for (int x = 0; x < m; x++)
            for (int y = 0; y < m; y++)
                if (key[x][y] == 1) {
                    keys.add(new int[]{x, y});
                }
        for (int i = 0; i < 4; i++) {
            for (int[] st : keys) {
                int dx = firstHole[0] - st[0];
                int dy = firstHole[1] - st[1];
                int match = 0;
                for (int[] k : keys) {
                    int x = k[0] + dx;
                    int y = k[1] + dy;
                    if (x < 0 || x >= n || y < 0 || y >= n) continue;
                    if (lock[x][y] == 1) {
                        match = -1;
                        break;
                    } else {
                        match++;
                    }
                }
                if (match == holeCnt) return true;
            }
            keys = rotate(m, keys);
        }
        return false;
    }
}