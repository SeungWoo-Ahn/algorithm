import java.util.*;

class Solution {
    private List<Map<Integer, Integer>> dices = new ArrayList<>();
    private int[] result;
    private int n, max = 0;
    
    private void setDices(int[][] dice) {
        for (int[] nums : dice) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            dices.add(map);
        }
    }
    
    private void perm(int depth, int st, int[] arr) {
        if (depth == arr.length) {
            int score = play(arr);
            if (score > max) {
                result = arr.clone();
                max = score;
            }
            return;
        }
        for (int i = st; i < n; i++) {
            arr[depth] = i;
            perm(depth + 1, i + 1, arr);
        }
    }
    
    private int play(int[] arr) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (idx < arr.length && i == arr[idx]) {
                a.add(i);
                idx++;
            } else {
                b.add(i);
            }
        }
        Map<Integer, Integer> aCount = new HashMap<>();
        Map<Integer, Integer> bCount = new HashMap<>();
        comb(0, 0, 1, a, aCount);
        comb(0, 0, 1, b, bCount);
        int aWin = 0;
        for (int aScore : aCount.keySet()) {
            for (int bScore : bCount.keySet()) {
                if (aScore > bScore) {
                    aWin += aCount.get(aScore) * bCount.get(bScore);
                }
            }
        }
        return aWin;
    }
    
    private void comb(int depth, int score, int acc, List<Integer> li, Map<Integer, Integer> count) {
        if (depth == li.size()) {
            count.put(score, count.getOrDefault(score, 0) + acc);
            return;
        }
        int idx = li.get(depth);
        Set<Integer> keys = dices.get(idx).keySet();
        for (int key : keys) {
            comb(depth + 1, score + key, acc * dices.get(idx).get(key), li, count);
        }
    }
    
    // n개의 주사위를 a와 b가 반씩 나눔
    // a의 승 확률이 가장 높은 주사위 조합?
    // 1. 주사위를 반으로 나눔
    // 2. 각각의 점수 조합을 구함
    // 3. 표를 만들고 a승 가지수를 구해서 최대값을 구함
    public int[] solution(int[][] dice) {
        setDices(dice);
        n = dice.length;
        perm(0, 0, new int[n >> 1]);
        for (int i = 0; i < result.length; i++) {
            result[i]++;
        }
        return result;
    }
}