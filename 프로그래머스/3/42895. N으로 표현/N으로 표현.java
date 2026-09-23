import java.util.*;

class Solution {
    public int solution(int n, int number) {
        Set<Integer>[] dp = new Set[9];
        int cur = n;
        for (int i = 1; i < 9; i++) {
            if (cur == number) {
                return i;
            }
            dp[i] = new HashSet<>();
            dp[i].add(cur);
            cur = cur * 10 + n;
        }
        for (int i = 2; i < 9; i++)
            for (int j = 1; j < i; j++)
                for (int a : dp[j])
                    for (int b : dp[i - j]) {
                        if (a + b == number) return i;
                        dp[i].add(a + b);
                        
                        if (a - b == number) return i;
                        dp[i].add(a - b);
                        
                        if (a * b == number) return i;
                        dp[i].add(a * b);
                        
                        if (b != 0) {
                            if (a / b == number) return i;
                            dp[i].add(a / b);
                        }
                    }
        return -1;
    }
}