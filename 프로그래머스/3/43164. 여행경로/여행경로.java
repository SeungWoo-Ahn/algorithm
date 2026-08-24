import java.util.*;

class Solution {
    private String[][] tickets;
    private Map<String, List<Integer>> routes = new HashMap<>();
    private boolean[] used;
    private String result = "a";
    
    private void dfs(int depth, String cur, String path) {
        if (depth == tickets.length) {
            if (path.compareTo(result) < 0) {
                result = path;
            }
            return;
        }
        for (int i : routes.getOrDefault(cur, List.of())) {
            if (used[i]) continue;
            used[i] = true;
            dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1]);
            used[i] = false;
        }
    }
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        for (int i = 0; i < tickets.length; i++) {
            routes.putIfAbsent(tickets[i][0], new ArrayList<>());
            routes.get(tickets[i][0]).add(i);
        }
        used = new boolean[tickets.length];
        dfs(0, "ICN", "ICN");
        return result.split(" ");
    }
}