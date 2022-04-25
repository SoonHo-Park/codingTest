package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = 5;
        int[] stages = new int[]{2, 1, 2, 6, 2, 4, 3, 3};
        int[] answer = solution(N, stages);
        Arrays.stream(answer).forEach(System.out::println);
    }
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Map<Integer,Double> map = new HashMap<>();
        for(int i=1; i<=N; i++) {
            int stage = i;
            int noClearPlayer = 0;
            int currentStagePlayer = 0;
            for(int j=0; j < stages.length; j++) {
                int player = stages[j];
                if(stage == player) {
                    noClearPlayer++;
                }
                if(stage<=player) {
                    currentStagePlayer++;
                }
            }
            double failureRate = 0;
            if(noClearPlayer!=0 && currentStagePlayer!=0) {
                failureRate = (double)noClearPlayer / (double)currentStagePlayer;
            }
            map.put(stage, failureRate);

        }
        for(int i=0; i<N; i++) {
            double max = -1;
            int maxKey = 0;
            for(Integer key : map.keySet()) {
                if(max < map.get(key)) {
                    max = map.get(key);
                    maxKey = key;
                }
            }
            answer[i] = maxKey;
            map.remove(maxKey);
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }
}
