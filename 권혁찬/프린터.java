package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int[] priorities = new int[]{2, 1, 3, 2};
        int location = 2;
        int answer = solution(priorities, location);
        System.out.println("answer = " + answer);
    }

    public static int solution(int[] priorities, int location) {
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        int cnt = 0;
        // queue 안에 [인덱스, 우선순위] 로 리스트 넣어서 초기화
        for(int i=0; i<priorities.length; i++){
            ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(i, priorities[i]));
            queue.add(temp);
        }
        // 인쇄 될때까지 반복
        while (true){
            Iterator iter = queue.iterator();
            List<Integer> maxChkList = new ArrayList<>();
            while (iter.hasNext()) {
                ArrayList<Integer> maxChkTemp = (ArrayList<Integer>) iter.next();
                maxChkList.add(maxChkTemp.get(1));
            }
            // 우선순위 최댓값 구하기
            int maxValue = maxChkList.stream().mapToInt(x -> x).max().orElse(-1);

            // queue 맨앞값이 출력하려는 인덱스고, 우선순위 최대값이면 while 종료
            if(queue.peek().get(0) == location && queue.peek().get(1) == maxValue){
                cnt++;
                break;
            }

            // queue 진행
            for(int i=0; i<queue.size(); i++){
                // queue 맨앞값이 출력하려는 인덱스고, 우선순위 최대값이면 return
                if(queue.peek().get(0) == location && queue.peek().get(1) == maxValue){
                    queue.poll();
                    cnt++;
                    return cnt;
                }
                // 우선순위 최대값이기만 하면 queue에서 빼고 cnt 증가
                else if(queue.peek().get(1) == maxValue){
                    queue.poll();
                    cnt++;
                    break;
                }
                // 최대값 아니면 뒤에 붙힘
                else {
                    queue.add(queue.poll());
                }
            }
        }
        int answer = cnt;
        return answer;
    }
}
