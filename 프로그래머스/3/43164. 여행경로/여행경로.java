import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    private static class Ticket implements Comparable<Ticket> {
        final String from;
        final String to;
        
        public Ticket(String[] ticket) {
            this.from = ticket[0];
            this.to = ticket[1];
        }
        
        @Override
        public int compareTo(Ticket o) {
            if (from.equals(o.from)) {
                return to.compareTo(o.to);
            }
            return from.compareTo(o.from);
        }
    }
    
    private boolean backtracking(int depth, String cur, List<Ticket> ts, String[] result, boolean[] visited) {
        if (depth == ts.size()) {
            return true;
        }
        for (int i = 0; i < ts.size(); i++) {
            Ticket ticket = ts.get(i);
            if (!ticket.from.equals(cur)) continue;
            if (visited[i]) continue;
            visited[i] = true;
            result[depth + 1] = ticket.to;
            if (backtracking(depth + 1, ticket.to, ts, result, visited)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }
    
    public String[] solution(String[][] tickets) {
        List<Ticket> ts = new ArrayList<>();
        for (String[] ticket : tickets) {
            ts.add(new Ticket(ticket));
        }
        Collections.sort(ts);
        String[] result = new String[ts.size() + 1];
        boolean[] visited = new boolean[ts.size()];
        result[0] = "ICN";
        backtracking(0, "ICN", ts, result, visited);
        return result;
    }
}