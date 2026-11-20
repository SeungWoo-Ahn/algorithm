import java.util.*;

class Solution {
    record Meta(int w, int h, int count) {
        public Meta reverse() {
            return new Meta(h, w, count);
        }
    }
    
    record Piece(int id, Meta meta, boolean[][] data) {}
    
    private final Map<Meta, List<Piece>> map = new HashMap<>();
    private final int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int n;
    
    private void setMap(int[][] table) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] v = new boolean[n][n];
        int id = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (table[x][y] == 1 && !v[x][y]) {
                    Piece piece = getPiece(id++, x, y, 1, dq, v, table);
                    map.putIfAbsent(piece.meta, new ArrayList<>());
                    map.get(piece.meta).add(piece);
                }
            }
        }
    }
    
    private int matching(int[][] game_board) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] v = new boolean[n][n];
        Set<Integer> used = new HashSet<>();
        int result = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (game_board[x][y] == 0 && !v[x][y]) {
                    Piece piece = getPiece(-1, x, y, 0, dq, v, game_board);
                    Meta meta = piece.meta;
                    List<Piece> cand = new ArrayList<>(map.getOrDefault(meta, List.of()));
                    if (meta.w != meta.h) {
                        cand.addAll(map.getOrDefault(meta.reverse(), List.of()));
                    }
                    for (Piece p : cand) {
                        if (used.contains(p.id)) continue;
                        if (fit(piece.data, p.data)) {
                            used.add(p.id);
                            result += meta.count;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
    
    private boolean fit(boolean[][] d1, boolean[][] d2) {
        int h = d1.length;
        int w = d1[0].length;
        boolean[][] target = d2;
        for (int i = 0; i < 4; i++) {
            if (h == target.length && w == target[0].length) {
                if (same(d1, target)) {
                    return true;
                }
            }
            if (i == 3) break;
            target = rotate(target);
        }
        return false;
    }
    
    private boolean same(boolean[][] d1, boolean[][] d2) {
        for (int x = 0; x < d1.length; x++) {
            for (int y = 0; y < d1[x].length; y++) {
                if (d1[x][y] != d2[x][y])
                    return false;
            }
        }
        return true;
    }
    
    private boolean[][] rotate(boolean[][] data) {
        int h = data.length;
        int w = data[0].length;
        boolean[][] rdata = new boolean[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                rdata[x][y] = data[h - y - 1][x];
            }
        }
        return rdata;
    }
    
    private Piece getPiece(int id, int sx, int sy, int target, 
                           Deque<int[]> dq, boolean[][] v, int[][] arr) {
        List<int[]> cells = new ArrayList<>();
        int minX = n;
        int minY = n;
        int maxX = -1;
        int maxY = -1;
        dq.addLast(new int[]{sx, sy});
        v[sx][sy] = true;
        while (!dq.isEmpty()) {
            int[] cur = dq.removeFirst();
            cells.add(cur);
            minX = Math.min(minX, cur[0]);
            minY = Math.min(minY, cur[1]);
            maxX = Math.max(maxX, cur[0]);
            maxY = Math.max(maxY, cur[1]);
            for (int[] d : ds) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (arr[nx][ny] != target || v[nx][ny]) continue;
                dq.addLast(new int[]{nx, ny});
                v[nx][ny] = true;
            }
        }
        int w = maxY - minY + 1;
        int h = maxX - minX + 1;
        Meta meta = new Meta(w, h, cells.size());
        boolean[][] data = new boolean[h][w];
        for (int[] cell : cells) {
            data[cell[0] - minX][cell[1] - minY] = true;
        }
        return new Piece(id, meta, data);
    }
    
    // 딱 맞는 조각만 매칭 (회전 가능)
    // 몇 칸 채울 수 있는지?
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        setMap(table);
        return matching(game_board);
    }
}