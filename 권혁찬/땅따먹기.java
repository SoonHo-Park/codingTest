package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        int[][] land = {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
        int answer = solution(land);
        System.out.println("answer = " + answer);
    }

    // 1 2 3 5
    // 5 6 7 8
    // 4 3 2 1
    public static int solution(int[][] land) {
        int answer = 0;

        // 5 6 7 8 부터 돔
        // 전 row 의 최대값 더함
        for(int i=1; i<land.length; i++){
            for(int j=0; j<4; j++){
                int max = 0;
                for(int k=0; k<4; k++){
                    // 연속으로 같은 라인 불가능
                    if(j==k){
                        continue;
                    }
                    if(max <= land[i-1][k]){
                        max = land[i-1][k];
                    }
                }
                land[i][j] = land[i][j] + max;
            }
        }

        // 정렬후 마지막 값
        Arrays.sort(land[land.length-1]);
        answer = land[land.length-1][3];

        return answer;
    }


}
