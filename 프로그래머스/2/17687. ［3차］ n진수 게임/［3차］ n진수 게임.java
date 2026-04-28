class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        int turn = 0;
        while (sb.length() < t) {
            String ns = Integer.toString(num, n).toUpperCase();
            for (int i = 0; i < ns.length(); i++) {
                if (turn == p - 1) {
                    sb.append(ns.charAt(i));
                    if (sb.length() == t) break;
                }
                turn = (turn + 1) % m;
            }
            num++;
        }
        return sb.toString();
    }
}