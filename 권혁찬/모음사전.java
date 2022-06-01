package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        String word = "AAAAE";
        int answer = solution(word);
        System.out.println("answer = " + answer);
    }

    public static int solution(String word) {
        int answer = word.length();
        // 자릿수 별로 한번 바꾸는데 움직이는 횟수
        int[] unitNum = new int[]{781, 156, 31, 6, 1};
        String alpha = "AEIOU";
        for (int i = 0; i < word.length(); i++) {
            answer = answer + (unitNum[i] * alpha.indexOf(word.charAt(i)));
        }
        return answer;
    }

}
