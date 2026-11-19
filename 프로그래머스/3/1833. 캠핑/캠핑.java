import java.util.*;

class Solution {
    // n개의 쐐기, 한 쌍을 골라 텐트 설치
    // 텐트 넓이 > 0, 내부에 쐐기 포함X
    // 조건을 만족하는 쐐기 쌍의 개수 반환
    // (x1, y1) ~ (x2, y2) 범위에 다른 쐐기가 없으면 ok
    public int solution(int n, int[][] data) {
        Arrays.sort(data, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            int[] a = data[i];
            int pMax = Integer.MAX_VALUE;
            int pMin = Integer.MIN_VALUE;
            int cMax = Integer.MAX_VALUE;
            int cMin = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                int[] b = data[j];
                if (a[0] == b[0]) continue;
                if (b[1] > a[1]) {
                    if (pMax >= b[1]) {
                        cMax = Math.min(cMax, b[1]);
                        result++;
                    }
                } else if (b[1] < a[1]) {
                    if (pMin <= b[1]) {
                        cMin = Math.max(cMin, b[1]);
                        result++;
                    }
                }
                if (j + 1 < n && b[0] != data[j + 1][0]) {
                    pMax = cMax;
                    pMin = cMin;
                }
            }
        }
        return result;
    }
}