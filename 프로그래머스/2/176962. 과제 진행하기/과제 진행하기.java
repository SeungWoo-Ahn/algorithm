import java.util.*;

class Solution {
    static class Work implements Comparable<Work> {
        final String name;
        final int start;
        final int play;
        
        public Work(String name, int start, int play) {
            this.name = name;
            this.start = start;
            this.play = play;
        }
        
        @Override
        public int compareTo(Work o) {
            return start - o.start;
        }
    }
    
    private int minuate(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3));
        return hh * 60 + mm;
    }
    
    public List<String> solution(String[][] plans) {
        List<Work> seq = new ArrayList<>();
        Deque<Work> ready = new ArrayDeque<>();
        for (String[] plan : plans) {
            String name = plan[0];
            int start = minuate(plan[1]);
            int play = Integer.parseInt(plan[2]);
            seq.add(new Work(name, start, play));
        }
        Collections.sort(seq);
        List<String> result = new ArrayList<>();
        int idx = 0;
        while (idx < seq.size()) {
            if (idx == seq.size() - 1) {
                result.add(seq.get(idx).name);
                break;
            }
            Work w = seq.get(idx);
            Work nw = seq.get(idx + 1);
            if (w.start + w.play <= nw.start) {
                result.add(w.name);
                int remain = nw.start - (w.start + w.play);
                while (!ready.isEmpty() && remain > 0) {
                    Work r = ready.removeLast();
                    if (r.play <= remain) {
                        result.add(r.name);
                        remain -= r.play;
                    } else {
                        ready.addLast(new Work(r.name, r.start, r.play - remain));
                        break;
                    }
                }
            } else {
                int need = w.start + w.play - nw.start;
                ready.addLast(new Work(w.name, w.start, need));
            }
            idx++;
        }
        while (!ready.isEmpty()) {
            Work w = ready.removeLast();
            result.add(w.name);
        }
        return result;
    }
}