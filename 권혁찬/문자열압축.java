package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "aabbaccc";
        int answer = solution(s);
        System.out.println("answer = " + answer);
    }

    public static int solution(String s) {
        int answer = s.length();
        String prev = "";
        // 앞에서 부터 압축하므로 압축범위가 반을 넘어갈 수 없음
        for (int i = 1; i < s.length()/2+1; i++) {
            // 압축여부 판단을 위해 이전 값 저장
            prev = s.substring(0, i);
            String answerSt = "";
            int cnt = 1;
            // 1, 2, 3 범위로 압축
            for (int j = i; j < s.length(); j = j + i) {
                // 전체길이를 넘어가는 경우 이전까지의 값만 저장하고
                // prev에 나머지 문자열 저장
                if (j + i > s.length()) {
                    if (cnt > 1) {
                        answerSt += cnt + prev;
                    } else {
                        answerSt += prev;
                    }
                    prev = s.substring(j);
                    cnt = 1;
                    continue;
                } else {
                    // 대상 문자열 짜른 후 이전 문자열과 비교
                    String curr = s.substring(j, j + i);
                    if (prev.equals(curr)) {
                        cnt++;
                    } else {
                        if (cnt > 1) {
                            answerSt += cnt + prev;
                        } else {
                            answerSt += prev;
                        }
                        prev = curr;
                        cnt = 1;
                    }
                }
            }
            // 마지막으로 압축된 경우에 따라 분기
            // 마지막 압축된 경우가 아니면 저장했던 나머지 문자열
            if (cnt > 1) {
                answerSt += cnt + prev;
            } else {
                answerSt += prev;
            }
            answer = Math.min(answer, answerSt.length());
        }

        return answer;
    }
}
