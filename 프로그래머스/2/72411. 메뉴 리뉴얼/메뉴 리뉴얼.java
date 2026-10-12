import java.util.*;

class Solution {
    private final Map<String, Integer>[] map = new Map[11];
    
    private String sort(String s) {
        boolean[] e = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            e[s.charAt(i) - 'A'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (e[i]) {
                char ch = (char) (i + 'A');
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    
    private void dfs(int size, int st, String s, String acc) {
        if (acc.length() == size) {
            map[size].put(acc, map[size].getOrDefault(acc, 0) + 1);
            return;
        }
        for (int i = st; i <= s.length() - size + acc.length(); i++) {
            dfs(size, i + 1, s, acc + s.charAt(i));
        }
    }
    
    private List<String> popular(int size) {
        int max = 0;
        for (int cnt : map[size].values()) {
            if (cnt > max) {
                max = cnt;
            }
        }
        if (max < 2) {
            return List.of();
        }
        List<String> res = new ArrayList<>();
        for (String key : map[size].keySet()) {
            if (map[size].get(key) == max) {
                res.add(key);
            }
        }
        return res;
    }
    
    public List<String> solution(String[] orders, int[] course) {
        for (int i = 0; i < orders.length; i++) {
            orders[i] = sort(orders[i]);
        }
        for (int size = 1; size <= 10; size++) {
            map[size] = new HashMap<>();
        }
        for (int size : course) {
            for (String order : orders) {
                if (order.length() < size) continue;
                dfs(size, 0, order, "");
            }
        }
        List<String> result = new ArrayList<>();
        for (int size : course) {
            result.addAll(popular(size));
        }
        Collections.sort(result);
        return result;
    }
}