class Solution {
    private int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
    
    private int lcm(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        int gcd = gcd(min, max);
        return a / gcd * b;
    }
    
    public int solution(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        int lcm = lcm(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            lcm = lcm(arr[i], lcm);
        }
        return lcm;
    }
}