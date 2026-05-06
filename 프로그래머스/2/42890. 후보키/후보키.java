import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

class Solution {
    private boolean isMinimal(int bit, List<Integer> result) {
        for (int key : result) {
            if ((bit & key) == key) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isUnique(int bit, String[][] relation) {
        List<Integer> cols = new ArrayList<>();
        int i = 0;
        while (bit > 0) {
            if ((bit & 1) != 0) {
                cols.add(i);
            }
            i++;
            bit = bit >> 1;
        }
        Set<String> set = new HashSet<>();
        for (String[] tuple : relation) {
            StringBuilder sb = new StringBuilder();
            for (int col : cols) {
                sb.append(tuple[col]).append(' ');
            }
            String key = sb.toString().trim();
            if (!set.add(key)) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(String[][] relation) {
        int colLen = relation[0].length;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int col = 0; col < colLen; col++) {
            int bit = 1 << col;
            q.add(bit);
            visited.add(bit);
        }
        while (!q.isEmpty()) {
            int cur = q.poll();            
            if (!isMinimal(cur, result)) continue;
            if (isUnique(cur, relation)) {
                result.add(cur);
                continue;
            }
            for (int i = 0; i < colLen; i++) {
                int next = cur | (1 << i);
                if (next == cur || visited.contains(next)) continue;
                q.add(next);
                visited.add(next);
            }
        }
        return result.size();
    }
}