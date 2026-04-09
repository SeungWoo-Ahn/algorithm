import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

class Solution {
    private int getGemsCount(String[] gems) {
        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }
        return set.size();
    }
    
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        int[] result = new int[2];
        int gemsCount = getGemsCount(gems);
        int st = 0;
        int en = 0;
        int size = gems.length;
        while (st < gems.length) {
            if (map.size() == gemsCount) {
                if (en - st < size) {
                    result[0] = st + 1;
                    result[1] = en;
                    size = en - st;
                } else {
                    int count = map.getOrDefault(gems[st], 0);
                    if (count <= 1) {
                        map.remove(gems[st]);
                    } else {
                        map.put(gems[st], count - 1);
                    }
                    st++;
                }
            } else {
                if (en < gems.length) {
                    map.put(gems[en], map.getOrDefault(gems[en], 0) + 1);
                    en++;
                } else {
                    break;
                }
            }
        }
        if (result[0] == 0 && result[1] == 0) {
            result[0] = 1;
            result[1] = gems.length;
        }
        return result;
    }
}