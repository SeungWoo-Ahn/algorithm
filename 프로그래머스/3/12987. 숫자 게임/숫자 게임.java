import java.util.*;

class Solution {
    // 1 3 5 7
    // 2 2 6 8
    public int solution(int[] a, int[] b) {
        int n = a.length;
        Arrays.sort(a);
        Arrays.sort(b);
        int min = 0;
        int max = n - 1;
        int result = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] < b[max]) {
                max--;
                result++;
            } else {
                min++;
            }
            if (min > max) break;
        }
        return result;
    }
}