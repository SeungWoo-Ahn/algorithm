class Solution {
    private int getSeconds(String time) {
        int mm = Integer.parseInt(time.substring(0, 2));
        int ss = Integer.parseInt(time.substring(3));
        return mm * 60 + ss;
    }
    
    private String getTime(int seconds) {
        StringBuilder sb = new StringBuilder();
        int mm = seconds / 60;
        int ss = seconds % 60;
        if (mm < 10) {
            sb.append('0');
        }
        sb.append(mm);
        sb.append(':');
        if (ss < 10) {
            sb.append('0');
        }
        sb.append(ss);
        return sb.toString();
    }
    
    private int prev(int seconds) {
        return seconds - 10 < 0 ? 0 : seconds - 10;
    }
    
    private int next(int seconds, int len) {
        return seconds + 10 > len ? len : seconds + 10;
    }
    
    private int jump(int seconds, int[] op) {
        if (op[0] <= seconds && seconds <= op[1]) {
            return op[1];
        }
        return seconds;
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int len = getSeconds(video_len);
        int cur = getSeconds(pos);
        int[] op = {getSeconds(op_start), getSeconds(op_end)};
        cur = jump(cur, op);
        for (String command : commands) {
            if (command.equals("prev")) {
                cur = prev(cur);
            } else if (command.equals("next")) {
                cur = next(cur, len);
            }
            cur = jump(cur, op);
        }
        return getTime(cur);
    }
}