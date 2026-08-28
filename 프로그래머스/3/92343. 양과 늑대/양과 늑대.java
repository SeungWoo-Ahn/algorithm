import java.util.*;

class Solution {
    private int[] info, cnt;
    private List<Integer>[] children;
    private int result = 0;
    
    private void dfs(int cur, List<Integer> path) {
        if (cnt[0] <= cnt[1]) {
            return;
        }
        if (cnt[0] > result) {
            result = cnt[0];
        }
        List<Integer> npath = new ArrayList<>();
        for (int p : path) {
            if (p == cur) continue;
            npath.add(p);
        }
        for (int child : children[cur]) {
            npath.add(child);
        }
        for (int nxt : npath) {
            cnt[info[nxt]]++;
            dfs(nxt, npath);
            cnt[info[nxt]]--;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        int n = info.length;
        children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            children[edge[0]].add(edge[1]);
        }
        List<Integer> path = new ArrayList<>();
        cnt = new int[2];
        cnt[info[0]]++;
        dfs(0, path);
        return result;
    }
}