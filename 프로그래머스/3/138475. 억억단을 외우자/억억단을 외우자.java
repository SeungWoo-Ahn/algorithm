import java.util.*;

class Solution {
    // s ~ e에서 약수 개수가 가장 많은 수 중 가장 작은 수
    // 1 2 3 4 5 6 7 8
    // 1 2 2 3 2 4 2 4
    public int[] solution(int e, int[] starts) {
        int[] cnt = new int[e + 1];
        Arrays.fill(cnt, 1);
        for (int i = 2; i <= e; i++) {
            cnt[i]++;
            for (int r = 2; r <= e; r++) {
                if (i * r > e) break;
                cnt[i * r]++;
            }
        }
        int[] max = new int[e + 1];
        max[e] = e;
        for (int i = e - 1; i >= 1; i--) {
            if (cnt[i] >= cnt[max[i + 1]]) {
                max[i] = i;
            } else {
                max[i] = max[i + 1];
            }
        }
        int[] result = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            result[i] = max[starts[i]];
        }
        return result;
    }
}