import java.util.*;

class Solution {
    // 피로도 = 남은 작업량 제곱의 합
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }
        while (n-- > 0) {
            int max = pq.poll();
            if (max == 0) return 0L;
            pq.add(max - 1);
        }
        long result = 0L;
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            result += cur * cur;
        }
        return result;
    }
}