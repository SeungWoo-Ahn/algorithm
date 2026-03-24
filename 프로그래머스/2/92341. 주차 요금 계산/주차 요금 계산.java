import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

class Solution {
    private static class Calculator {
        final int basicTime;
        final int basicCost;
        final int unitTime;
        final int unitCost;
        
        public Calculator(int[] fees) {
            this.basicTime = fees[0];
            this.basicCost = fees[1];
            this.unitTime = fees[2];
            this.unitCost = fees[3];
        }
        
        public int calculate(int accTime) {
            if (accTime < basicTime) {
                return basicCost;
            }
            double time = ((double) (accTime - basicTime)) / unitTime;
            int upperTime = (int) Math.ceil(time);
            return basicCost + upperTime * unitCost;
        }
    }
    
    private static class Bill implements Comparable<Bill> {
        final String carNum;
        final int cost;
        
        public Bill(String carNum, int cost) {
            this.carNum = carNum;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Bill o) {
            return carNum.compareTo(o.carNum);
        }
    }
    
    private static final String END_TIME = "23:59";
    
    private int getMinutes(String time) {
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3, 5));
        return hh * 60 + mm;
    }
    
    public List<Integer> solution(int[] fees, String[] records) {
        Map<String, Integer> historyMap = new HashMap<>();
        Map<String, Integer> accTimeMap = new HashMap<>();
        for (String record : records) {
            String[] command = record.split(" ");
            int minutes = getMinutes(command[0]);
            String carNum = command[1];
            boolean in = command[2].equals("IN");
            if (in) {
                historyMap.put(carNum, minutes);
            } else {
                int accTime = minutes - historyMap.get(carNum);
                accTimeMap.put(carNum, accTimeMap.getOrDefault(carNum, 0) + accTime);
                historyMap.remove(carNum);
            }
        }
        int endMinutes = getMinutes(END_TIME);
        for (String carNum : historyMap.keySet()) {
            int accTime = endMinutes - historyMap.get(carNum);
            accTimeMap.put(carNum, accTimeMap.getOrDefault(carNum, 0) + accTime);
        }
        Calculator calculator = new Calculator(fees);
        List<Bill> bills = new ArrayList<>();
        for (String carNum : accTimeMap.keySet()) {
            int accTime = accTimeMap.get(carNum);
            int cost = calculator.calculate(accTime);
            bills.add(new Bill(carNum, cost));
        }
        Collections.sort(bills);
        return bills.stream()
            .map(it -> it.cost)
            .collect(Collectors.toList());
    }
}