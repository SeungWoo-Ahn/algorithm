import java.util.Set;
import java.util.HashSet;

class Solution {
    private Set<Integer> result = new HashSet<>();
    
    private void dfs(int visited, String cur, String numbers) {
        if (cur.length() >= 1) {
            int num = Integer.parseInt(cur);
            if (isPrime(num)) {
                result.add(num);
            }
        }
        if (cur.length() == numbers.length()) {
            return;
        }
        for (int i = 0; i < numbers.length(); i++) {
            if ((visited & (1 << i)) != 0) continue;
            int nxtVisited = visited | (1 << i);
            String nxt = cur + numbers.charAt(i);
            dfs(nxtVisited, nxt, numbers);
        }
    }
    
    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(String numbers) {
        dfs(0, "", numbers);
        return result.size();
    }
}