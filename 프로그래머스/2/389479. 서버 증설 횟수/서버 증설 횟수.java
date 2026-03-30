import java.util.ArrayDeque;

class Solution {
    public int solution(int[] players, int m, int k) {
        ArrayDeque<Integer> serverQ = new ArrayDeque<>();
        int result = 0;
        for (int time = 0; time < players.length; time++) {
            while (!serverQ.isEmpty() && serverQ.peek() <= time) {
                serverQ.poll();
            }
            int need = (players[time] / m) - serverQ.size();
            if (need > 0) {
                int returnTime = time + k;
                result += need;
                while (need-- > 0) {
                    serverQ.offer(returnTime);
                }
            }
        }
        return result;
    }
}