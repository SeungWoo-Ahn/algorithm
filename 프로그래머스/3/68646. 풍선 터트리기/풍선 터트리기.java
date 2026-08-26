class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int[] leftMin = new int[n + 2];
        int[] rightMin = new int[n + 2];
        leftMin[0] = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], a[i - 1]);
        }
        rightMin[n + 1] = Integer.MAX_VALUE;
        for (int i = n; i >= 1; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i - 1]);
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            int target = a[i - 1];
            int max = Math.max(target, Math.max(leftMin[i - 1], rightMin[i + 1]));
            if (target != max) {
                result++;
            }
        }
        return result;
    }
}