import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<int[]> min = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> max = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        Set<Integer> deleted = new HashSet<>();
        for (int i = 0; i < operations.length; i++) {
            String[] sp = operations[i].split(" ");
            if (sp[0].equals("I")) {
                int num = Integer.parseInt(sp[1]);
                min.add(new int[]{num, i});
                max.add(new int[]{num, i});
                continue;
            }
            if (sp[1].equals("1")) {
                while (!max.isEmpty() && deleted.contains(max.peek()[1])) {
                    max.poll();
                }
                if (max.isEmpty()) continue;
                deleted.add(max.poll()[1]);
            } else {
                while (!min.isEmpty() && deleted.contains(min.peek()[1])) {
                    min.poll();
                }
                if (min.isEmpty()) continue;
                deleted.add(min.poll()[1]);
            }
        }
        while (!max.isEmpty() && deleted.contains(max.peek()[1])) {
            max.poll();
        }
        while (!min.isEmpty() && deleted.contains(min.peek()[1])) {
            min.poll();
        }
        if (min.isEmpty() || max.isEmpty()) {
            return new int[]{0, 0};
        }
        return new int[]{max.peek()[0], min.peek()[0]};
    }
}