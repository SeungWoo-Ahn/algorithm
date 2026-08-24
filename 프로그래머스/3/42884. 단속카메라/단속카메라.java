import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);
        int n = routes.length;
        int en = routes[0][1];
        int result = n;
        for (int i = 1; i < n; i++) {
            if (en >= routes[i][0]) {
                result--;
                en = Math.min(en, routes[i][1]);
            } else {
                en = routes[i][1];
            }
        }
        return result;
    }
}