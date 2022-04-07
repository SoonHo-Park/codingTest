package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "1zerotwozero3";
        int answer = solution(s);
        System.out.println("answer = " + answer);
    }

    public static int solution(String s) {
        int answer = 0;
        String[] alphaIdx = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < alphaIdx.length; i++) {
            if (s.contains(alphaIdx[i])) {
                s = s.replace(alphaIdx[i], Integer.toString(i));
            }
        }

        answer = Integer.parseInt(s);
        return answer;
    }



}
