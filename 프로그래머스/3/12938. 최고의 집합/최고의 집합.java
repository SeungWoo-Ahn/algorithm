import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[]{-1};
        }
        int base = s / n;
        int rest = s % n;
        int[] result = new int[n];
        Arrays.fill(result, base);
        for (int i = n - rest; i < n; i++) {
            result[i]++;
        }
        return result;
    }
}