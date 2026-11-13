import java.util.*;

class Solution {
    private int[] pos(int number) {
        if (number == 0) {
            return new int[]{3, 1};
        }
        number--;
        return new int[]{number / 3, number % 3};
    }
    
    private int weight(int from, int to) {
        int[] fp = pos(from);
        int[] tp = pos(to);
        int x = Math.abs(fp[0] - tp[0]);
        int y = Math.abs(fp[1] - tp[1]);
        if (x == 0 && y == 0) {
            return 1;
        }
        int min = Math.min(x, y);
        int max = Math.max(x, y);
        return min * 3 + (max - min) * 2;
    }
    
    public int solution(String numbers) {
        int n = numbers.length();
        int[][][] dp = new int[n][10][10];
        for (int i = 0; i < n; i++) {
            for (int l = 0; l < 10; l++) {
                Arrays.fill(dp[i][l], Integer.MAX_VALUE);
            }
        }
        int firstNumber = numbers.charAt(0) - '0';
        dp[0][firstNumber][6] = weight(4, firstNumber);
        dp[0][4][firstNumber] = weight(6, firstNumber);
        
        for (int i = 1; i < n; i++) {
            int number = numbers.charAt(i) - '0';
            for (int l = 0; l < 10; l++) {
                for (int r = 0; r < 10; r++) {
                    if (l == r || dp[i - 1][l][r] == Integer.MAX_VALUE) continue;
                    dp[i][number][r] = Math.min(dp[i][number][r], dp[i - 1][l][r] + weight(l, number));
                    dp[i][l][number] = Math.min(dp[i][l][number], dp[i - 1][l][r] + weight(r, number));
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for (int l = 0; l < 10; l++) {
            for (int r = 0; r < 10; r++) {
                result = Math.min(result, dp[n - 1][l][r]);
            }
        }
        return result;
    }
}