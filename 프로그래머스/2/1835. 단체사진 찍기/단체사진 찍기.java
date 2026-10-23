class Solution {
    private final char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    
    private int perm(int v, String seq, String[] data) {
        if (seq.length() == friends.length) {
            return check(seq, data) ? 1 : 0;
        }
        int sum = 0;
        for (int i = 0; i < friends.length; i++) {
            if ((v & (1 << i)) != 0) continue;
            sum += perm(v | (1 << i), seq + friends[i], data);
        }
        return sum;
    }
    
    private boolean check(String seq, String[] data) {
        for (String d : data) {
            char a = d.charAt(0);
            char b = d.charAt(2);
            char op = d.charAt(3);
            int gap = Integer.parseInt(d.substring(4));
            int real = Math.abs(seq.indexOf(a) - seq.indexOf(b)) - 1;
            if ((op == '=' && real != gap) ||
               (op == '<' && real >= gap) ||
                (op == '>' && real <= gap))
                return false;
        }
        return true;
    }
    
    public int solution(int n, String[] data) {
        return perm(0, "", data);
    }
}