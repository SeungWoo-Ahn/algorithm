import java.util.Set;
import java.util.HashSet;

class Solution {    
    public int solution(int n, int number) {
        Set<Integer>[] dp = new Set[9];
        Set<Integer> used = new HashSet<>();
        int cur = n;
        for (int depth = 1; depth < dp.length; depth++) {
            dp[depth] = new HashSet<>();
            if (cur == number) {
                return depth;
            }
            dp[depth].add(cur);
            cur = cur * 10 + n;
        }
        for (int depth = 2; depth < dp.length; depth++) {
            for (int left = 1; left < depth; left++) {
                int right = depth - left;
                for (int leftNum : dp[left]) {
                    for (int rightNum : dp[right]) {
                        int[] cand = {
                            leftNum + rightNum, 
                            leftNum - rightNum, 
                            rightNum - leftNum, 
                            leftNum * rightNum,
                            leftNum / rightNum,
                            rightNum / leftNum
                        };
                        for (int num : cand) {
                            if (num <= 0 || used.contains(num)) continue;
                            if (num == number) {
                                return depth;
                            }
                            used.add(num);
                            dp[depth].add(num);
                        }
                    }
                }
            }
        }
        return -1;
    }
}