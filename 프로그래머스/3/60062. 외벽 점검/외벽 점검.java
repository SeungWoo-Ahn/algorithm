import java.util.*;

class Solution {
    private int len;
    private int[] gap, dist;
    
    private boolean perm(int depth, int[] arr, boolean[] v) {
        if (depth == arr.length) {
            return check(arr);
        }
        for (int i = dist.length - 1; i > dist.length - arr.length - 1; i--) {
            if (v[i]) continue;
            v[i] = true;
            arr[depth] = dist[i];
            if (perm(depth + 1, arr, v)) {
                return true;
            }
            v[i] = false;
        }
        return false;
    }
    
    private boolean check(int[] arr) {
        for (int st = 0; st < len; st++) {
            int idx = st;
            int cnt = 0;
            for (int d : arr) {
                while (d - gap[idx %= len] >= 0) {
                    d -= gap[idx++];
                    cnt++;
                }
                idx++;
                cnt++;
                if (cnt >= len) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        len = weak.length;
        gap = new int[len];
        for (int i = 1; i < len; i++) {
            gap[i - 1] = weak[i] - weak[i - 1];
        }
        gap[len - 1] = weak[0] + n - weak[len - 1];
        
        this.dist = dist;
        Arrays.sort(dist);
        
        boolean[] v = new boolean[dist.length];
        for (int cnt = 1; cnt <= dist.length; cnt++) {
            if (perm(0, new int[cnt], v)) {
                return cnt;
            }
        }
        return -1;
    }
}