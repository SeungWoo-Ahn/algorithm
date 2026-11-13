import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;
        int[] idx = new int[n + 1];
        for (int i = 0; i < n; i++) {
            idx[cards[i]] = i;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        boolean[] get = new boolean[n + 1];
        for (int i = 0; i < n / 3; i++) {
            int card = cards[i];
            int pair = target - card;
            get[card] = true;
            if (get[pair]) {
                pq.add(0);
            }
        }
        
        int round = 1;
        for (int i = n / 3; i < n; i++) {
            int card = cards[i];
            int pair = target - card;
            get[card] = true;
            if (get[pair]) {
                if (idx[pair] < n / 3) { // 처음에 가짐: 비용1
                    pq.add(1);
                } else { // 처음에 없음: 비용2
                    pq.add(2);
                }
            }
            if (i % 2 == 1) { // 다음 라운드 판별
                if (!pq.isEmpty()) {
                    int cost = pq.poll();
                    if (coin >= cost) {
                        coin -= cost;
                        round++;
                    } else { // 코인 부족
                        break;
                    }
                } else { // 만들 수 있는 쌍이 없음
                    break;
                }
            }
        }
        return round;
    }
}