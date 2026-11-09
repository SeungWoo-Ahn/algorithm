class Solution {
    private long f(long number) {
        if (number % 2 == 0L) {
            return number + 1;
        }
        char[] arr = Long.toString(number, 2).toCharArray();
        int zeroIdx = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == '0') {
                arr[i] = '1';
                zeroIdx = i;
                break;
            }
        }
        for (int i = zeroIdx + 1; i < arr.length; i++) {
            if (arr[i] == '1') {
                arr[i] = '0';
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (zeroIdx < 0) {
            sb.append('1');
        }
        for (char ch : arr) {
            sb.append(ch);
        }
        return Long.parseLong(sb.toString(), 2);
    }
    
    public long[] solution(long[] numbers) {
        long[] result = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = f(numbers[i]);
        }
        return result;
    }
}