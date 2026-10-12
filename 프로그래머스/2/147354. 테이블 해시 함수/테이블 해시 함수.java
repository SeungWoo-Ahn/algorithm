import java.util.*;

class Solution {
    private int si(int i, int[] d) {
        int si = 0;
        for (int num : d) {
            si += num % i;
        }
        return si;
    }
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (o1, o2) -> 
                    o1[col - 1] != o2[col - 1] ? o1[col - 1] - o2[col - 1] : o2[0] - o1[0]);
        int result = si(row_begin, data[row_begin - 1]);
        for (int i = row_begin + 1; i <= row_end; i++) {
            int si = si(i, data[i - 1]);
            result ^= si;
        }
        return result;
    }
}