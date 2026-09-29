import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> m = new HashMap<>();
        Map<Integer, Integer> o = new HashMap<>();
        for (int t : topping) {
            o.put(t, o.getOrDefault(t, 0) + 1);
        }
        int result = 0;
        for (int t : topping) {
            m.put(t, m.getOrDefault(t, 0) + 1);
            o.put(t, o.getOrDefault(t, 0) - 1);
            if (o.get(t) <= 0) {
                o.remove(t);
            }
            if (m.size() == o.size()) {
                result++;
            }
        }
        return result;
    }
}