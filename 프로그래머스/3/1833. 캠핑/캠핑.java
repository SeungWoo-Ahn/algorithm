import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] data) {
        Arrays.sort(data, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] one = data[i];
                int[] two = data[j];
                if (one[0] == two[0] || one[1] == two[1]) continue;
                boolean ok = true;
                for (int k = i + 1; k < n; k++) {
                    int[] d = data[k];
                    if (one[0] < d[0] && d[0] < two[0] &&
                       Math.min(one[1], two[1]) < d[1] && d[1] < Math.max(one[1], two[1])) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    result++;
                }
            }
        }
        return result;
    }
}