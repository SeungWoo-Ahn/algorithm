import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Solution {
    private static class Expression {
        final String a;
        final String oper;
        final String b;
        final String result;
        
        public Expression(String a, String oper, String b, String result) {
            this.a = a;
            this.oper = oper;
            this.b = b;
            this.result = result;
        }
        
        public boolean isUnknown() {
            return result.equals("X");
        }
        
        public int getMinRadix() {
            int maxNum = Math.max(getMaxNum(a), getMaxNum(b));
            if (!isUnknown()) {
                maxNum = Math.max(maxNum, getMaxNum(result));
            }
            return Math.min(maxNum + 1, 9);
        }
        
        private int getMaxNum(String target) {
            int maxNum = 0;
            for (int i = 0; i < target.length(); i++) {
                int num = target.charAt(i) - '0';
                maxNum = Math.max(maxNum, num);
            }
            return maxNum;
        }
        
        public String calc(int radix) {
            int ai = Integer.parseInt(a, radix);
            int bi = Integer.parseInt(b, radix);
            int result = oper.equals("+") ? ai + bi : ai - bi;
            return Integer.toString(result, radix);
        }
        
        public String reveal(String str) {
            return a + " " + oper + " " + b + " = " + str;
        }
    }
    
    private boolean[] getPossibleRadix(int minRadix) {
        boolean[] possibleRadix = new boolean[10];
        for (int radix = minRadix; radix <= 9; radix++) {
            possibleRadix[radix] = true;
        }
        return possibleRadix;
    }
    
    public List<String> solution(String[] expressions) {
        List<Expression> knowns = new ArrayList<>();
        List<Expression> unknowns = new ArrayList<>();
        int minRadix = 0;
        for(String exp : expressions) {
            String[] split = exp.split(" ");
            Expression expression = new Expression(split[0], split[1], split[2], split[4]);
            if (expression.isUnknown()) {
                unknowns.add(expression);
            } else {
                knowns.add(expression);
            }
            minRadix = Math.max(minRadix, expression.getMinRadix());
        }
        boolean[] possibleRadix = getPossibleRadix(minRadix);
        for (Expression expression : knowns) {
            for (int radix = 2; radix <= 9; radix++) {
                if (!possibleRadix[radix]) continue;
                if (!expression.result.equals(expression.calc(radix))) {
                    possibleRadix[radix] = false;
                }
            }
        }
        Set<Integer> possibleRadixSet = new HashSet<>();
        for (int radix = 2; radix <= 9; radix++) {
            if (possibleRadix[radix]) {
                possibleRadixSet.add(radix);
            }
        }
        List<String> result = new ArrayList<>();
        for (Expression expression : unknowns) {
            Set<String> calcResultSet = new HashSet<>();
            for (int radix : possibleRadixSet) {
                calcResultSet.add(expression.calc(radix));
                if (calcResultSet.size() >= 2) break;
            }
            if (calcResultSet.size() == 1) {
                result.add(expression.reveal(calcResultSet.iterator().next()));
            } else {
                result.add(expression.reveal("?"));
            }
        }
        return result;
    }
}