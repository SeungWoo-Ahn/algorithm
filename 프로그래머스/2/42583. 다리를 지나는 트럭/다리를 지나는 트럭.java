import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int n = truck_weights.length;
        Queue<Integer> q = new LinkedList<>();
        int idx = 0;
        int done = 0;
        int load = 0;
        int time = 1;
        while (done < n) {
            if (load < weight) {
                if (idx < n && load + truck_weights[idx] <= weight) {
                    int w = truck_weights[idx++];
                    load += w;
                    q.add(w);
                } else {
                    q.add(0);
                }
            } else {
                q.add(0);
            }
            if (q.size() == bridge_length) {
                int w = q.poll();
                load -= w;
                if (w > 0) {
                    done++;
                }
            }
            time++;
        }
        return time;
    }
}