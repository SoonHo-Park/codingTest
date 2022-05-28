package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        String s = "110010101001";
        int[] answer = solution(s);
        Arrays.stream(answer).forEach(System.out::println);
    }

    public static int[] solution(String s) {
        int[] answer = new int[2];
        int cnt = 0;
        int totalDeltCnt = 0;
        while (!s.equals("1")) {
            int sLen = s.length();
            int deltCnt = deltZero(s);
            totalDeltCnt += deltCnt;
            int elseCnt = sLen - deltCnt;
            s = Integer.toBinaryString(elseCnt);
            cnt++;
        }
        answer[0] = cnt;
        answer[1] = totalDeltCnt;
        return answer;
    }

    public static int deltZero(String s){
        char[] array = s.toCharArray();
        int result = 0;
        for(char temp : array){
            if(temp == '0'){
                result++;
            }
        }
        return result;
    }
}
