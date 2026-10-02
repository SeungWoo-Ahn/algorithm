import java.util.*;

class Solution {
    private int minute(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3, 5));
        return hh * 60 + mm;
    }
    
    public List<Integer> solution(int[] fees, String[] records) {
        Map<String, Integer> in = new HashMap<>();
        Map<String, Integer> acc = new HashMap<>();
        for (String record : records) {
            String[] info = record.split(" ");
            int minute = minute(info[0]);
            String key = info[1];
            if ("IN".equals(info[2])) {
                in.put(key, minute);
            } else if ("OUT".equals(info[2])) {
                acc.put(key, acc.getOrDefault(key, 0) + minute - in.get(key));
                in.remove(key);
            }
        }
        int last = minute("23:59");
        for (String key : in.keySet()) {
            acc.put(key, acc.getOrDefault(key, 0) + last - in.get(key));
        }
        List<String> keys = new ArrayList<>(acc.keySet());
        Collections.sort(keys);
        List<Integer> result = new ArrayList<>();
        for (String key : keys) {
            int used = acc.get(key);
            int cost = fees[1];
            if (used > fees[0]) {
                int diff = used - fees[0];
                int t = diff / fees[2];
                if (diff % fees[2] != 0) {
                    t++;
                }
                cost += t * fees[3];
            }
            result.add(cost);
        }
        return result;
    }
}