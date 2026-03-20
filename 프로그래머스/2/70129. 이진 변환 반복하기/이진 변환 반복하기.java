class Solution {
    public int[] solution(String s) {
        int[] result = new int[2];
        while (!s.equals("1")) {
            int oneCnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    oneCnt++;
                }
            }            
            result[0]++;
            result[1] += s.length() - oneCnt;
            s = Integer.toString(oneCnt, 2);
        }
        return result;
    }
}