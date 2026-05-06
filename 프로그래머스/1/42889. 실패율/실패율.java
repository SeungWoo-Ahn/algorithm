import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    private static class Stage implements Comparable<Stage> {
        final int no;
        final double failRate;
        
        public Stage(int no, double failRate) {
            this.no = no;
            this.failRate = failRate;
        }
        
        @Override
        public int compareTo(Stage o) {
            if (failRate != o.failRate) {
                if (failRate < o.failRate) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return no - o.no;
        }
    }
    
    private int[] getCnts(int n, int[] stages) {
        int[] cnts = new int[n + 2];
        for (int stage : stages) {
            cnts[stage]++;
        }
        return cnts;
    }
    
    private int[] getSuffixSum(int[] arr) {
        int len = arr.length;
        int[] suffixSum = new int[len];
        suffixSum[len - 1] = arr[len - 1];
        for (int i = len - 2; i >= 1; i--) {
            suffixSum[i] = arr[i] + suffixSum[i + 1];
        }
        return suffixSum;
    }
    
    public int[] solution(int n, int[] stages) {
        int[] cnts = getCnts(n, stages);
        int[] suffixSum = getSuffixSum(cnts);
        List<Stage> stageList = new ArrayList<>();
        for (int no = 1; no <= n; no++) {
            double failRate = suffixSum[no] == 0 ? 0.0 : (double) cnts[no] / suffixSum[no];
            stageList.add(new Stage(no, failRate));
        }
        Collections.sort(stageList);
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = stageList.get(i).no;
        }
        return result;
    }
}