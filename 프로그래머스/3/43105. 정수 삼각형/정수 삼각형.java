class Solution {
    // 7
    // 3 8
    // 8 1 0
    public int solution(int[][] triangle) {
        int n = triangle.length;
        for (int i = 1; i < n; i++) {
            triangle[i][0] += triangle[i - 1][0];
            for (int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
            triangle[i][i] += triangle[i - 1][i - 1];
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, triangle[n - 1][i]);
        }
        return result;
    }
}