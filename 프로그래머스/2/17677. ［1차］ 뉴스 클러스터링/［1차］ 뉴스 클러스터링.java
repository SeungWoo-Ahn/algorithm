import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Solution {
    private static class Group {
        final Map<String, Integer> map = new HashMap<>();
        
        public Group(String s) {
            String target = s.toUpperCase();
            for (int i = 1; i < target.length(); i++) {
                String word = target.substring(i - 1, i + 1);
                if (isAlpah(word)) {
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }
        }
        
        private boolean isAlpah(String word) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!('A' <= ch && ch <= 'Z')) {
                    return false;
                }
            }
            return true;
        }
    }
    
    private int calcChild(Group g1, Group g2) {
        int child = 0;
        for (String key : g1.map.keySet()) {
            if (!g2.map.containsKey(key)) continue;
            child += Math.min(g1.map.get(key), g2.map.get(key));
        }
        return child;
    }
    
    private int calcParent(Group g1, Group g2) {
        int parent = 0;
        Set<String> keySet = new HashSet();
        keySet.addAll(g1.map.keySet());
        keySet.addAll(g2.map.keySet());
        for (String key : keySet) {
            boolean c1 = g1.map.containsKey(key);
            boolean c2 = g2.map.containsKey(key);
            if (c1 && c2) {
                parent += Math.max(g1.map.get(key), g2.map.get(key));
            } else if (c1) {
                parent += g1.map.get(key);
            } else {
                parent += g2.map.get(key);
            }
        }
        return parent;
    }
    
    private int getJ(int child, int parent) {
        double result;
        if (parent == 0) {
            result = 1.0;
        } else {
            result = (double) child / parent;
        }
        return (int) (result * 65_536);
    }
    
    public int solution(String str1, String str2) {
        Group g1 = new Group(str1);
        Group g2 = new Group(str2);
        int child = calcChild(g1, g2);
        int parent = calcParent(g1, g2);
        return getJ(child, parent);
    }
}