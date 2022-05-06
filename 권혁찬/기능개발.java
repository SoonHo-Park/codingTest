package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] progresses = {5 ,5 ,5};
        int[] speeds = {21, 25, 20};
        int[] answer = solution(progresses, speeds);
        Arrays.stream(answer).forEach(System.out::println);
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int value = 0;
        int step = 0;
        int cnt = 0;
        boolean cntChk = true;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < progresses.length; i++) {
            cnt = 0;
            // 남은 정도 계산
            value = 100 - progresses[i];
            // 몇 번 더 진행되어야 하는지(step) 계산
            step = value / speeds[i];
            // 나머지가 있는 경우 step + 1
            if(value % speeds[i] > 0){
                step++;
            }
            // 진행을 시작할 때의 첫번째 기능은 무조건 배포
            cnt++;
            cntChk = true;
            // 그 다음 기능 부터 마지막 기능까지 speed * step 더하기
            for (int j = i+1; j < progresses.length; j++) {
                progresses[j] = progresses[j] + speeds[j] * step;
                // 동시 배포 가능한 경우
                if(progresses[j] >= 100 && cntChk){
                    cnt++;
                }
                // 불가능 한 경우 이후 기능들은 cnt를 더하지 않기 위해
                else if (progresses[j] < 100) {
                    cntChk = false;
                }
            }
            // 배표한 만큼 i 인덱스 더하기, 반목문 시작 시 1더하기 때문에 -1
            i=i+cnt-1;
            result.add(cnt);
        }
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }


}
