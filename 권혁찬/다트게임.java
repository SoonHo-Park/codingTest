package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String dartResult = "1D2S#10S";
        int answer = solution(dartResult);
        System.out.println("answer = " + answer);
        System.out.println(('2' - '0'));
    }

    public static int solution(String dartResult) {
        int answer = 0;
        char[] darts = dartResult.toCharArray();
        int[] score = new int[3];
        String dartsTemp = "";
        int scoreIdx = 0;
        int powValue = 0;
        for (int i = 0; i < darts.length; i++) {
            if (darts[i] != '*' && darts[i] != '#') {
                // 숫자인 경우 temp에 더한다 (10과 같이 2글자 숫자 고려)
                if (darts[i] != 'S' && darts[i] != 'D' && darts[i] != 'T') {
                    dartsTemp += darts[i];
                } else {
                    if (darts[i] == 'S') {
                        powValue = 1;
                    } else if (darts[i] == 'D') {
                        powValue = 2;
                    } else if (darts[i] == 'T') {
                        powValue = 3;
                    }
                    // S, D, T 에 따라 제곱 처리
                    score[scoreIdx] = (int)Math.pow(Integer.parseInt(dartsTemp), powValue);
                    scoreIdx++;
                    // score 저장 후 temp 초기화
                    dartsTemp = "";
                }
            } else {
                // #, * 에 따라 score 점수 처리
                if (darts[i] == '#') {
                    score[scoreIdx-1] *= -1;
                } else if(darts[i] == '*'){
                    score[scoreIdx-1] *= 2;
                    if (scoreIdx-2 >= 0) {
                        score[scoreIdx-2] *= 2;
                    }
                }
                // temp 초기화
                dartsTemp = "";
            }
        }
        // score 합 계산
        answer = Arrays.stream(score).sum();
        return answer;
    }

    /*다른사람의 풀이*/
    public static int solution2(String dartResult) {
        // stack 자료 구조
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (int i = 0; i < dartResult.length(); ++i) {
            char c = dartResult.charAt(i);
            // isDigit : char가 숫자 값인지 판단 (true/false)
            if (Character.isDigit(c)) {
                // char형 숫자에 '0'을 빼서 int형으로 변환
                sum = (c - '0');
                // 10 체크
                if (sum == 1 && i < dartResult.length() - 1 && dartResult.charAt(i + 1) == '0') {
                    sum = 10;
                    i++;
                }
                // stack에 저장
                stack.push(sum);
            } else {
                int prev = stack.pop();
                if (c == 'D') {
                    prev *= prev;
                } else if (c == 'T') {
                    prev = prev * prev * prev;
                } else if (c == '*') {
                    if (!stack.isEmpty()) {
                        int val = stack.pop() * 2;
                        stack.push(val);
                    }
                    prev *= 2;
                } else if (c == '#') {
                    prev *= (-1);
                }
                // System.out.println(prev);
                stack.push(prev);
            }
        }
        int totalScore = 0;
        while (!stack.isEmpty()) {
            totalScore += stack.pop();
        }
        return totalScore;
    }

}
