class Solution {
    // 0: 1
    // 1: 11011
    // 2: 110[11 / 11011 / 00000 / 11]011 / 11011
    
    // 4 -> 0
    // 5 -> 1
    // 14 -> 2
    // 16 -> 3, 1
    // 17 -> 3, 2
    public int solution(int n, long l, long r) {
        int result = 0;
        for (long i = l - 1; i < r; i++) {
            if (i % 5 == 2) continue;
            long cur = i;
            boolean one = true;
            for (int j = 0; j < n - 1; j++) {
                cur /= 5;
                if (cur % 5 == 2) {
                    one = false;
                    break;
                }
            }
            if (one)
                result++;
        }
        return result;
    }
}