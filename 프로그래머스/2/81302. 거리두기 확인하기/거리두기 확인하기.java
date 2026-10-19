import java.util.*;

class Solution {
    private boolean check(String[] map) {
        List<int[]> c = new ArrayList<>();
        for (int x = 0; x < 5; x++)
            for (int y = 0; y < 5; y++)
                if (map[x].charAt(y) == 'P') {
                    c.add(new int[]{x, y});
                }
        for (int i = 0; i < c.size() - 1; i++)
            for (int j = i + 1; j < c.size(); j++) {
                int[] u = c.get(i);
                int[] v = c.get(j);
                int d = d(u, v);
                if (d > 2) continue;
                if (d == 1) return false;
                if (u[0] == v[0]) {
                    if (map[u[0]].charAt(u[1] + 1) != 'X')
                        return false;
                } else if (u[1] == v[1]) {
                    if (map[u[0] + 1].charAt(u[1]) != 'X')
                        return false;
                } else {
                    if (map[u[0]].charAt(v[1]) != 'X' ||
                       map[v[0]].charAt(u[1]) != 'X')
                        return false;
                }
            }
        return true;
    }
    
    private int d(int[] u, int[] v) {
        return Math.abs(u[0] - v[0]) + Math.abs(u[1] - v[1]);
    }
    
    public int[] solution(String[][] places) {
        int[] result = new int[5];
        for (int i = 0; i < 5; i++) {
            result[i] = check(places[i]) ? 1: 0;
        }
        return result;
    }
}