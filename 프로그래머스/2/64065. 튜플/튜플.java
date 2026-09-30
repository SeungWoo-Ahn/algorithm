import java.util.*;

class Solution {
    public List<Integer> solution(String s) {
        List<String[]> tuples = new ArrayList<>();
        int idx = 0;
        while (idx < s.length()) {
            while (s.charAt(idx) == '{') idx++;
            int st = idx;
            while (s.charAt(idx) != '}') idx++;
            String[] tuple = s.substring(st, idx).split(",");
            tuples.add(tuple);
            idx += 2;
        }
        Collections.sort(tuples, (o1, o2) -> o1.length - o2.length);
        Set<String> v = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (String[] tuple : tuples) {
            for (String member : tuple) {
                if (v.add(member)) {
                    result.add(Integer.parseInt(member));
                    break;
                }
            }
        }
        return result;
    }
}