import java.util.*;

class Solution {
    private Map<String, String> parent = new HashMap<>();
    private Map<String, Integer> profit = new HashMap<>();
    private static final String ADMIN = "center";
    
    private void dfs(String cur, int rest) {
        int nxt = rest / 10;
        if (nxt < 1 || parent.get(cur) == null) {
            profit.put(cur, profit.getOrDefault(cur, 0) + rest);
            return;
        }
        profit.put(cur, profit.getOrDefault(cur, 0) + rest - nxt);
        dfs(parent.get(cur), nxt);
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (int i = 0; i < referral.length; i++) {
            String p = referral[i].equals("-") ? ADMIN : referral[i];
            parent.put(enroll[i], p);
        }
        for (int i = 0; i < seller.length; i++) {
            dfs(seller[i], amount[i] * 100);
        }
        int[] result = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            result[i] = profit.getOrDefault(enroll[i], 0);
        }
        return result;
    }
}