import java.util.*;

class Solution {
    private final Map<String, List<Integer>> map = new HashMap<>();
    
    private void insert(int depth, String[] data, String[] arr) {
        if (depth == arr.length) {
            String key = key(arr);
            int score = Integer.parseInt(data[4]);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(score);
            return;
        }
        arr[depth] = data[depth];
        insert(depth + 1, data, arr);
        arr[depth] = "-";
        insert(depth + 1, data, arr);
    }
    
    private String key(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }
    
    private int lowerBound(int target, List<Integer> v) {
        int st = 0;
        int en = v.size();
        while (st < en) {
            int mid = st + (en - st) / 2;
            if (v.get(mid) >= target) {
                en = mid;
            } else {
                st = mid + 1;
            }
        }
        return st;
    }
    
    public int[] solution(String[] info, String[] query) {
        for (String in : info) {
            String[] data = in.split(" ");
            insert(0, data, new String[4]);
        }
        for (List<Integer> v : map.values()) {
            Collections.sort(v);
        }
        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] data = query[i].split(" ");
            String[] arr = {data[0], data[2], data[4], data[6]};
            String key = key(arr);
            List<Integer> v = map.get(key);
            if (v == null) continue;
            int score = Integer.parseInt(data[7]);
            result[i] = v.size() - lowerBound(score, v);
        }
        return result;
    }
}