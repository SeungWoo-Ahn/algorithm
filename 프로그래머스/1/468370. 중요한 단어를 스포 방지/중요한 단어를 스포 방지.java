import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Solution {
    private List<String> preventWords = new ArrayList<>();
    private Set<String> nonPreventWords = new HashSet<>();
    
    private boolean[] getIsPrevent(int length, int[][] spoiler_ranges) {
        boolean[] isPrevent = new boolean[length];
        for (int[] range : spoiler_ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                isPrevent[i] = true;
            }
        }
        return isPrevent;
    }
    
    private void setWords(String message, boolean[] isPrevent) {
        int st = 0;
        while (st < message.length()) {
            if (message.charAt(st) == ' ') {
                st++;
                continue;
            }
            int en = st;
            boolean prevent = false;
            while (en < message.length() && message.charAt(en) != ' ') {
                if (isPrevent[en++]) {
                    prevent = true;
                }
            }
            String word = message.substring(st, en);
            if (prevent) {
                preventWords.add(word);
            } else {
                nonPreventWords.add(word);
            }
            st = en;
        }
    }
    
    public int solution(String message, int[][] spoiler_ranges) {
        boolean[] isPrevent = getIsPrevent(message.length(), spoiler_ranges);
        setWords(message, isPrevent);
        Set<String> history = new HashSet<>();
        int result = 0;
        for (String preventWord : preventWords) {
            if (history.add(preventWord) && !nonPreventWords.contains(preventWord)) {
                result++;
            }
        }
        return result;
    }
}