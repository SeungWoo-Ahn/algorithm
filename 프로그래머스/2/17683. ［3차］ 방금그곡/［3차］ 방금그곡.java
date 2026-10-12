import java.util.*;

class Solution {
    static class Music implements Comparable<Music> {
        final int id;
        final int playTime;
        final String title;
        
        public Music(int id, int playTime, String title) {
            this.id = id;
            this.playTime = playTime;
            this.title = title;
        }
        
        @Override
        public int compareTo(Music o) {
            if (playTime != o.playTime) {
                return o.playTime - playTime;
            }
            return id - o.id;
        }
    }
    
    private List<String> alpha = List.of("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
    
    private String convert(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            String target;
            if (i < s.length() - 1 && s.charAt(i + 1) == '#') {
                target = s.substring(i, i + 2);
                i += 2;
            } else {
                target = s.substring(i, i + 1);
                i += 1;
            }
            String key = Integer.toString(alpha.indexOf(target), alpha.size());
            sb.append(key);
        }
        return sb.toString();
    }
    
    private int minuate(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3));
        return hh * 60 + mm;
    }
    
    public String solution(String m, String[] musicinfos) {
        String target = convert(m);
        List<Music> matched = new ArrayList<>();
        for (int id = 0; id < musicinfos.length; id++) {
            String[] info = musicinfos[id].split(",");
            int playTime = minuate(info[1]) - minuate(info[0]);
            String title = info[2];
            String unit = convert(info[3]);
            String melody;
            if (unit.length() > playTime) {
                melody = unit.substring(0, playTime);
            } else {
                StringBuilder sb = new StringBuilder();
                int repeat = playTime / unit.length();
                int remain = playTime % unit.length();
                while (repeat-- > 0) sb.append(unit);
                sb.append(unit.substring(0, remain));
                melody = sb.toString();
            }
            if (melody.length() >= target.length() && melody.contains(target)) {
                matched.add(new Music(id, playTime, title));
            }
        }
        if (matched.isEmpty()) {
            return "(None)";
        }
        if (matched.size() > 1) {
            Collections.sort(matched);
        }
        return matched.get(0).title;
    }
}