import java.util.*;

class Solution {
    public int solution(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (dq.isEmpty() || dq.getLast() != s.charAt(i)) {
                dq.addLast(s.charAt(i));
            } else {
                dq.removeLast();
            }
        }
        return dq.isEmpty() ? 1 : 0;
    }
}