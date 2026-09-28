public class Solution {
    public int solution(int n) {
        String s = Integer.toString(n, 2);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                result++;
            } 
        }
        return result;
    }
}