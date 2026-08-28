import java.util.*;

class Solution {
    static class Node {
        final int num;
        Node prev, next;
        
        public Node(int num) {
            this.num = num;
            this.prev = null;
            this.next = null;
        }
        
        public Node remove() {
            prev.next = next;
            next.prev = prev;
            if (next.next == null) {
                return prev;
            }
            return next;
        }
        
        public void restore() {
            prev.next = this;
            next.prev = this;
        }
    }
    
    private Node cursor;
    private Deque<Node> history = new ArrayDeque<>();
    
    private void setNodes(int n) {
        Node start = new Node(-1);
        Node prev = start;
        for (int num = 0; num < n; num++) {
            Node cur = new Node(num);
            prev.next = cur;
            cur.prev = prev;
            prev = cur;
        }
        
        Node end = new Node(-1);
        prev.next = end;
        end.prev = prev;
        
        cursor = start.next;
    }
    
    private void u(int x) {
        for (int i = 0; i < x; i++) {
            cursor = cursor.prev;
        }
    }
    
    private void d(int x) {
        for (int i = 0; i < x; i++) {
            cursor = cursor.next;
        }
    }
    
    private void c() {
        history.addLast(cursor);
        cursor = cursor.remove();
    }
    
    private void z() {
        Node removedLast = history.removeLast();
        removedLast.restore();
    }
    
    public String solution(int n, int k, String[] cmd) {
        setNodes(n);
        d(k);
        for (String c : cmd) {
            String[] sp = c.split(" ");
            if ("U".equals(sp[0])) {
                int x = Integer.parseInt(sp[1]);
                u(x);
            } else if ("D".equals(sp[0])) {
                int x = Integer.parseInt(sp[1]);
                d(x);
            } else if ("C".equals(sp[0])) {
                c();
            } else if ("Z".equals(sp[0])) {
                z();
            }
        }
        char[] result = new char[n];
        Arrays.fill(result, 'O');
        for (Node node : history) {
            result[node.num] = 'X';
        }
        StringBuilder sb = new StringBuilder();
        for (char r : result)
            sb.append(r);
        return sb.toString();
    }
}