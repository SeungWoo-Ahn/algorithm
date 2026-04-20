class Solution {
    private int getMaxDiff(int[] diffs) {
        int max = 0;
        for (int diff : diffs) {
            if (diff > max) {
                max = diff;
            }
        }
        return max;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        int st = 1;
        int en = getMaxDiff(diffs);
        while (st <= en) {
            int mid = st + ((en - st) >> 1);
            long total = times[0];
            boolean complete = true;
            for (int i = 1; i < diffs.length; i++) {
                if (diffs[i] <= mid) {
                    total += times[i];
                } else {
                    total += (diffs[i] - mid) * (times[i] + times[i - 1]) + times[i];
                }
                if (total > limit) {
                    complete = false;
                    break;
                }
            }
            if (complete) {
                en = mid - 1;
            } else {
                st = mid + 1;
            }
        }
        return st;
    }
}