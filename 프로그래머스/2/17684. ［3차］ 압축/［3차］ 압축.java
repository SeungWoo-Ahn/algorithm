import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    private Map<String, Integer> getDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int num = 0; num < 26; num++) {
            char ch = (char) ('A' + num);
            String key = String.valueOf(ch);
            dictionary.put(key, num + 1);
        }
        return dictionary;
    }
    
    public List<Integer> solution(String msg) {
        Map<String, Integer> dictionary = getDictionary();
        List<Integer> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int num = dictionary.size() + 1;
        while (sb.length() < msg.length()) {
            int st = sb.length();
            int en = st;
            while (en < msg.length() && dictionary.containsKey(msg.substring(st, en + 1))) {
                en++;
            }
            String key = msg.substring(st, en);
            result.add(dictionary.get(key));
            sb.append(key);
            if (en < msg.length()) {
                dictionary.put(msg.substring(st, en + 1), num++);
            }
        }
        return result;
    }
}