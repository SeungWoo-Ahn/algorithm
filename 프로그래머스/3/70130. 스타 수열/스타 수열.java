import java.util.*;

class Solution {
    static class Group implements Comparable<Group> {
        final int num;
        final List<Integer> idxs;
        
        public Group(int num, List<Integer> idxs) {
            this.num = num;
            this.idxs = idxs;
        }
        
        @Override
        public int compareTo(Group o) {
            return o.idxs.size() - idxs.size();
        }
    }
    
    public int solution(int[] a) {
        if (a.length < 2) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.putIfAbsent(a[i], new ArrayList<>());
            map.get(a[i]).add(i);
        }
        List<Group> groups = new ArrayList<>();
        for (int num : map.keySet()) {
            groups.add(new Group(num, map.get(num)));
        }
        Collections.sort(groups);
        int result = 0;
        for (Group group : groups) {
            if (group.idxs.size() * 2 < result) {
                return result;
            }
            int target = group.num;
            int prevIdx = -1;
            int cnt = 0;
            for (int idx : group.idxs) {
                if (idx == 0) {
                    if (a[idx + 1] != target) {
                        prevIdx = idx + 1;
                        cnt++;
                    }
                } else if (idx == a.length - 1) {
                    if (a[idx - 1] != target && idx - 1 > prevIdx) {
                        prevIdx = idx - 1;
                        cnt++;
                    }
                } else if (a[idx - 1] != target && idx - 1 > prevIdx) {
                    prevIdx = idx - 1;
                    cnt++;
                } else if (a[idx + 1] != target) {
                    prevIdx = idx + 1;
                    cnt++;
                }
            }
            if (cnt * 2 > result) {
                result = cnt * 2;
            }
        }
        return result;
    }
}