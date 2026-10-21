import java.util.*;

class Solution {
    // 2 1
    // 3
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Deque<int[]> dq = new ArrayDeque<>();
        Deque<int[]> pq = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] > 0) {
                dq.addLast(new int[]{i + 1, deliveries[i]});
            }
            if (pickups[i] > 0) {
                pq.addLast(new int[]{i + 1, pickups[i]});
            }
        }
        long result = 0L;
        while (!dq.isEmpty() || !pq.isEmpty()) {
            int dm = dq.isEmpty() ? 0 : dq.peek()[0];
            int pm = pq.isEmpty() ? 0 : pq.peek()[0];
            int max = Math.max(dm, pm);
            int dc = cap;
            while (!dq.isEmpty() && dc > 0) {
                int[] d = dq.removeFirst();
                if (d[1] <= dc) {
                    dc -= d[1];
                } else {
                    dq.addFirst(new int[]{d[0], d[1] - dc});
                    break;
                }
            }
            int pc = cap;
            while (!pq.isEmpty() && pc > 0) {
                int[] p = pq.removeFirst();
                if (p[1] <= pc) {
                    pc -= p[1];
                } else {
                    pq.addFirst(new int[]{p[0], p[1] - pc});
                    break;
                }
            }
            result += max << 1;
        }
        return result;
    }
}