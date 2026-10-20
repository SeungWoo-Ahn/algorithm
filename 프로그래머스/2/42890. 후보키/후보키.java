import java.util.*;

class Solution {
    private final List<Integer> cand = new ArrayList<>();
    
    private void comb(int depth, int st, int[] cols, String[][] relation) {
        if (depth > 0 && check(depth, cols, relation)) {
            int bit = 0;
            for (int i = 0; i < depth; i++) {
                bit |= 1 << cols[i];
            }
            cand.add(bit);
            return;
        }
        if (depth == cols.length) {
            return;
        }
        for (int i = st; i < cols.length; i++) {
            cols[depth] = i;
            comb(depth + 1, i + 1, cols, relation);
        }
    }
    
    private boolean check(int depth, int[] cols, String[][] relation) {
        Set<String> set = new HashSet<>();
        for (String[] r : relation) {
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                key.append(r[cols[i]]).append(' ');
            }
            if (!set.add(key.toString())) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(String[][] relation) {
        int m = relation[0].length;
        comb(0, 0, new int[m], relation);
        Collections.sort(cand, (o1, o2) -> {
            int bc1 = Integer.bitCount(o1);
            int bc2 = Integer.bitCount(o2);
            if (bc1 != bc2) return bc1 - bc2;
            return o1 - o2;
        });
        List<Integer> result = new ArrayList<>();
        for (int key : cand) {
            boolean minimal = true;
            for (int k : result) {
                if ((key & k) == k) {
                    minimal = false;
                    break;
                }
            }
            if (minimal)
                result.add(key);
        }
        return result.size();
    }
}