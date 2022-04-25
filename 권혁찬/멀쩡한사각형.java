package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int w = 8;
        int h = 12;
        long answer = solution(w, h);
        System.out.println("answer = " + answer);
    }

    public static long solution(int w, int h) {
        long answer = 1;
        int gcd = getGcd(w, h);
        answer = ((long)w * h) - (((w / gcd) + (h / gcd) - 1) * gcd);
        return answer;
    }

    public static int getGcd(int w, int h) {
        while (h != 0) {
            int r = w % h;
            w = h;
            h = r;
        }
        return w;
    }
}
