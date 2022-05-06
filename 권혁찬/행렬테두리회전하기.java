package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4}, {3,3,6,6}, {5,1,6,3}};
        int[] answer = solution(rows, columns, queries);
        Arrays.stream(answer).forEach(System.out::println);
    }

    public static int[] solution(int rows, int columns, int[][] queries) {

        int[] answer = new int[queries.length];
        int[][] matrix = new int[rows][columns];
        // 초기화
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = 1 + (i * columns) + j;
            }
        }


        for (int i = 0; i < queries.length; i++) {
            List<Integer> list = new ArrayList<>();
            int rowStart = queries[i][0]-1;
            int colStart = queries[i][1]-1;
            int rowEnd = queries[i][2]-1;
            int colEnd = queries[i][3]-1;
            // 1 1 4 3
            // r:1, c:1 ~ 3
            int tempCurr = 0;
            int tempPrev = 0;
            // 4가지 방향 회전하면서 옮기기
            for (int c1 = colStart; c1 < colEnd + 1; c1++) {
                tempCurr = matrix[rowStart][c1]; // 8
                matrix[rowStart][c1] = tempPrev; // 0
                tempPrev = tempCurr; // 8
                list.add(tempCurr);
            }
            // r:2 ~ 4, c:3
            for (int r1 = rowStart + 1; r1 < rowEnd + 1; r1++) {
                tempCurr = matrix[r1][colEnd];
                matrix[r1][colEnd] = tempPrev;
                tempPrev = tempCurr;
                list.add(tempCurr);
            }
            // r:4, c:3 ~ 1
            for (int c1 = colEnd -1; c1 > colStart - 1; c1--) {
                tempCurr = matrix[rowEnd][c1];
                matrix[rowEnd][c1] = tempPrev;
                tempPrev = tempCurr; // 8
                list.add(tempCurr);
            }
            // r:4 ~ 1, c:1
            for (int r1 = rowEnd - 1; r1 > rowStart - 1; r1--) {
                tempCurr = matrix[r1][colStart];
                matrix[r1][colStart] = tempPrev;
                tempPrev = tempCurr;
                if(tempCurr != 0){
                    list.add(tempCurr);
                }
            }
            // 정렬 후 최솟값 넣기
            Collections.sort(list);
            answer[i] = list.get(0);
        }
        return answer;
    }


}
