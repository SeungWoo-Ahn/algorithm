import java.util.*;

class Solution {
    private String solve(String x) {
        Deque<Character> dq = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < x.length(); i++) {
            char ch = x.charAt(i);
            if (ch == '0' && dq.size() >= 2) {
                char mid = dq.removeLast();
                char left = dq.getLast();
                if (left == '1' && mid == '1') {
                    dq.removeLast();
                    cnt++;
                } else {
                    dq.addLast(mid);
                    dq.addLast(ch);
                }
            } else {
                dq.addLast(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : dq) {
            sb.append(ch);
        }
        x = sb.toString();
        sb = new StringBuilder();
        int target = x.lastIndexOf('0');
        String left = x.substring(0, target + 1);
        String right = x.substring(target + 1);
        sb.append(left);
        while (cnt-- > 0) {
            sb.append("110");
        }
        sb.append(right);
        return sb.toString();
    }
    
    public String[] solution(String[] s) {
        String[] result = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = solve(s[i]);
        }
        return result;
    }
}