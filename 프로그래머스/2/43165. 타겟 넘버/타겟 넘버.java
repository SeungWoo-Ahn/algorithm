class Solution {
    public int dfs(int depth, int[] numbers, int target, int acc) {
        if (depth == numbers.length) {
            return acc == target ? 1 : 0;
        }
        int res = 0;
        res += dfs(depth + 1, numbers, target, acc + numbers[depth]);
        res += dfs(depth + 1, numbers, target, acc - numbers[depth]);
        return res;
    }
    
    public int solution(int[] numbers, int target) {
        return dfs(0, numbers, target, 0);
    }
}