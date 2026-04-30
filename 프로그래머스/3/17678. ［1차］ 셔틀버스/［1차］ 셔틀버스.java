import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    private int getMinutes(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3));
        return hh * 60 + mm;
    }
    
    private int[] getMinutesTable(String[] timetable) {
        int[] minutesTable = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            minutesTable[i] = getMinutes(timetable[i]);
        }
        Arrays.sort(minutesTable);
        return minutesTable;
    }
    
    private List<List<Integer>> grouping(int n, int t, int m, int[] minutesTable) {
        List<List<Integer>> groups = new ArrayList<>();
        int cur = getMinutes("09:00");
        int end = getMinutes("23:59");
        int st = 0;
        while (n-- > 0) {
            List<Integer> group = new ArrayList<>();
            int en = st;
            while (en < minutesTable.length && minutesTable[en] <= cur && group.size() < m) {
                group.add(minutesTable[en]);
                en++;
            }
            st = en;
            cur += t;
            groups.add(group);
            if (cur > end) break;
        }
        return groups;
    }
    
    private String getTime(int minutes) {
        StringBuilder sb = new StringBuilder();
        int hh = minutes / 60;
        int mm = minutes % 60;
        if (hh < 10) {
            sb.append(0);
        }
        sb.append(hh);
        sb.append(':');
        if (mm < 10) {
            sb.append(0);
        }
        sb.append(mm);
        return sb.toString();
    }
        
    public String solution(int n, int t, int m, String[] timetable) {
        int[] minutesTable = getMinutesTable(timetable);
        List<List<Integer>> groups = grouping(n, t, m, minutesTable);
        List<Integer> lastGroup = groups.get(groups.size() - 1);
        if (lastGroup.size() == m) {
            int lastMinutes = lastGroup.get(lastGroup.size() - 1);
            return getTime(lastMinutes - 1);
        }
        int busMinutes = getMinutes("09:00") + t * (n - 1);
        return getTime(busMinutes);
    }
}