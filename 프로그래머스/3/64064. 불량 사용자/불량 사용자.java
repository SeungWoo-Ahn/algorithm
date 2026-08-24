import java.util.*;

class Solution {
    private List<Integer>[] matches;
    private Set<Integer> result = new HashSet<>();
    
    private boolean match(String uid, String bid) {
        if (uid.length() != bid.length()) {
            return false;
        }
        for (int i = 0; i < uid.length(); i++) {
            if (bid.charAt(i) == '*') continue;
            if (uid.charAt(i) != bid.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    private void dfs(int depth, int v) {
        if (depth == matches.length) {
            result.add(v);
            return;
        }
        for (int i : matches[depth]) {
            if ((v & (1 << i)) != 0) continue;
            dfs(depth + 1, v | (1 << i));
        }
    } 
    
    public int solution(String[] user_id, String[] banned_id) {
        matches = new List[banned_id.length];
        for (int i = 0; i < banned_id.length; i++) {
            matches[i] = new ArrayList<>();
            for (int j = 0; j < user_id.length; j++) {
                if (match(user_id[j], banned_id[i])) {
                    matches[i].add(j);
                }
            }
        }
        dfs(0, 0);
        return result.size();
    }
}