import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class State {
        final int x;
        final int y;
        final int breakCnt;
        final int cost;

        public State(int x, int y, int breakCnt, int cost) {
            this.x = x;
            this.y = y;
            this.breakCnt = breakCnt;
            this.cost = cost;
        }
    }

    private static int solution(int n, int m, int k, boolean[][] wall) {
        Queue<State> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][k + 1];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        q.offer(new State(0, 0, 0, 1));
        visited[0][0][0] = true;
        while (!q.isEmpty()) {
            State s = q.poll();
            if (s.x == n - 1 && s.y == m - 1) {
                return s.cost;
            }
            for (int[] dir : dirs) {
                int nx = s.x + dir[0];
                int ny = s.y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (!wall[nx][ny]) {
                    if (!visited[nx][ny][s.breakCnt]) {
                        q.offer(new State(nx, ny, s.breakCnt, s.cost + 1));
                        visited[nx][ny][s.breakCnt] = true;
                    }
                } else if (wall[nx][ny] && s.breakCnt < k) {
                    if (!visited[nx][ny][s.breakCnt + 1]) {
                        q.offer(new State(nx, ny, s.breakCnt + 1, s.cost + 1));
                        visited[nx][ny][s.breakCnt + 1] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[][] wall = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                wall[i][j] = row.charAt(j) == '1';
            }
        }
        int answer = solution(n, m, k, wall);
        System.out.print(answer);
    }
}