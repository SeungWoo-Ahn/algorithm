class Solution {
    private final int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    // turn: 0 -> a, 1 -> b
    // 승리 가능: 최소한 턴으로 이겨야 함
    // 무조건 패배: 최대한의 턴으로 패배해야 함
    // 게임이 끝날 때까지 몇 턴 남았는지 반환
    private int dfs(int turn, int[][] pos, int[][] board) {
        int x = pos[turn][0];
        int y = pos[turn][1];
        if (board[x][y] == 0) { // 현위치 발판 없음
            return 0;
        }
        int nextTurn = (turn + 1) % 2;
        boolean canWin = false;
        int winMin = Integer.MAX_VALUE;
        int loseMax = -1;
        board[x][y] = 0;
        for (int i = 0; i < ds.length; i++) {
            int nx = x + ds[i][0];
            int ny = y + ds[i][1];
            if (oob(nx, ny, board) || board[nx][ny] == 0) continue;
            pos[turn][0] = nx;
            pos[turn][1] = ny;
            int r = dfs(nextTurn, pos, board);
            if (r % 2 == 0) { // 내 턴에 승리 -> 최대한 빨리
                canWin = true;
                winMin = Math.min(winMin, r);
            } else { // 내 턴에 패배 -> 최대한 늦게
                loseMax = Math.max(loseMax, r);
            }
        }
        board[x][y] = 1;
        pos[turn][0] = x;
        pos[turn][1] = y;
        if (!canWin && loseMax < 0) { // 이동 불가
            return 0;
        }
        return canWin ? winMin + 1 : loseMax + 1;
    }
    
    private boolean oob(int x, int y, int[][] board) {
        return x < 0 || x >= board.length || y < 0 || y >= board[x].length;
    }
    
    // 발판 밟고 이동하면, 사라짐
    // 번갈아가며 상하좌우 이동
    // 1. 이동할 곳이 없으면 패배
    // 2. 같은 발판에서 상대가 이동해서 발판이 사라지면 패배
    // A먼저 시작, 승자: 승리 최적, 패자: 최대한 오래
    // 움직인 횟수의 합의 최대값
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int[][] pos = new int[2][2];
        pos[0][0] = aloc[0];
        pos[0][1] = aloc[1];
        pos[1][0] = bloc[0];
        pos[1][1] = bloc[1];
        return dfs(0, pos, board);
    }
}