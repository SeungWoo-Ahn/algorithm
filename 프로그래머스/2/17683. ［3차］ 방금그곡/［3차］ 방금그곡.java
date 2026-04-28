import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Solution {
    private Map<String, Integer> getIdxMap() {
        Map<String, Integer> idxMap = new HashMap<>();
        idxMap.put("C", 0);
        idxMap.put("C#", 1);
        idxMap.put("D", 2);
        idxMap.put("D#", 3);
        idxMap.put("E", 4);
        idxMap.put("F", 5);
        idxMap.put("F#", 6);
        idxMap.put("G", 7);
        idxMap.put("G#", 8);
        idxMap.put("A", 9);
        idxMap.put("A#", 10);
        idxMap.put("B", 11);
        return idxMap;
    }
    
    private String getOcta(String str, Map<String, Integer> idxMap) {
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        while (cur < str.length()) {
            String target;
            if (cur + 1 < str.length() && str.charAt(cur + 1) == '#') {
                target = str.substring(cur, cur + 2);
                cur += 2;
            } else {
                target = str.substring(cur, cur + 1);
                cur++;
            }
            int idx = idxMap.get(target);
            sb.append(Integer.toString(idx, 16));
        }
        return sb.toString();
    }
    
    private Set<Character> getMelodySet(String melody) {
        Set<Character> melodySet = new HashSet<>();
        for (int i = 0; i < melody.length(); i++) {
            melodySet.add(melody.charAt(i));
            if (melodySet.size() == 12) break;
        }
        return melodySet;
    }
    
    private int getMinutes(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3));
        return hh * 60 + mm;
    }
    
    public String solution(String m, String[] musicinfos) {
        Map<String, Integer> idxMap = getIdxMap();
        String melodyOcta = getOcta(m, idxMap);
        Set<Character> melodySet = getMelodySet(melodyOcta);
        String result = "(None)";
        int maxPlayTime = 0;
        for (String info : musicinfos) {
            String[] sp = info.split(",");
            String octa = getOcta(sp[3], idxMap);
            Set<Character> set = getMelodySet(octa);
            boolean enable = true;
            for (char ch : melodySet) {
                if (!set.contains(ch)) {
                    enable = false;
                    continue;
                }
            }
            if (!enable) continue;
            int playTime = getMinutes(sp[1]) - getMinutes(sp[0]);
            int cnt = playTime / octa.length();
            int rest = playTime % octa.length();
            StringBuilder sb = new StringBuilder();
            while (cnt-- > 0) {
                sb.append(octa);
            }
            if (rest > 0) {
                sb.append(octa.substring(0, rest));
            }
            if (sb.toString().contains(melodyOcta) && playTime > maxPlayTime) {
                result = sp[2];
                maxPlayTime = playTime;
            }
        }
        return result;
    }
}