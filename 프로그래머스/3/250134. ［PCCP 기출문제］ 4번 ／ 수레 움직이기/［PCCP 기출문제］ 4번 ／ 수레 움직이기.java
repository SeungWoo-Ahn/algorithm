import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private static class Pos {
        final int x;
        final int y;
        
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pos)) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }
        
        @Override
        public String toString() {
            return x + "," + y;
        }
    }
    
    private static class Node {
        final Pos red;
        final Pos blue;
        final int rv;
        final int bv;
        final int time;
        
        public Node(Pos red, Pos blue, int rv, int bv, int time) {
            this.red = red;
            this.blue = blue;
            this.rv = rv;
            this.bv = bv;
            this.time = time;
        }
        
        @Override
        public String toString() {
            return red.toString() + " " + Integer.toString(rv, 2) + " " +
                blue.toString() + " " + Integer.toString(bv, 2) + " " + time;
        }
    }
    
    private Pos rs, re, bs, be;
    private boolean[][] wall;
    
    private void setAttrs(int n, int m, int[][] maze) {
        wall = new boolean[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                int num = maze[x][y];
                if (1 <= num && num <= 4) {
                    Pos pos = new Pos(x, y);
                    if (num == 1) rs = pos;
                    else if (num == 2) bs = pos;
                    else if (num == 3) re = pos;
                    else if (num == 4) be = pos;
                }
                wall[x][y] = num == 5;
            }
        }
    }
    
    private int getBit(int m, Pos pos) {
        int num = pos.x * m + pos.y;
        return 1 << num;
    }

    private boolean isValid(int m, int visited, Pos pos) {
        return 0 <= pos.x && pos.x < wall.length &&
            0 <= pos.y && pos.y < wall[pos.x].length && 
            !wall[pos.x][pos.y] &&
            (getBit(m, pos) & visited) == 0;
    }
    
    public int solution(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        setAttrs(n, m, maze);
        Queue<Node> q = new LinkedList<>();
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        q.add(new Node(rs, bs, getBit(m, rs), getBit(m, bs), 0));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            Pos red = cur.red;
            Pos blue = cur.blue;
            if (red.equals(re) && blue.equals(be)) {
                return cur.time;
            }
            if (red.equals(re)) {
                for (int[] dir : dirs) {
                    int nx = blue.x + dir[0];
                    int ny = blue.y + dir[1];
                    Pos next = new Pos(nx, ny);
                    if (isValid(m, cur.bv, next) && !next.equals(red)) {
                        q.add(new Node(red, next, cur.rv, cur.bv | getBit(m, next), cur.time + 1));
                    }
                }
            } else if (blue.equals(be)) {
                for (int[] dir : dirs) {
                    int nx = red.x + dir[0];
                    int ny = red.y + dir[1];
                    Pos next = new Pos(nx, ny);
                    if (isValid(m, cur.rv, next) && !next.equals(blue)) {
                        q.add(new Node(next, blue, cur.rv | getBit(m, next), cur.bv, cur.time + 1));
                    }
                }
            } else {
                for (int[] rdir : dirs) {
                    for (int[] bdir : dirs) {
                        int rnx = red.x + rdir[0];
                        int rny = red.y + rdir[1];
                        int bnx = blue.x + bdir[0];
                        int bny = blue.y + bdir[1];
                        Pos rn = new Pos(rnx, rny);
                        Pos bn = new Pos(bnx, bny);
                        if (rn.equals(bn) || (rn.equals(blue) && bn.equals(red))) continue;
                        if (isValid(m, cur.rv, rn) && isValid(m, cur.bv, bn)) {
                            q.add(new Node(rn, bn, cur.rv | getBit(m, rn), cur.bv | getBit(m, bn), cur.time + 1));
                        }
                    }
                }
            }
        }
        return 0;
    }
}