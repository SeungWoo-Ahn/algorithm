import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int[][][] costs = new int[n][n][2];
        for (int[][] cost : costs) {
            for (int[] c : cost) {
                Arrays.fill(c, 100_000_000);
            }
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        costs[0][0][0] = 0;
        costs[0][0][1] = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            for (int i = 0; i < dirs.length; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (board[nx][ny] == 1) continue;
                int target = i / 2;
                int minCost = Math.min(costs[x][y][target], costs[x][y][1 - target] + 500) + 100;
                if (minCost < costs[nx][ny][target]) {
                    q.add(new int[]{nx, ny});
                    costs[nx][ny][target] = minCost;
                }
            }
        }
        return Math.min(costs[n - 1][n - 1][0], costs[n - 1][n - 1][1]);
    }
}