import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        final int num, x, y;
        Node left, right;
        
        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.left = null;
            this.right = null;
        }
        
        @Override
        public int compareTo(Node o) {
            if (y != o.y) {
                return o.y - y;
            }
            return x - o.x;
        }
    }
    
    private void addNode(Node cur, Node target) {
        if (target.x < cur.x) {
            if (cur.left == null) {
                cur.left = target;
            } else {
                addNode(cur.left, target);
            }
        } else {
            if (cur.right == null) {
                cur.right = target;
            } else {
                addNode(cur.right, target);
            }
        }
    }
    
    private void preorder(Node cur, List<Integer> order) {
        order.add(cur.num);
        if (cur.left != null) {
            preorder(cur.left, order);
        }
        if (cur.right != null) {
            preorder(cur.right, order);
        }
    }
    
    private void postorder(Node cur, List<Integer> order) {
        if (cur.left != null) {
            postorder(cur.left, order);
        }
        if (cur.right != null) {
            postorder(cur.right, order);
        }
        order.add(cur.num);
    }
    
    public List<List<Integer>> solution(int[][] nodeinfo) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            int[] node = nodeinfo[i];
            nodes.add(new Node(i + 1, node[0], node[1]));
        }
        Collections.sort(nodes);
        
        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            addNode(root, nodes.get(i));
        }
        
        List<Integer> preorderResult = new ArrayList<>();
        List<Integer> postorderResult = new ArrayList<>();
        preorder(root, preorderResult);
        postorder(root, postorderResult);
        
        List<List<Integer>> result = new ArrayList<>();
        result.add(preorderResult);
        result.add(postorderResult);
        return result;
    }
}