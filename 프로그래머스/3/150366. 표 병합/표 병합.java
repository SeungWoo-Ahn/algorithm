import java.util.*;

class Solution {
    private static final int SIZE = 50 * 50 + 1;
    private int[] parent = new int[SIZE];
    private String[] values = new String[SIZE];
    private List<String> result = new ArrayList<>();
    
    private void setParent() {
        for (int i = 0; i < SIZE; i++) {
            parent[i] = i;
        }
    }
    
    private int getKey(int r, int c) {
        return (r - 1) * 50 + (c - 1);
    }
    
    private int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    private void update(int r, int c, String value) {
        int x = getKey(r, c);
        int root = find(x);
        values[root] = value;
    }
    
    private void update(String value1, String value2) {
        for (int i = 0; i < SIZE; i++) {
            if (Objects.equals(values[i], value1)) {
                values[i] = value2;
            }
        }
    }
    
    private void merge(int r1, int c1, int r2, int c2) {
        int x = getKey(r1, c1);
        int y = getKey(r2, c2);
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) return;
        
        int root;
        if (rootX < rootY) {
            root = rootX;
            parent[rootY] = rootX;
        } else {
            root = rootY;
            parent[rootX] = rootY;
        }
        
        String value;
        if (values[rootX] == null && values[rootY] != null) {
            value = values[rootY];
        } else {
            value = values[rootX];
        }
        values[rootX] = null;
        values[rootY] = null;
        values[root] = value;
    }
    
    private void unmerge(int r, int c) {
        int x = getKey(r, c);
        int root = find(x);
        String value = values[root];
        List<Integer> group = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            if (find(i) == root) {
                group.add(i);
            }
        }
        for (int num : group) {
            parent[num] = num;
            values[num] = null;
        }
        values[x] = value;
    }
    
    private void print(int r, int c) {
        int x = getKey(r, c);
        int root = find(x);
        String value = values[root] == null ? "EMPTY" : values[root];
        result.add(value);
    }
    
    public List<String> solution(String[] commands) {
        setParent();
        for (String command : commands) {
            String[] sp = command.split(" ");
            if ("UPDATE".equals(sp[0])) {
                if (sp.length == 4) {
                    int r = Integer.parseInt(sp[1]);
                    int c = Integer.parseInt(sp[2]);
                    update(r, c, sp[3]);
                } else {
                    update(sp[1], sp[2]);
                }
            } else if ("MERGE".equals(sp[0])) {
                int r1 = Integer.parseInt(sp[1]);
                int c1 = Integer.parseInt(sp[2]);
                int r2 = Integer.parseInt(sp[3]);
                int c2 = Integer.parseInt(sp[4]);
                merge(r1, c1, r2, c2);
            } else if ("UNMERGE".equals(sp[0])) {
                int r = Integer.parseInt(sp[1]);
                int c = Integer.parseInt(sp[2]);
                unmerge(r, c);
            } else if ("PRINT".equals(sp[0])) {
                int r = Integer.parseInt(sp[1]);
                int c = Integer.parseInt(sp[2]);
                print(r, c);
            }
        }
        return result;
    }
}