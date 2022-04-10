package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        String answer = solution(numbers, hand);
        System.out.println("answer = " + answer);
    }

    public static String solution(int[] numbers, String hand) {
        String answer = "";
        StringBuilder sb_answer = new StringBuilder(answer);
        int[][] numIdx = new int[][]{{3,1}, {0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2}, {3,0}, {3,2}};
        int[] leftSide = new int[]{1, 4, 7};
        int[] rightSide = new int[]{3, 6, 9};
        int leftState = 10;
        int rightState = 11;
        int leftDt = 0;
        int rightDt = 0;

        for (int i = 0; i < numbers.length; i++) {
            final int k = i;
            if (Arrays.stream(leftSide).anyMatch(j -> j == numbers[k])) {
                sb_answer.append("L");
                leftState= numbers[i];
            } else if (Arrays.stream(rightSide).anyMatch(j -> j == numbers[k])) {
                sb_answer.append("R");
                rightState= numbers[i];
            } else {
                leftDt = Math.abs(numIdx[leftState][0] - numIdx[numbers[i]][0]) + Math.abs(numIdx[leftState][1] - numIdx[numbers[i]][1]);
                rightDt = Math.abs(numIdx[rightState][0] - numIdx[numbers[i]][0]) + Math.abs(numIdx[rightState][1] - numIdx[numbers[i]][1]);
                if (leftDt < rightDt) {
                    sb_answer.append("L");
                    leftState = numbers[i];
                } else if (rightDt < leftDt) {
                    sb_answer.append("R");
                    rightState = numbers[i];
                } else if (rightDt == leftDt){
                    if ("left".equals(hand)) {
                        sb_answer.append("L");
                        leftState = numbers[i];
                    } else if ("right".equals(hand)) {
                        sb_answer.append("R");
                        rightState = numbers[i];
                    }
                }
            }

        }
        return sb_answer.toString();
    }
}
