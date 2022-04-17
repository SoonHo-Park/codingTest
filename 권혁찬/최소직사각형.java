package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] sizes = new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int answer = solution(sizes);
        System.out.println("answer = " + answer);
    }

    public static int solution(int[][] sizes) {
        int answer = 0;
        int maxX = 0;
        int maxY = 0;
        for (int i = 0; i < sizes.length; i++) {
            Arrays.sort(sizes[i]);
        }
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] >= maxX) {
                maxX = sizes[i][0];
            }
            if (sizes[i][1] >= maxY) {
                maxY = sizes[i][1];
            }
        }

        answer = maxX * maxY;

        return answer;
    }





}
