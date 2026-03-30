class Solution {
    private final int[][] dirs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    
    private int[] dfs(int size, int sx, int sy, int[][] arr) {
        if (size == 1 || isCompressable(size, sx, sy, arr)) {
            if (arr[sx][sy] == 0) {
                return new int[]{1, 0};
            } else {
                return new int[]{0, 1};
            }
        }
        int[] result = new int[2];
        int nxtSize = size >> 1;
        for (int[] dir : dirs) {
            int nsx = sx + dir[0] * nxtSize;
            int nsy = sy + dir[1] * nxtSize;
            int[] nxtResult = dfs(nxtSize, nsx, nsy, arr);
            result[0] += nxtResult[0];
            result[1] += nxtResult[1];
        }
        return result;
    }
    
    private boolean isCompressable(int size, int sx, int sy, int[][] arr) {
        int target = arr[sx][sy];
        for (int x = sx; x < sx + size; x++) {
            for (int y = sy; y < sy + size; y++) {
                if (arr[x][y] != target) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int[] solution(int[][] arr) {
        int n = arr.length;
        return dfs(n, 0, 0, arr);
    }
}