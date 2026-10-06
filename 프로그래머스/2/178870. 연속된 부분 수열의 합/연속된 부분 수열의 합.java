class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int[] acc = new int[n + 1];
        acc[1] = sequence[0];
        for (int i = 2; i <= n; i++) {
            acc[i] = acc[i - 1] + sequence[i - 1];
        }
        int[] result = new int[2];
        int st = 0;
        int en = 1;
        int len = n + 1;
        while (en <= n) {
            int sum = acc[en] - acc[st];
            if (sum == k) {
                if (en - st < len) {
                    result[0] = st;
                    result[1] = en - 1;
                    len = en - st;
                }
                st++;
                en++;
            } else if (sum < k) {
                en++;
            } else {
                st++;
            }
        }
        return result;
    }
}