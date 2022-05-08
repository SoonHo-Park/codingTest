package com.company;
import java.util.*;
import java.util.regex.*;
public class Main {
    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"};
        int answer = solution(m, n, board);
        System.out.println(answer);
    }

    public static int solution(int m, int n, String[] board) {

        int answer = 0;
        // 한 회차에 부서질 블록 개수
        int popCnt = 0;
        String[][] matrix = new String[m][];
        // 부서질 블록 표시할 배열
        boolean[][] visited = new boolean[m][];
        for(int i=0; i<m; i++){
            matrix[i] = board[i].split("");
        }
        for(int i=0; i<m; i++){
            visited[i] = new boolean[n];
        }

        do {
            popCnt = 0;
            for(int i=0; i<matrix.length; i++){
                for(int j=0; j<matrix[0].length; j++){
                    // 부서질 블록이 아니고, 부서진 블록이 아니면 체크
                    if(visited[i][j] == false && !matrix[i][j].equals("X")){
                        chkPop(i, j, matrix[i][j], matrix, visited);
                    }
                }
            }
            // visited 확인해서 부서지는 matrix X로 변경, popCnt 증가
            for(int i=0; i<visited.length; i++){
                for(int j=0; j<visited[0].length; j++){
                    if(visited[i][j]){
                        popCnt++;
                        matrix[i][j] = "X";
                        visited[i][j] = false;
                    }
                }
            }
            answer += popCnt;

            // 부서진 자리 내리기, index 반대로 해서 땡기기
            for(int i=0; i<n; i++){
                // 부서진 위치 기억할 queue
                Queue<Integer> emptyIdx = new LinkedList<>();
                for(int j=m-1; j>-1; j--){
                    if(matrix[j][i].equals("X")){
                        emptyIdx.add(j);
                    }
                    else {
                        if(!emptyIdx.isEmpty()){
                            emptyIdx.add(j);
                            matrix[emptyIdx.poll()][i] = matrix[j][i];
                            matrix[j][i] = "X";
                        }
                    }
                }
            }
        }while (popCnt != 0);

        return answer;
    }

    // 좌상, 우상, 우하, 좌상의 블록들 부서지는지 체크
    public static void chkPop(int i, int j, String curr, String[][] matrix, boolean[][] visited){
        // 좌상 방향
        int[] dx1 = {-1,-1,0};
        int[] dy1 = {0,-1,-1};
        // 우상 방향
        int[] dx2 = {0,1,1};
        int[] dy2 = {-1,-1,0};
        // 우하 방향
        int[] dx3 = {1,1,0};
        int[] dy3 = {0,1,1};
        // 좌하 방향
        int[] dx4 = {0,-1,-1};
        int[] dy4 = {1,1,0};
        // 현재블록과 같은 블록 개수
        int tempCnt = 0;

        // 좌상 확인
        for(int k=0; k<3; k++){
            int newX = j+dx1[k];
            int newY = i+dy1[k];
            if(newX<0 || newX>matrix[0].length-1 || newY<0 || newY>matrix.length-1){
                continue;
            }
            if(matrix[newY][newX].equals(curr)){
                tempCnt++;
            }
        }
        // 현재블록과 같은 블록이 3개면 부서짐, visited 배열 true
        if(tempCnt == 3){
            visited[i][j] = true;
            for(int k=0; k<3; k++){
                int newX = j+dx1[k];
                int newY = i+dy1[k];
                visited[newY][newX] = true;
            }
        }
        tempCnt = 0;
        // 우상 확인
        for(int k=0; k<3; k++){
            int newX = j+dx2[k];
            int newY = i+dy2[k];
            if(newX<0 || newX>matrix[0].length-1 || newY<0 || newY>matrix.length-1){
                continue;
            }
            if(matrix[newY][newX].equals(curr)){
                tempCnt++;
            }
        }
        if(tempCnt == 3){
            visited[i][j] = true;
            for(int k=0; k<3; k++){
                int newX = j+dx2[k];
                int newY = i+dy2[k];
                visited[newY][newX] = true;
            }
        }
        tempCnt = 0;
        // 우하 확인
        for(int k=0; k<3; k++){
            int newX = j+dx3[k];
            int newY = i+dy3[k];
            if(newX<0 || newX>matrix[0].length-1 || newY<0 || newY>matrix.length-1){
                continue;
            }
            if(matrix[newY][newX].equals(curr)){
                tempCnt++;
            }
        }
        if(tempCnt == 3){
            visited[i][j] = true;
            for(int k=0; k<3; k++){
                int newX = j+dx3[k];
                int newY = i+dy3[k];
                visited[newY][newX] = true;
            }
        }
        tempCnt = 0;
        // 좌하 확인
        for(int k=0; k<3; k++){
            int newX = j+dx4[k];
            int newY = i+dy4[k];
            if(newX<0 || newX>matrix[0].length-1 || newY<0 || newY>matrix.length-1){
                continue;
            }
            if(matrix[newY][newX].equals(curr)){
                tempCnt++;
            }
        }
        if(tempCnt == 3){
            visited[i][j] = true;
            for(int k=0; k<3; k++){
                int newX = j+dx4[k];
                int newY = i+dy4[k];
                visited[newY][newX] = true;
            }
        }
    }

}
