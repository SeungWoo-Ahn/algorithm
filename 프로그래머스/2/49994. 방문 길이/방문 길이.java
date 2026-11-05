import java.util.*;

class Solution {
    public int solution(String dirs) {
        List<Character> op = List.of('U', 'R', 'D', 'L');
        int[][] ds = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Set<String> history = new HashSet<>();
        int x = 0;
        int y = 0;
        for (int i = 0; i < dirs.length(); i++) {
            char ch = dirs.charAt(i);
            int idx = op.indexOf(ch);
            int[] d = ds[idx];
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx > 5 || nx < -5 || ny > 5 || ny < -5) continue;
            String path1 = x + " " + y + " " + ch;
            String path2 = nx + " " + ny + " " + op.get((idx + 2) % 4);
            x = nx;
            y = ny;
            history.add(path1);
            history.add(path2);
        }
        return history.size() >> 1;
    }
}