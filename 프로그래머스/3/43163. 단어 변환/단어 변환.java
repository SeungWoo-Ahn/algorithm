import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Solution {
    private Map<String, List<String>> adj = new HashMap<>();
    private Set<String> visited = new HashSet<>();
    
    private boolean setAdj(String begin, String target, String[] words) {
        boolean findTarget = false;
        String[] keys = new String[words.length + 1];
        keys[0] = begin;
        for (int i = 1; i < keys.length; i++) {
            keys[i] = words[i - 1];
        }
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                String a = keys[i];
                String b = keys[j];
                if (a.equals(target) || b.equals(target)) {
                    findTarget = true;
                }
                if (isAdj(a, b)) {
                    List<String> aAdj = adj.getOrDefault(a, new ArrayList<String>());
                    List<String> bAdj = adj.getOrDefault(b, new ArrayList<String>());
                    aAdj.add(b);
                    bAdj.add(a);
                    adj.put(a, aAdj);
                    adj.put(b, bAdj);
                }
            }
        }
        return findTarget;
    }
    
    private boolean isAdj(String a, String b) {
        int mismatchCnt = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                mismatchCnt++;
                if (mismatchCnt > 1) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private int dfs(int depth, String cur, String target) {
        if (cur.equals(target)) {
            return depth;
        }
        visited.add(cur);
        int minDepth = 51;
        for (String nxt : adj.get(cur)) {
            if (visited.contains(nxt)) continue;
            int result = dfs(depth + 1, nxt, target);
            if (result < minDepth) {
                minDepth = result;
            }
        }
        return minDepth;
    }
    
    public int solution(String begin, String target, String[] words) {
        boolean findTarget = setAdj(begin, target, words);
        if (!findTarget) {
            return 0;
        }
        visited.add(begin);
        return dfs(0, begin, target);
    }
}