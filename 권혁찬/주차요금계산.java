package com.company;
import java.util.*;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {
        int[] fees = new int[]{180, 5000, 10, 600};
        String[] records = new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] answer = solution(fees, records);
        Arrays.stream(answer).forEach(System.out::println);
    }

    public static int[] solution(int[] fees, String[] records) {
        Map<String, Integer> resultMap = new HashMap<>();
        Map<String, String> calcMap = new HashMap<>();
        for (String record : records) {
            String[] temp = record.split(" ");
            if(temp[2].equals("IN")){
                String time = temp[0];
                String carNo = temp[1];
                // 번호 : 시간
                calcMap.put(carNo, time);
                if(resultMap.get(carNo) == null){
                    resultMap.put(carNo, 0);
                }
            }
            else if(temp[2].equals("OUT")){
                String time = temp[0];
                String carNo = temp[1];
                String startTime = calcMap.get(carNo);
                String finishTime = time;
                int resultTime = calcTime(startTime, finishTime);
                resultMap.put(carNo, resultMap.get(carNo) + resultTime);
                // 계산한 차량 제거
                calcMap.remove(carNo);
            }
        }

        for (String carNo: calcMap.keySet()) {
            String endTime = "23:59";
            String startTime = calcMap.get(carNo);
            int resultTime = calcTime(startTime, endTime);
            resultMap.put(carNo, resultMap.get(carNo) + resultTime);
        }

        Map<String, Integer> sortedMap = new TreeMap<>(resultMap);
        int[] answer = new int[sortedMap.size()];

        int idx = 0;
        for (String carNo : sortedMap.keySet()) {
            int time = sortedMap.get(carNo);
            int price = 0;
            if(time <= fees[0]){
                price = fees[1];
            }
            else {
                price = (int)(fees[1] + Math.ceil(((double) time - fees[0]) / fees[2]) * fees[3]);
            }
            answer[idx] = price;
            idx++;
        }

        return answer;
    }

    public static int calcTime(String startTime, String finishTime){
        String[] startTimes = startTime.split(":");
        String[] finishTimes = finishTime.split(":");
        int sT = (Integer.parseInt(startTimes[0]) * 60) + Integer.parseInt(startTimes[1]);
        int fT = (Integer.parseInt(finishTimes[0]) * 60) + Integer.parseInt(finishTimes[1]);
        return fT - sT;
    }

}
