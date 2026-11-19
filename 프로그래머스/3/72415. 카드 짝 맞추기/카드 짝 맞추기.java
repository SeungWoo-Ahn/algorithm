import java.util.*;

class Solution {
    record Pos(int x, int y) {}
    
    private final Map<Integer, List<Pos>> pairs = new HashMap<>();
    private final int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int result = Integer.MAX_VALUE;
    
    private void backtracking(int depth, int v, int cost, Pos cursor, int[][] board, List<Integer> targets) {
        if (cost >= result) return;
        if (depth == targets.size()) {
            result = Math.min(result, cost);
            return;
        }
        for (int i = 0; i < targets.size(); i++) {
            if ((v & (1 << i)) != 0) continue;
            int target = targets.get(i);
            List<Pos> pair = pairs.get(target);
            Pos a = pair.get(0);
            Pos b = pair.get(1);
            for (int s = 0; s < 2; s++) {
                int e = (s + 1) % 2;
                int cTos = move(cursor, pair.get(s), board);
                int sToe = move(pair.get(s), pair.get(e), board) + 2;
                int nxtCost = cTos + sToe + cost;
                board[a.x][a.y] = 0;
                board[b.x][b.y] = 0;
                backtracking(depth + 1, v | (1 << i), nxtCost, pair.get(e), board, targets);
                board[a.x][a.y] = target;
                board[b.x][b.y] = target;
            }
        }
    }
    
    // from -> to 가는 최소 동선
    private int move(Pos from, Pos to, int[][] board) {
        Deque<Pos> dq = new ArrayDeque<>();
        boolean[][] v = new boolean[4][4];
        int move = 0;
        dq.addLast(from);
        v[from.x][from.y] = true;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                Pos cur = dq.removeFirst();
                if (cur.equals(to)) {
                    return move;
                }
                // Ctrl
                for (int[] d : ds) {
                    int nx = cur.x + d[0];
                    int ny = cur.y + d[1];
                    if (oob(nx, ny)) continue;
                    while (board[nx][ny] == 0) {
                        nx += d[0];
                        ny += d[1];
                        if (oob(nx, ny)) {
                            nx -= d[0];
                            ny -= d[1];
                            break;
                        }
                    }
                    if (v[nx][ny]) continue;
                    dq.addLast(new Pos(nx, ny));
                    v[nx][ny] = true;
                }
                // 방향키
                for (int[] d : ds) {
                    int nx = cur.x + d[0];
                    int ny = cur.y + d[1];
                    if (oob(nx, ny) || v[nx][ny]) continue;
                    dq.addLast(new Pos(nx, ny));
                    v[nx][ny] = true;
                }
            }
            move++;
        }
        return -1;
    }
    
    private boolean oob(int x, int y) {
        return x < 0 || x >= 4 || y < 0 || y >= 4;
    }
    
    // 방향키: 한 칸 이동
    // Ctrl: 해당 방향 가장 가까운 카드로, 없으면 마지막 칸
    // Enter: 뒤집기, 앞면 2장이고 같은 그림이면 사라짐, 다르면 2장 모두 뒷면으로
    // 카드를 제거하는데 필요한 최소 조작 횟수
    public int solution(int[][] board, int r, int c) {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int num = board[x][y];
                if (num == 0) continue;
                pairs.putIfAbsent(num, new ArrayList<>());
                pairs.get(num).add(new Pos(x, y));
            }
        }
        List<Integer> targets = new ArrayList<>(pairs.keySet());
        backtracking(0, 0, 0, new Pos(r, c), board, targets);
        return result;
    }
}