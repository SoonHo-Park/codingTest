package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        int[] answer = solution(s);
        Arrays.stream(answer).forEach(System.out::println);
    }

    public static int[] solution(String s) {
        // 배열로 변환
        String s1 = s.substring(2, s.length() - 2);
        String s2 = s1.replace("},{", "/");
        String[] s2Split = s2.split("/");
        String[][] result = new String[s2Split.length][];

        // 2차원 배열로 변환
        for (int i = 0; i < s2Split.length; i++) {
            String[] temp = s2Split[i].split(",");
            result[i] = temp;
        }

        // 길이가 짧은 게 먼저 나온 것이니 길이순으로 정렬
        Arrays.sort(result, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1.length - o2.length;
            }
        });

        // 가장 길이가 긴 배열의 길이가 answer 의 size
        int[] answer = new int[result[result.length - 1].length];

        // 첫번째 값 넣고 set 비교로 다음 숫자 구함
        answer[0] = Integer.parseInt(result[0][0]);
        for (int i = 0; i < result.length-1; i++) {
            HashSet<String> currSet = new HashSet<>(Arrays.asList(result[i]));
            HashSet<String> nextSet = new HashSet<>(Arrays.asList(result[i+1]));
            nextSet.removeAll(currSet);
            Iterator<String> it = nextSet.iterator();
            answer[i + 1] = Integer.parseInt(it.next());
        }
        return answer;
    }
}
