import java.util.*;

class Solution {    
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for (String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }
        List<Integer> cnts = new ArrayList<>(map.values());
        int result = 1;
        for (int cnt : cnts) {
            result *= (cnt + 1);
        }
        return result - 1;
    }
}