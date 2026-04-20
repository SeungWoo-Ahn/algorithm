import java.util.Map;
import java.util.HashMap;

class Solution {
    private Map<Integer, Integer>[][] getTimeMap() {
        Map<Integer, Integer>[][] timeMap = new Map[101][101];
        for (int i = 0; i < timeMap.length; i++) {
            for (int j = 0; j < timeMap[i].length; j++) {
                timeMap[i][j] = new HashMap<>();
            }
        }
        return timeMap;
    }
    
    public int solution(int[][] points, int[][] routes) {
        Map<Integer, Integer>[][] timeMap = getTimeMap();
        for (int[] route : routes) {
            int time = 0;
            for (int i = 1; i < route.length; i++) {
                int si = route[i - 1] - 1;
                int ei = route[i] - 1;
                int[] cur = {points[si][0], points[si][1]};
                int[] goal = {points[ei][0], points[ei][1]};
                int dx = cur[0] < goal[0] ? 1 : -1;
                int dy = cur[1] < goal[1] ? 1 : -1;
                while (cur[0] != goal[0]) {
                    timeMap[cur[0]][cur[1]].put(time, timeMap[cur[0]][cur[1]].getOrDefault(time, 0) + 1);
                    cur[0] += dx;
                    time++;
                }
                while (cur[1] != goal[1]) {
                    timeMap[cur[0]][cur[1]].put(time, timeMap[cur[0]][cur[1]].getOrDefault(time, 0) + 1);
                    cur[1] += dy;
                    time++;
                }
            }
            int li = route[route.length - 1] - 1;
            int[] last = {points[li][0], points[li][1]};
            timeMap[last[0]][last[1]].put(time, timeMap[last[0]][last[1]].getOrDefault(time, 0) + 1);
        }
        int result = 0;
        for (int i = 0; i < timeMap.length; i++) {
            for (int j = 0; j < timeMap[i].length; j++) {
                for (int key : timeMap[i][j].keySet()) {
                    int size = timeMap[i][j].get(key);
                    if (size > 1) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}