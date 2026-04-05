import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int solution(int k, int[][] things) {
        int[] dp = new int[k + 1];
        for (int[] thing : things) {
            int w = thing[0];
            int v = thing[1];
            for (int i = k; i >= w; i--) {
                dp[i] = Math.max(dp[i], dp[i - w] + v);
            }
        }
        return dp[k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] things = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            things[i][0] = Integer.parseInt(st.nextToken());
            things[i][1] = Integer.parseInt(st.nextToken());
        }
        int answer = solution(k, things);
        System.out.print(answer);
    }
}