import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        final int x;
        final int y;
        final int chance;

        public Node(int x, int y, int chance) {
            this.x = x;
            this.y = y;
            this.chance = chance;
        }
    }

    private static int n, m;
    private static char[][] map;
    private static int[][][] cost;
    private static boolean[][][] visited;

    private static int bfs() {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.addLast(new Node(0, 0,1));
        cost[0][0][1] = 1;
        visited[0][0][1] = true;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            Node node = q.removeFirst();
            if (node.x == n - 1 && node.y == m - 1) {
                return cost[node.x][node.y][node.chance];
            }
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (outOfBoundary(nx, ny)) continue;
                if (map[nx][ny] == '0' && !visited[nx][ny][node.chance]) {
                    q.addLast(new Node(nx, ny, node.chance));
                    cost[nx][ny][node.chance] = cost[node.x][node.y][node.chance] + 1;
                    visited[nx][ny][node.chance] = true;
                } else if (map[nx][ny] == '1' && node.chance > 0) {
                    q.addLast(new Node(nx, ny, 0));
                    cost[nx][ny][0] = cost[node.x][node.y][1] + 1;
                    visited[nx][ny][0] = true;
                }
            }
        }
        return -1;
    }

    private static boolean outOfBoundary(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        cost = new int[n][m][2];
        visited = new boolean[n][m][2];
        System.out.print(bfs());
    }
}
