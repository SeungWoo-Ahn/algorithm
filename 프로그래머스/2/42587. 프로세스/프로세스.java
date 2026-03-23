import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Integer> readyQ = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < priorities.length; i++) {
            readyQ.offer(i);
            pq.offer(priorities[i]);
        }
        int seq = 0;
        while (!readyQ.isEmpty()) {
            int index = readyQ.poll();
            int priority = priorities[index];
            if (priority >= pq.peek()) {
                pq.poll();
                seq++;
                if (index == location) {
                    return seq;
                }
            } else {
                readyQ.offer(index);
            }
        }
        return seq;
    }
}