package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        String s = "1 2 3 4";
        String answer = solution(s);
        System.out.println("answer = " + answer);
    }

    public static String solution(String s) {
        String answer = "";
        String[] nums = s.split(" ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (String num : nums) {
            int val = Integer.parseInt(num);
            if(val > max){
                max = val;
            }
            if(val < min){
                min = val;
            }
        }
        answer = min + " " + max;

        return answer;
    }


}
