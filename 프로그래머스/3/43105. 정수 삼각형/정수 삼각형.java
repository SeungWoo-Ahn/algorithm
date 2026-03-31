class Solution {
    public int solution(int[][] triangle) {
        for (int x = triangle.length - 2; x >= 0; x--) {
            for (int y = 0; y < triangle[x].length; y++) {
                triangle[x][y] += Math.max(triangle[x + 1][y], triangle[x + 1][y + 1]);
            }
        }
        return triangle[0][0];
    }
}