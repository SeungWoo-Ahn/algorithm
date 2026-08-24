import java.util.*;

class Solution {
    private int calcUnitCnt(int cnt, int unit) {
        return cnt / unit + ((cnt % unit != 0) ? 1 : 0);
    }
    
    public int solution(int n, int[] stations, int w) {
        List<int[]> ranges = new ArrayList<>();
        int st = stations[0] - w;
        int en = stations[0] + w;
        for (int i = 1; i < stations.length; i++) {
            if (en + 1 < stations[i] - w) {
                ranges.add(new int[]{st, en});
                st = stations[i] - w;
            }
            en = stations[i] + w;
        }
        ranges.add(new int[]{st, en});
        int result = 0;
        int unit = w * 2 + 1;
        if (ranges.get(0)[0] > 1) {
            int cnt = ranges.get(0)[0] - 1;
            result += calcUnitCnt(cnt, unit);
        }
        for (int i = 1; i < ranges.size(); i++) {
            int cnt = ranges.get(i)[0] - ranges.get(i - 1)[1] - 1;
            result += calcUnitCnt(cnt, unit);
        }
        if (ranges.get(ranges.size() - 1)[1] < n) {
            int cnt = n - ranges.get(ranges.size() - 1)[1];
            result += calcUnitCnt(cnt, unit);
        }
        return result;
    }
}