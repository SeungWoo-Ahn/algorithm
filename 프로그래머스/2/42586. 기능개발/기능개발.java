import java.util.*;

class Solution {    
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> days = new ArrayList<>();
        for (int i = 0; i < progresses.length; i++) {
            int remain = 100 - progresses[i];
            int day = remain / speeds[i];
            if (remain % speeds[i] != 0) {
                day++;
            }
            days.add(day);
        }
        List<Integer> result = new ArrayList<>();
        int idx = 0;
        while (idx < days.size()) {
            int cri = days.get(idx);
            int st = idx;
            while (idx < days.size() - 1 && days.get(idx + 1) <= cri) {
                idx++;
            }
            result.add(idx - st + 1);
            idx++;
        }
        return result;
    }
}