import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }
        Map<String, Integer> map = new HashMap<>();
        int kind = set.size();
        int left = 0;
        int right = 0;
        int[] result = new int[]{1, gems.length};
        map.put(gems[0], 1);
        while (left <= right) {
            if (map.size() == kind && right - left < result[1] - result[0]) {
                result[0] = left + 1;
                result[1] = right + 1;
            }
            if (map.size() < kind) {
                if (++right == gems.length) break;
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            } else {
                map.put(gems[left], map.get(gems[left]) - 1);
                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
            }
        }
        return result;
    }
}