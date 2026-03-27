class Solution {
    private static final int BLANK = -1;
    
    private int[][] makeGameBoard(int m, int n, String[] board) {
        int[][] gameBoard = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                gameBoard[i][j] = board[i].charAt(j) - 'A';
            }
        }
        return gameBoard;
    }
    
    private int removeBlocks(int m, int n, int[][] gameBoard) {
        boolean[][] removed = new boolean[m][n];
        int removedCnt = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                int target = gameBoard[i][j];
                if (target == BLANK) continue;
                if (gameBoard[i + 1][j] == target && 
                    gameBoard[i][j + 1] == target && 
                    gameBoard[i + 1][j + 1] == target) {
                    removed[i][j] = removed[i + 1][j] = removed[i][j + 1] = removed[i + 1][j + 1] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (removed[i][j]) {
                    gameBoard[i][j] = BLANK;
                    removedCnt++;
                }
            }
        }
        return removedCnt;
    }
    
    private void pulldownBlocks(int m, int n, int[][] gameBoard) {
        for (int j = 0; j < n; j++) {
            int writeIdx = m - 1;
            for (int i = m - 1; i >= 0; i--) {
                if (gameBoard[i][j] != BLANK) {
                    int temp = gameBoard[i][j];
                    gameBoard[i][j] = BLANK;
                    gameBoard[writeIdx][j] = temp;
                    writeIdx--;
                }
            }
        }
    }
    
    public int solution(int m, int n, String[] board) {
        int[][] gameBoard = makeGameBoard(m, n, board);
        int result = 0;
        while (true) {
            int removedBlocks = removeBlocks(m, n, gameBoard);
            if (removedBlocks == 0) {
                break;
            }
            pulldownBlocks(m, n, gameBoard);
            result += removedBlocks;
        }
        return result;
    }
}