class Solution {
    private long simulate(long x, int[] times) {
        long result = 0;
        for (int time : times) {
            result += x / time;
        }
        return result;
    }
    
    public long solution(int n, int[] times) {
        long st = 1L;
        long en = 1_000_000_000L * 1_000_000_000L;
        while (st < en) {
            long x = st + (en - st) / 2;
            long r = simulate(x, times);
            if (r < n) {
                st = x + 1;
            } else {
                en = x;
            }
        }
        return st;
    }
}