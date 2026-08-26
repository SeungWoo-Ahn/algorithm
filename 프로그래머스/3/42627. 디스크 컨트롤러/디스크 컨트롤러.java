import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> readyQ = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int time = 0;
        int idx = 0;
        int result = 0;
        while (idx < jobs.length || !readyQ.isEmpty()) {
            while (idx < jobs.length && jobs[idx][0] <= time) {
                readyQ.add(jobs[idx++]);
            }
            if (readyQ.isEmpty()) {
                time = jobs[idx][0];
                continue;
            }
            int[] job = readyQ.poll();
            time += job[1];
            result += time - job[0];
        }
        return result / jobs.length;
    }
}