import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    private Map<Integer, Integer> getCntBySize(int[] tangerine) {
        Map<Integer, Integer> cntBySize = new HashMap<>();
        for (int size : tangerine) {
            cntBySize.put(size, cntBySize.getOrDefault(size, 0) + 1);
        }
        return cntBySize;
    }
    
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> cntBySize = getCntBySize(tangerine);
        List<Integer> cnts = new ArrayList<>(cntBySize.values());
        cnts.sort(Collections.reverseOrder());
        int type = 0;
        int acc = 0;
        for (int cnt : cnts) {
            type++;
            acc += cnt;
            if (acc >= k) break;
        }
        return type;
    }
}