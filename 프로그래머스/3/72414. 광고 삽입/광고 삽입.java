class Solution {
    private int getSec(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3, 5));
        int ss = Integer.parseInt(time.substring(6));
        return hh * 3_600 + mm * 60 + ss;
    }
    
    private String getTime(int sec) {
        int hh = sec / 3_600;
        int mm = sec % 3_600 / 60;
        int ss = sec % 3_600 % 60;
        return String.format("%02d:%02d:%02d", hh, mm, ss);
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = getSec(play_time);
        int advSec = getSec(adv_time);
        long[] acc = new long[playSec + 1];
        for (String log : logs) {
            int st = getSec(log.substring(0, 8));
            int en = getSec(log.substring(9));
            acc[st]++;
            acc[en]--;
        }
        for (int sec = 1; sec <= playSec; sec++) {
            acc[sec] += acc[sec - 1];
        }
        for (int sec = 1; sec <= playSec; sec++) {
            acc[sec] += acc[sec - 1];
        }
        long max = acc[advSec];
        int maxSec = 0;
        for (int sec = 1; sec <= playSec - advSec; sec++) {
            long r = acc[sec + advSec - 1] - acc[sec - 1];
            if (r > max) {
                max = r;
                maxSec = sec;
            }
        }
        return getTime(maxSec);
    }
}