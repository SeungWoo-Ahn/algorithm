class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }
        n -= cores.length;
        int st = 1;
        int en = 10_000 * n;
        int work = 0;
        while (st < en) {
            int mid = st + (en - st) / 2;
            int res = 0;
            for (int core : cores) {
                res += (mid / core);
            }
            if (res >= n) {
                en = mid;
                work = res;
            } else {
                st = mid + 1;
            }
        }
        work -= n;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (st % cores[i] == 0) {
                if (work == 0) {
                    return i + 1;
                }
                work--;
            }
        }
        return -1;
    }
}