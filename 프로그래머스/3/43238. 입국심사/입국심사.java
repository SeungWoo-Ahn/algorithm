class Solution {
    private long getMax() {
        long max = 1L;
        for (int i = 0; i < 18; i++) {
            max *= 10;
        }
        return max;
    }
    
    public long solution(int n, int[] times) {
        long st = 1L;
        long en = getMax();
        long result = Long.MAX_VALUE;
        while (st <= en) {
            long mid = st + ((en - st) >> 1);
            long people = 0L;
            for (int time : times) {
                people += mid / time;
            }
            if (people < n) {
                st = mid + 1;
            } else {
                en = mid - 1;
                if (mid < result) {
                    result = mid;
                }
            }
        }
        return result;
    }
}