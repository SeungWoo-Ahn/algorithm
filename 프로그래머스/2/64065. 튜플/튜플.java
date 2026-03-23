import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

class Solution {
    private static class Tuple implements Comparable<Tuple> {
        final List<Integer> data;
        
        public Tuple(List<Integer> data) {
            this.data = data;
        }
        
        @Override
        public int compareTo(Tuple o) {
            return data.size() - o.data.size();
        }
    }
    
    public int[] solution(String s) {
        List<Tuple> tuples = new ArrayList<>();
        int index = 1;
        while (index < s.length() - 1) {
            int st = index + 1;
            int en = st;
            while (en < s.length() - 1 && s.charAt(en) != '}') {
                en++;
            }
            List<Integer> data = Arrays.stream(s.substring(st, en).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            tuples.add(new Tuple(data));
            index = en + 2;
        }
        Collections.sort(tuples);
        int[] result = new int[tuples.size()];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < result.length; i++) {
            for (int num : tuples.get(i).data) {
                if (set.add(num)) {
                    result[i] = num;
                    break;
                }
            }
        }
        return result;
    }
}