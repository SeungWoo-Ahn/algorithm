import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Solution {
    private List<Integer>[] getBannedCombinations(String[] user_id, String[] banned_id) {
        List<Integer>[] bannedCombinations = new List[banned_id.length];
        for (int i = 0; i < banned_id.length; i++) {
            List<Integer> bannedCombination = new ArrayList<>();
            for (int j = 0; j < user_id.length; j++) {
                if (matches(user_id[j], banned_id[i])) {
                    bannedCombination.add(j);
                }
            }
            bannedCombinations[i] = bannedCombination;
        }
        return bannedCombinations;
    }
    
    private boolean matches(String id, String target) {
        if (id.length() != target.length()) {
            return false;
        }
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == '*') continue;
            if (id.charAt(i) != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    private Set<Integer> result = new HashSet<>();
    
    private void backtracking(int depth, int used, List<Integer>[] bannedCombinations) {
        if (depth == bannedCombinations.length) {
            result.add(used);
            return;
        }
        for (int id : bannedCombinations[depth]) {
            if ((used & (1 << id)) != 0) continue;
            int nxtUsed = used | (1 << id);
            backtracking(depth + 1, nxtUsed, bannedCombinations);
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        List<Integer>[] bannedCombinations = getBannedCombinations(user_id, banned_id);
        backtracking(0, 0, bannedCombinations);
        return result.size();
    }
}