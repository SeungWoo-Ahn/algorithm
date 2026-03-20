import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                stack.addLast(input[i]);
            } else {
                stack.removeLast();
                if (input[i - 1] == '(') {
                    result += stack.size();
                } else {
                    result++;
                }
            }
        }
        System.out.print(result);
    }
}