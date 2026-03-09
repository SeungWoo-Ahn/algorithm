import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int[] acc = new int[31];
        int depth = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[') {
                stack.addLast(c);
                depth++;
                continue;
            }
            if (!stack.isEmpty() && ((c == ')' && stack.getLast() == '(') || (c == ']' && stack.getLast() =='['))) {
                int point = c == ')' ? 2 : 3;
                if (acc[depth] > 0) {
                    point *= acc[depth];
                    acc[depth] = 0;
                }
                acc[--depth] += point;
                stack.removeLast();
            } else {
                acc[0] = 0;
                break;
            }
        }
        System.out.print(acc[0]);
    }
}