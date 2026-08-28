import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int wonhoA = scores[0][0];
        int wonhoB = scores[0][1];
        int wonhoSum = wonhoA + wonhoB;
        Arrays.sort(scores, (o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
        int maxb = 0;
        int rank = 1;
        for (int[] score : scores) {
            if (maxb > score[1]) {
                if (score[0] == wonhoA && score[1] == wonhoB) {
                    return -1;
                }
                continue;
            }
            maxb = Math.max(maxb, score[1]);
            if (score[0] + score[1] > wonhoSum) {
                rank++;
            }
        }
        return rank;
    }
}