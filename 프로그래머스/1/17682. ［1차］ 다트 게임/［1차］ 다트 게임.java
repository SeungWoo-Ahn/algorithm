import java.util.List;
import java.util.ArrayList;

class Solution {    
    private static class Score {
        final int value;
        final char bonus;
        final char option;
        
        public Score(int value, char bonus, char option) {
            this.value = value;
            this.bonus = bonus;
            this.option = option;
        }
        
        public int getResult() {
            int result = value;
            switch (bonus) {
                case 'D':
                    result *= value;
                    break;
                case 'T':
                    result *= value * value;
                    break;
            }
            switch (option) {
                case '*':
                    result *= 2;
                    break;
                case '#':
                    result *= -1;
                    break;
            }
            return result;
        }
    }
    
    private List<Score> getScores(String dartResult) {
        List<Score> scores = new ArrayList<>();
        int len = dartResult.length();
        int st = 0;
        while (st < dartResult.length()) {
            int en = st;
            while (en < len && isNum(dartResult.charAt(en))) {
                en++;
            }
            int value = Integer.parseInt(dartResult.substring(st, en));
            char bonus = dartResult.charAt(en);
            char option = ' ';
            if (en + 1 < len && (dartResult.charAt(en + 1) == '*' || dartResult.charAt(en + 1) == '#')) {
                option = dartResult.charAt(++en);
            }
            Score score = new Score(value, bonus, option);
            scores.add(score);
            st = ++en;
        }
        return scores;
    }
    
    private boolean isNum(char ch) {
        int code = ch - '0';
        return 0 <= code && code <= 9;
    }
    
    public int solution(String dartResult) {
        List<Score> scores = getScores(dartResult);
        int result = 0;
        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            result += score.getResult();
            if (i > 0 && score.option == '*') {
                result += scores.get(i - 1).getResult();
            }
        }
        return result;
    }
}