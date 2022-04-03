package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        long result = solution(a, b);
        System.out.println("result = " + result);
    }

    public static long solution(int a, int b) {
        int max = (a <= b) ? b : a;
        int min = (a <= b) ? a : b;
        long answer = 0;
        double mid_num = (double) (max - min + 1) / 2;
        long sum_num = max + min;
        answer = (long) (mid_num * sum_num);
        return answer;
    }
}
