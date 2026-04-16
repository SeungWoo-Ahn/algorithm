import java.util.Arrays;

class Solution {
    private boolean[] getExist(int n, int[] lost) {
        boolean[] exist = new boolean[n + 2];
        for (int i = 1; i <= n; i++) {
            exist[i] = true;
        }
        for (int i : lost) {
            exist[i] = false;
        }
        return exist;
    }
    
    private boolean[] getRemain(int n, int[] reserve) {
        boolean[] remain = new boolean[n + 2];
        for (int i : reserve) {
            remain[i] = true;
        }
        return remain;
    }
    
    public int solution(int n, int[] lost, int[] reserve) {
        boolean[] exist = getExist(n, lost);
        boolean[] remain = getRemain(n, reserve);
        for (int i = 1; i <= n; i++) {
            if (!exist[i] && remain[i]) {
                exist[i] = true;
                remain[i] = false;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (exist[i]) continue;
            if (remain[i - 1]) {
                exist[i] = true;
                remain[i - 1] = false;
            } else if (remain[i + 1]) {
                exist[i] = true;
                remain[i + 1] = false;
            }
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (exist[i]) {
                result++;
            }
        }
        return result;
    }
}