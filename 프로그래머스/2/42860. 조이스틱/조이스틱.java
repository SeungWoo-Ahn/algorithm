import java.util.List;
import java.util.ArrayList;

class Solution {
    private static class Group {
        final int st;
        final int en;
        
        public Group(int st, int en) {
            this.st = st;
            this.en = en;
        }
    }
    
    private int calcMinChange(char target) {
        int code = target - 'A';
        return Math.min(code, 26 - code);
    }
    
    public int solution(String name) {
        int[] changeCnt = new int[name.length()];
        int changeCntSum = 0;
        for (int i = 0; i < name.length(); i++) {
            changeCnt[i] = calcMinChange(name.charAt(i));
            changeCntSum += changeCnt[i];
        }
        if (changeCntSum == 0) {
            return 0;
        }
        List<Group> groups = new ArrayList<>();
        int st = 0;
        while (st < changeCnt.length) {
            if (changeCnt[st] == 0) {
                st++;
                continue;
            }
            int en = st;
            while (en < changeCnt.length - 1 && changeCnt[en + 1] != 0) {
                en++;
            }
            groups.add(new Group(st, en));
            st = en + 1;
        }
        int moveCntSum = Integer.MAX_VALUE;
        for (int i = 0; i < groups.size(); i++) {
            if (i == groups.size() - 1) {
                int sum = groups.get(i).en;
                moveCntSum = Math.min(moveCntSum, sum);
                continue;
            }
            Group first = groups.get(i);
            Group second = groups.get(i + 1);
            int case1 = first.en * 2 + name.length() - second.st;
            int case2 = first.en + (name.length() - second.st) * 2;
            moveCntSum = Math.min(moveCntSum, Math.min(case1, case2));
        }
        return changeCntSum + moveCntSum;
    }
}