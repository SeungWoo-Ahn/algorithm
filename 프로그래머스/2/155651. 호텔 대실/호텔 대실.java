class Solution {
    private int minuate(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3));
        return hh * 60 + mm;
    }
    
    public int solution(String[][] book_time) {
        int[] acc = new int[minuate("24:00") + 10];
        for (String[] time : book_time) {
            int st = minuate(time[0]);
            int en = minuate(time[1]) + 10;
            acc[st]++;
            acc[en]--;
        }
        int result = acc[0];
        for (int i = 1; i < acc.length; i++) {
            acc[i] += acc[i - 1];
            if (acc[i] > result) {
                result = acc[i];
            }
        }
        return result;
    }
}