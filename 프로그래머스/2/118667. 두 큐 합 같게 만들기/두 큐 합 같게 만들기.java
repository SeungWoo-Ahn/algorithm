class Solution {
    private int[] concat(int[] arr1, int[] arr2) {
        int[] arr = new int[arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            arr[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            arr[i + arr1.length] = arr2[i];
        }
        return arr;
    }
    
    private long sum(int[] arr) {
        long sum = 0L;
        for (int e : arr) {
            sum += e;
        }
        return sum;
    }
    
    private boolean possible(int[] arr, long sum) {
        if (sum % 2 != 0L) {
            return false;
        }
        long half = sum >> 1;
        for (int e : arr) {
            if (e > half) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(int[] queue1, int[] queue2) {
        int[] queue = concat(queue1, queue2);
        long sum = sum(queue);
        if (!possible(queue, sum)) {
            return -1;
        }
        long half = sum >> 1;
        long acc = sum(queue1);
        int st = 0;
        int en = queue1.length;
        int cnt = 0;
        while (st < en && en <= queue.length) {
            if (acc == half) {
                return cnt;
            }
            if (acc < half) {
                if (en < queue.length) {
                    acc += queue[en];
                    en++;
                    cnt++;
                } else {
                    return -1;
                }
            } else {
                acc -= queue[st];
                st++;
                cnt++;
            }
        }
        return -1;
    }
}