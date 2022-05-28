package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int brown = 24;
        int yellow = 24;
        int[] answer = solution(brown, yellow);
        Arrays.stream(answer).forEach(System.out::println);
    }

    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        //brown + yellow 가 width * height
        int total = brown + yellow;
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        // yellow 가 1이상이면 가로, 세로 최소 3이상
        int minHeight = 3;
        int width = 0;
        for (int height = minHeight; height <= total; height++) {
            if((total % height) == 0){
                width = total / height;
                // 곱 조합 저장
                if(width >= height){
                    ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(width, height));
                    queue.add(temp);
                }
                else {
                    break;
                }
            }
        }

        // 곱 조합 체크
        while (!queue.isEmpty()){
            ArrayList<Integer> list = queue.poll();
            int chkWidth = list.get(0);
            int chkHeight = list.get(1);

            int chkBrown = chkWidth + chkWidth + (chkHeight - 2) + (chkHeight - 2);
            int chkYellow = (chkWidth - 2) * (chkHeight - 2);

            if (chkBrown == brown && chkYellow == yellow) {
                answer[0] = chkWidth;
                answer[1] = chkHeight;
                break;
            }
        }
        return answer;
    }
}
