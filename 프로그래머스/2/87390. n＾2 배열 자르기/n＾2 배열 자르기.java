class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int) (right - left);
        int[] result = new int[size + 1];
        for (int i = 0; i < result.length; i++) {
            int col = (int) ((i + left) / n);
            int row = (int) ((i + left) % n);
            if (row <= col) {
                result[i] = col + 1;
            } else {
                result[i] = row + 1;
            }
        }
        return result;
    }
}