import java.util.*;

class Solution {
    static class Log {
        final int start;
        final int end;
        
        public Log(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    private Log convert(String line) {
        String[] sp = line.split(" ");
        int end = ms(sp[1]);
        String s = sp[2].substring(0, sp[2].length() - 1);
        int process = (int) Math.round(Double.parseDouble(s) * 1_000);
        int start = end - process + 1;
        return new Log(start, end);
    }
    
    private int ms(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3, 5));
        int ss = Integer.parseInt(time.substring(6, 8));
        int sss = Integer.parseInt(time.substring(9));
        return hh * 60 * 60 * 1_000 + mm * 60 * 1_000 + ss * 1_000 + sss;
    }
    
    public int solution(String[] lines) {
        List<Log> logs = new ArrayList<>();
        for (String line : lines) {
            Log log = convert(line);
            logs.add(log);
        }
        int result = 1;
        for (int i = 0; i < logs.size() - 1; i++) {
            int windowEnd = logs.get(i).end + 1_000;
            int count = 1;
            for (int j = i + 1; j < logs.size(); j++) {
                if (logs.get(j).start < windowEnd) {
                    count++;
                }
            }
            result = Math.max(result, count);
        }
        return result;
    }
}