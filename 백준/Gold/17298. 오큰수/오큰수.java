import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static class Num {
        final int value;
        final int index;

        public Num(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    private static int[] initResult(int n) {
        int[] result = new int[n];
        Arrays.fill(result, -1);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<Num> stack = new ArrayDeque<>();
        int[] result = initResult(n);
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && stack.getLast().value < value) {
                int index = stack.removeLast().index;
                result[index] = value;
            }
            stack.addLast(new Num(value, i));
        }
        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            sb.append(r).append(' ');
        }
        System.out.print(sb);
    }
}