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
        return max / gcd(min, max) * min;
    }
    
    private int getLcm(int[][] signals) {
        int lcm = 1;
        for (int[] signal : signals) {
            int sum = signal[0] + signal[1] + signal[2];
            lcm = lcm(lcm, sum);
        }
        return lcm;
    }
    
    public int solution(int[][] signals) {
        int lcm = getLcm(signals);
        for (int time = 1; time <= lcm; time++) {
            boolean allYellow = true;
            for (int[] signal : signals) {
                int g = signal[0];
                int y = signal[1];
                int r = signal[2];
                int target = (time % (g + y + r)) - g;
                if (1 <= target && target <= y) continue;
                allYellow = false;
                break;
            }
            if (allYellow) {
                return time;
            }
        }
        return -1;
    }
}