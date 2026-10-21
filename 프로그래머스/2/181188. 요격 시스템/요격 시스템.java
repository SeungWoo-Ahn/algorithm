import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> {
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        int idx = 0;
        int result = 0;
        while (idx < targets.length) {
            int[] cur = targets[idx];
            while (idx < targets.length - 1 && targets[idx + 1][0] < cur[1]) {
                idx++;
            }
            idx++;
            result++;
        }
        return result;
    }
}