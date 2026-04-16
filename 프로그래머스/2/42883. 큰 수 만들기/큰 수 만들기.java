import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        int need = number.length() - k;
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (k == 0 || stack.isEmpty()) {
                stack.addLast(ch);
                continue;
            }
            while (k > 0 && !stack.isEmpty() && stack.peekLast() < ch) {
                stack.removeLast();
                k--;
            }
            stack.addLast(ch);
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
            if (--need == 0) break;
        }
        return sb.toString();
    }
}