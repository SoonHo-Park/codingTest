package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 2;
        int[][] answer = solution(n);
        for(int i=0; i<answer.length; i++){
            for(int j=0; j<answer[0].length; j++){
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    static ArrayList<int[]> list = new ArrayList<>();
    public static int[][] solution(int n) {

        hanoi(n, 1, 3, 2);
        int[][] answer = new int[list.size()][2];

        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }

    public static void hanoi(int n, int start, int to, int via){
        if(n == 1){
            list.add(new int[]{start, to});
            return;
        }
        hanoi(n-1, start, via, to);
        list.add(new int[]{start, to});
        hanoi(n-1, via, to, start);
    }
}
