import java.util.*;

class Solution {
    private Map<String, List<String>> adj = new HashMap<>();
    private Set<String> v = new HashSet<>();
    private int result = 51;
    
    private boolean contains(String target, String[] words) {
        for (String word : words) {
            if (word.equals(target)) {
                return true;
            }
        }
        return false;
    }
    
    private void setAdj(String begin, String[] words) {
        adj.put(begin, new ArrayList<>());
        for (String word : words) {
            adj.put(word, new ArrayList<>());
            if (isAdj(begin, word)) {
                adj.get(begin).add(word);
                adj.get(word).add(begin);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 1; j < words.length; j++) {
                String a = words[i];
                String b = words[j];
                if (isAdj(a, b)) {
                    adj.get(a).add(b);
                    adj.get(b).add(a);
                }
            }
        }
    }
    
    private boolean isAdj(String a, String b) {
        int mismatch = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                mismatch++;
            }
        }
        return mismatch == 1;
    }
    
    private boolean dfs(int depth, String cur, String target) {
        if (cur.equals(target)) {
            result = Math.min(result, depth);
            return true;
        }
        boolean found = false;
        for (String nxt : adj.get(cur)) {
            if (v.contains(nxt)) continue;
            v.add(nxt);
            if (dfs(depth + 1, nxt, target)) {
                found = true;
            }
            v.remove(nxt);
        }
        return found;
    }
    
    public int solution(String begin, String target, String[] words) {
        if (!contains(target, words)) {
            return 0;
        }
        setAdj(begin, words);
        v.add(begin);
        return dfs(0, begin, target) ? result : 0;
    }
}