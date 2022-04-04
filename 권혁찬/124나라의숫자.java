package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int n = 5;
        String result = solution(n);
        System.out.println("result = " + result);
    }

    public static String solution(int n) {
        String answer = "";
        String[] idx_array = new String[]{"4", "1", "2"};
        while (n > 0) {
            int value = n / 3;
            int mod = n % 3;
            answer = idx_array[mod]+answer;
            if (mod == 0) {
                value--;
            }
            n = value;
        }

        return answer;
    }
}
