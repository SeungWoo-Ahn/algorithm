import java.util.*;

class Solution {
    record Request(int in, int time) {}
    
    private List<Request>[] rs;
    private int[][] cache;
    private static final int INF = Integer.MAX_VALUE;
    
    private void setRs(int k, int[][] reqs) {
        rs = new List[k + 1];
        for (int type = 1; type <= k; type++) {
            rs[type] = new ArrayList<>();
        }
        for (int[] r : reqs) {
            int type = r[2];
            rs[type].add(new Request(r[0], r[1]));
        }
    }
    
    private void setCache(int k, int n) {
        cache = new int[k + 1][n - k + 1];
        for (int type = 1; type <= k; type++) {
            Arrays.fill(cache[type], INF);
        }
    }
    
    private int comb(int type, int remain) {
        if (type == rs.length) {
            return 0;
        }
        if (cache[type][remain] != INF) {
            return cache[type][remain];
        }
        int min = Integer.MAX_VALUE;
        for (int x = 0; x <= remain; x++) {
            int sum = simulate(type, x + 1) + comb(type + 1, remain - x);
            min = Math.min(min, sum);
        }
        return cache[type][remain] = min;
    }
    
    private int simulate(int type, int cap) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int wait = 0;
        for (Request req : rs[type]) {
             if (pq.size() < cap) { // 멘토 남음
                 pq.add(req.in + req.time);
             } else if (pq.peek() <= req.in) { // 가장 빠른 멘토 이미 끝남
                 pq.poll();
                 pq.add(req.in + req.time);
             } else { // 가장 빠른 멘토 상담중
                 int out = pq.poll();
                 wait += out - req.in;
                 pq.add(out + req.time);
             }
        }
        return wait;
    }
    
    // 기다린 시간: 상단 시작 시간 - 요청 시간
    // 요청한 순서대로 상담
    // 기다린 시간의 합의 최솟값 반환
    public int solution(int k, int n, int[][] reqs) {
        setRs(k, reqs);
        setCache(k, n);
        return comb(1, n - k);
    }
}