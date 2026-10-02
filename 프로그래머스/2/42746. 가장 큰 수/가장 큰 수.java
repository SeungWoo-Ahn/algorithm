import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        List<String> li = new ArrayList<>();
        for (int number : numbers) {
            li.add(Integer.toString(number));
        }
        Collections.sort(li, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        if (li.get(0).charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String num : li) {
            sb.append(num);
        }
        return sb.toString();
    }
}