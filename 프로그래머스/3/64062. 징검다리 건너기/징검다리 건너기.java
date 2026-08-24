class Solution {
    private int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
    
    private int max(int[] arr) {
        int max = 0;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    
    private int simulate(int x, int[] stones) {
        int st = 0;
        int result = 0;
        while (st < stones.length) {
            if (stones[st] > x) {
                st++;
                continue;
            }
            int en = st;
            while (en < stones.length && stones[en] <= x) {
                en++;
            }
            int len = en - st;
            if (len > result) {
                result = len;
            }
            st = en;
        }
        return result;
    }
    
    public int solution(int[] stones, int k) {
        int st = min(stones);
        int en = max(stones);
        while (st < en) {
            int x = st + (en - st) / 2;
            int r = simulate(x, stones);
            if (r < k) {
                st = x + 1;
            } else {
                en = x;
            }
        }
        return st;
    }
}