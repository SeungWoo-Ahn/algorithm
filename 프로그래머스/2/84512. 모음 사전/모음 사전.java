import java.util.Map;
import java.util.HashMap;

class Solution {
    private String moeum = "AEIOU";
    private int seq = 1;
    private Map<String, Integer> dict = new HashMap<>();
    
    private void dfs(String cur) {
        if (cur.length() > 0) {
            dict.put(cur, seq++);
        }
        if (cur.length() == 5) {
            return;
        }
        for (int i = 0; i < moeum.length(); i++) {
            String nxt = cur + moeum.charAt(i);
            dfs(nxt);
        }
    }
    
    public int solution(String word) {
        dfs("");
        return dict.getOrDefault(word, 0);
    }
}