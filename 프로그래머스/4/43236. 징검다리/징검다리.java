import java.util.Arrays;

class Solution {
    private int[] getTotalRocks(int distance, int[] rocks) {
        Arrays.sort(rocks);
        int[] totalRocks = new int[rocks.length + 2];
        for (int i = 0; i < rocks.length; i++) {
            totalRocks[i + 1] = rocks[i];
        }
        totalRocks[totalRocks.length - 1] = distance;
        return totalRocks;
    }
    
    private int solve(int target, int[] rocks) {
        int cnt = 0;
        int st = 0;
        while (st < rocks.length) {
            int en = st + 1;
            while (en < rocks.length && rocks[en] - rocks[st] < target) {
                en++;
            }
            cnt += en - st - 1;
            st = en;
        }
        return cnt;
    }
    
    public int solution(int distance, int[] rocks, int n) {
        int[] totalRocks = getTotalRocks(distance, rocks);
        int st = 1;
        int en = distance;
        int answer = -1;
        while (st <= en) {
            int mid = (st + en) >> 1;
            int result = solve(mid, totalRocks);
            if (result <= n) {
                st = mid + 1;
                answer = mid;
            } else {
                en = mid - 1;
            }
        }
        return answer;
    }
}