import java.util.*;

class Solution {
    private int getMinuate(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3, 5));
        return hh * 60 + mm;
    }
    
    private String getTime(int minuate) {
        int hh = minuate / 60;
        int mm = minuate % 60;
        StringBuilder sb = new StringBuilder();
        if (hh < 10) sb.append(0);
        sb.append(hh);
        sb.append(':');
        if (mm < 10) sb.append(0);
        sb.append(mm);
        return sb.toString();
    }
    
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        List<Integer>[] table = new List[n];
        int base = getMinuate("09:00");
        int idx = 0;
        for (int i = 0; i < n; i++) {
            table[i] = new ArrayList<>();
            int lastMinuate = base + i * t;
            while (table[i].size() < m && 
                   idx < timetable.length && 
                   getMinuate(timetable[idx]) <= lastMinuate) {
                table[i].add(getMinuate(timetable[idx++]));
            }
            if (idx >= timetable.length) break;
        }
        int target;
        if (table[n - 1].size() < m) {
            target = base + (n - 1) * t;
        } else {
            target = table[n - 1].get(table[n - 1].size() - 1) - 1;
        }
        return getTime(target);
    }
}