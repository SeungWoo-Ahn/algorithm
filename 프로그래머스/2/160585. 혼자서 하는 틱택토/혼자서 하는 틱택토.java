class Solution {    
    public int solution(String[] board) {
        int oc = 0;
        int xc = 0;
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++) {
                if (board[x].charAt(y) == 'O') {
                    oc++;
                } else if (board[x].charAt(y) == 'X') {
                    xc++;
                }
            }
        if (xc > oc || oc - xc > 1) {
            return 0;
        }
        if (oc == 0 && xc == 0) {
            return 1;
        }
        int oEnd = 0;
        int xEnd = 0;
        for (int x = 0; x < 3; x++) {
            char ch = board[x].charAt(0);
            if (ch == '.') continue;
            if (board[x].charAt(1) == ch && board[x].charAt(2) == ch) {
                if (ch == 'O') oEnd++;
                else xEnd++;
            }
        }
        for (int y = 0; y < 3; y++) {
            char ch = board[0].charAt(y);
            if (ch == '.') continue;
            if (board[1].charAt(y) == ch && board[2].charAt(y) == ch) {
                if (ch == 'O') oEnd++;
                else xEnd++;
            }
        }
        if (board[0].charAt(0) != '.') {
            char ch = board[0].charAt(0);
            if (board[1].charAt(1) == ch && board[2].charAt(2) == ch) {
                if (ch == 'O') oEnd++;
                else xEnd++;
            }
        }
        if (board[0].charAt(2) != '.') {
            char ch = board[0].charAt(2);
            if (board[1].charAt(1) == ch && board[0].charAt(2) == ch) {
                if (ch == 'O') oEnd++;
                else xEnd++;
            }
        }
        if (oEnd == 0 && xEnd == 0) {
            return 1;
        }
        if (oc - xc == 0 && oEnd == 0 && xEnd > 0) {
            return 1;
        }
        if (oc - xc == 1 && oEnd > 0 && xEnd == 0) {
            return 1;
        }
        return 0;
    }
}