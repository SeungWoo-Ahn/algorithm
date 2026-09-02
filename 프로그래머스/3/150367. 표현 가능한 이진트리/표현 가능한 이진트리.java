class Solution {
    private int solve(String s) {
        int diff = bigger2square(s.length()) - s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (diff-- > 0) sb.append(0);
        sb.append(s);
        String x = sb.toString();
        return correctTree(x, 0, x.length() - 1) ? 1: 0;
    }
    
    private int bigger2square(int target) {
        int num = 2;
        while (num <= target) {
            num *= 2;
        }
        return num;
    }
    
    private boolean correctTree(String s, int st, int en) {
        if (st >= en) {
            return true;
        }
        int mid = (st + en) >> 1;
        if (s.charAt(mid) == '0') {
            for (int i = st; i <= en; i++) {
                if (i == mid) continue;
                if (s.charAt(i) == '1') return false;
            }
            return true;
        }
        return correctTree(s, st, mid - 1) && correctTree(s, mid + 1, en);
    }
    
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = solve(Long.toString(numbers[i], 2));
        }
        return result;
    }
}