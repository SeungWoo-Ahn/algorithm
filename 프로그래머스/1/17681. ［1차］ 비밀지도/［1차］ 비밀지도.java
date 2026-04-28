class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] result = new String[n];
        for (int i = 0; i < result.length; i++) {
            int bit = arr1[i] | arr2[i];
            StringBuilder sb = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                if ((bit & (1 << j)) != 0) {
                    sb.append('#');
                } else {
                    sb.append(' ');
                }
            }
            result[i] = sb.toString();
        }
        return result;
    }
}