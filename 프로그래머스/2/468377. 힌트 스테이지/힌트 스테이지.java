import java.util.*;

class Solution {
    private int dfs(int depth, int[] buy, int[][] cost, int[][] hint) {
        int idx = Math.min(buy[depth], cost[depth].length - 1);
        int stageCost = cost[depth][idx];
        if (depth == cost.length - 1) {
            return stageCost;
        }
        int hintCost = hint[depth][0];
        List<Integer> li = new ArrayList<>();
        for (int i = 1; i < hint[depth].length; i++) {
            int stage = hint[depth][i] - 1;
            li.add(stage);
            buy[stage]++;
        }
        // 산다: 금액 + 힌트 가격
        int min = dfs(depth + 1, buy, cost, hint) + stageCost + hintCost;
        for (int stage : li) {
            buy[stage]--;
        }
        // 안산다: 금액
        min = Math.min(min, dfs(depth + 1, buy, cost, hint) + stageCost);
        return min;
    }
    
    public int solution(int[][] cost, int[][] hint) {
        return dfs(0, new int[cost.length], cost, hint);
    }
}