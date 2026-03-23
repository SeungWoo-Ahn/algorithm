class Solution {
    private int getBinaryOneCnt(int num) {
        int oneCnt = 0;
        while (num > 0) {
            if (num % 2 == 1) {
                oneCnt++;
            }
            num /= 2;
        }
        return oneCnt;
    }
    
    public int solution(int n) {
        int target = getBinaryOneCnt(n);
        while (true) {
            n++;
            int oneCnt = getBinaryOneCnt(n);
            if (oneCnt == target) break;
        }
        return n;
    }
}