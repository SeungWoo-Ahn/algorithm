import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 2_000_000_000;

    private static int[][] getDp(int n) {
        int[][] dp = new int[n][1 << n];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return dp;
    }

    private static int dfs(int n, int cur, int visited, int[][] dp, int[][] w) {
        if (visited == (1 << n) - 1) {
            dp[cur][visited] = w[cur][0] == 0 ? INF : w[cur][0];
            return dp[cur][visited];
        }
        if (dp[cur][visited] != -1) {
            return dp[cur][visited];
        }
        int minCost = INF;
        for (int nxt = 0; nxt < n; nxt++) {
            if ((visited & (1 << nxt)) != 0 || w[cur][nxt] == 0) continue;
            int nxtVisited = visited | (1 << nxt);
            minCost = Math.min(minCost, dfs(n, nxt, nxtVisited, dp, w) + w[cur][nxt]);
        }
        dp[cur][visited] = minCost;
        return minCost;
    }

    private static int solution(int n, int[][] w) {
        int[][] dp = getDp(n);
        return dfs(n, 0, 1, dp, w);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] w = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = solution(n, w);
        System.out.print(answer);
    }
}