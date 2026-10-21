class Solution {
    private int updown(char ch) {
        return Math.min(ch - 'A', 'Z' - ch + 1);
    }
    
    public int solution(String name) {
        int n = name.length();
        int ud = 0;
        int lr = n - 1;
        for (int i = 0; i < n; i++) {
            ud += updown(name.charAt(i));
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }
            if (next - i > 1) {
                int d = Math.min(i * 2 + n - next, i + (n - next) * 2);
                lr = Math.min(lr, d);
            }
        }
        return ud + lr;
    }
}