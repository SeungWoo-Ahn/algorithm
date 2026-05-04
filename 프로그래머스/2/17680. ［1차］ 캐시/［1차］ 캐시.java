import java.util.Map;
import java.util.HashMap;

class Solution {
    private static class Node {
        final String value;
        Node prev, next;
        
        public Node(String value) { 
            this.value = value; 
        }
    }

    private static class OrderList {
        private Node head, tail;

        public Node add(String value) {
            Node node = new Node(value);
            if (head == null) {
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
            return node;
        }

        public void refresh(Node node) {
            if (node == head) return;
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (node == tail) {
                tail = node.prev;
            }
            node.prev = null;
            node.next = head;
            if (head != null) {
                head.prev = node;
            }
            head = node;
        }

        public Node removeLast() {
            if (tail == null) return null;
            Node removed = tail;
            if (head == tail) {
                head = tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            return removed;
        }
    }

    private static class LRUCache {
        private final Map<String, Node> cache = new HashMap<>();
        private final OrderList orderList = new OrderList();
        private final int capacity;

        public LRUCache(int capacity) { 
            this.capacity = capacity; 
        }

        public boolean add(String value) {
            if (cache.containsKey(value)) {
                orderList.refresh(cache.get(value));
                return true;
            }
            if (cache.size() >= capacity) {
                Node old = orderList.removeLast();
                if (old != null) {
                    cache.remove(old.value);
                }
            }
            cache.put(value, orderList.add(value));
            return false;
        }
    }

    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;
        LRUCache cache = new LRUCache(cacheSize);
        int result = 0;
        for (String city : cities) {
            String input = city.toUpperCase();
            result += cache.add(input) ? 1 : 5;
        }
        return result;
    }
}