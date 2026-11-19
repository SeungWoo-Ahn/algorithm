import java.util.*;

class Solution {
    static class Exp {
        final String a;
        final String b;
        private String c;
        final String op;
        
        public Exp(String a, String b, String c, String op) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.op = op;
        }
        
        public boolean unknown() {
            return c.equals("X");
        }
        
        public int maxRadix() {
            int maxNum = Math.max(maxNum(a), maxNum(b));
            if (!unknown()) {
                maxNum = Math.max(maxNum, maxNum(c));
            }
            return maxNum + 1;
        }
        
        private int maxNum(String s) {
            char maxNum = '0';
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) > maxNum) {
                    maxNum = s.charAt(i);
                }
            }
            return maxNum - '0';
        }
        
        public String calc(int radix) {
            int left = Integer.parseInt(a, radix);
            int right = Integer.parseInt(b, radix);
            int result = op.equals("+") ? left + right : left - right;
            return Integer.toString(result, radix);
        }
        
        public boolean correct(int radix) {
            return calc(radix).equals(c);
        }
        
        public void setC(String c) {
            this.c = c;
        }
        
        @Override
        public String toString() {
            return a + " " + op + " " + b + " = " + c;
        }
    }
    
    // 수식은 2 ~ 9 진법 중 하나
    // 1. 수식의 최대 숫자 + 1
    // 2. 계산이 옳은 결과
    // 3. (1) 후보가 여러 개: ?, (2) 후보가 한 개: 결과값
    public List<String> solution(String[] expressions) {
        List<Exp> knowns = new ArrayList<>();
        List<Exp> unknowns = new ArrayList<>();
        int minRadix = 2;
        for (String expression : expressions) {
            String[] sp = expression.split(" ");
            Exp exp = new Exp(sp[0], sp[2], sp[4], sp[1]);
            if (exp.unknown()) {
                unknowns.add(exp);
            } else {
                knowns.add(exp);
            }
            minRadix = Math.max(minRadix, exp.maxRadix());
        }
        Set<Integer> radixs = new HashSet<>();
        for (int radix = minRadix; radix <= 9; radix++) {
            radixs.add(radix);
        }
        for (Exp exp : knowns) {
            Set<Integer> temp = new HashSet<>();
            for (int radix : radixs) {
                if (exp.correct(radix)) {
                    temp.add(radix);
                }
            }
            radixs = temp;
        }
        List<String> result = new ArrayList<>();
        for (Exp exp : unknowns) {
            String c = null;
            for (int radix : radixs) {
                String res = exp.calc(radix);
                if (c == null) {
                    c = res;
                } else if (!c.equals(res)) {
                    c = null;
                    break;
                }
            }
            if (c == null) {
                exp.setC("?");
            } else {
                exp.setC(c);
            }
            result.add(exp.toString());
        }
        return result;
    }
}