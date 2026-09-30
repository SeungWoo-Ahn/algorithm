import java.util.*;

class Solution {
    static class Node {
        final String data;
        Node prev, next;
        
        public Node(String data) {
            this.data = data;
            this.prev = this.next = null;
        }
        
        public void link(Node head) {
            if (head != null) {
                head.prev = this;
            }
            this.next = head;
        }
        
        public void unlink() {
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            this.prev = this.next = null;
        }
    }
    
    static class History {
        private Node head, tail;
        
        public History() {
            this.head = this.tail = null;
        }
        
        public void add(Node node) {
            if (head == null) {
                head = tail = node;
            } else {
                node.link(head);
                head = node;
            }
        }
        
        public void refresh(Node node) {
            if (node == head) return;
            if (node == tail) {
                Node prev = node.prev;
                node.unlink();
                node.link(head);
                tail = prev;
                head = node;
                return;
            }
            node.unlink();
            node.link(head);
            head = node;
        }
        
        public Node evict() {
            if (tail == null) {
                return null;
            }
            if (head == tail) {
                Node last = tail;
                head = tail = null;
                return last;
            }
            Node last = tail;
            Node prev = last.prev;
            last.unlink();
            tail = prev;
            return last;
        }
    }
    
    static class LruCache {
        private final Map<String, Node> cache;
        private final History history;
        private final int size;
        
        public LruCache(int size) {
            this.cache = new HashMap<>();
            this.history = new History();
            this.size = size;
        }
        
        public boolean add(String data) {
            if (cache.containsKey(data)) {
                history.refresh(cache.get(data));
                return false;
            }
            if (cache.size() == size) {
                Node last = history.evict();
                if (last != null) {
                    cache.remove(last.data);
                }
            }
            Node node = new Node(data);
            history.add(node);
            cache.put(data, node);
            return true;
        }
    }
    
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        LruCache cache = new LruCache(cacheSize);
        int result = 0;
        for (String city : cities) {
            String data = city.toUpperCase();
            result += cache.add(data) ? 5 : 1;
        }
        return result;
    }
}