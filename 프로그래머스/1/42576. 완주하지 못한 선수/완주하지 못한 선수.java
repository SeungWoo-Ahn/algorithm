import java.util.Map;
import java.util.HashMap;

class Solution {
    private Map<String, Integer> getMap(String[] inputs) {
        Map<String, Integer> map = new HashMap<>();
        for (String input : inputs) {
            map.put(input, map.getOrDefault(input, 0) + 1);
        }
        return map;
    }
    
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> pMap = getMap(participant);
        Map<String, Integer> cMap = getMap(completion);
        String result = "";
        for (String key : pMap.keySet()) {
            int pi = pMap.get(key);
            int ci = cMap.getOrDefault(key, -1);
            if (pi != ci) {
                result = key;
                break;
            }
        }
        return result;
    }
}