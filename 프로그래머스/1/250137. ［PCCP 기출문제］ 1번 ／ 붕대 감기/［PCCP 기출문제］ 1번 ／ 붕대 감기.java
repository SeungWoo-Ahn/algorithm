class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int hp = health;
        for (int i = 0; i < attacks.length; i++) {
            hp -= attacks[i][1];
            if (hp <= 0) {
                return -1;
            }
            if (i == attacks.length - 1) continue;
            int time = attacks[i + 1][0] - attacks[i][0] - 1;
            int heal = bandage[1] * time + (time / bandage[0]) * bandage[2];
            if (hp + heal > health) {
                hp = health;
            } else {
                hp += heal;
            }
        }
        return hp;
    }
}