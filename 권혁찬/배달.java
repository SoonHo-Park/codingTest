package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int N = 5;
        int[][] road = new int[][]{{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;
        int answer = solution(N, road, K);
        //int answer = solution2(N, road, K);
        System.out.println("answer = " + answer);
    }

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;
        /**
         * 플로이드 와샬 알고리즘
         * 거쳐가는 노드를 기준으로 계산하여 모든 노드에서 모든 노드로의 최소 비용을 계산함
         * */
        // 거리를 저장할 matrix
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i==j){
                    matrix[i][j] = 0;
                }
                else {
                    matrix[i][j] = 500001;
                }
            }
        }
        for (int i = 0; i < road.length; i++) {
            int fir = road[i][0];
            int sec = road[i][1];
            int dis = road[i][2];
            if(dis < matrix[fir - 1][sec - 1]){
                matrix[fir - 1][sec - 1] = dis;
                matrix[sec - 1][fir - 1] = dis;
            }
        }
        // 거처가는 노드
        for (int k = 0; k < N; k++) {
            // 출발 노드
            for (int i = 0; i < N; i++) {
                // 출발 노드를 거쳐가는 경우는 계산할 필요 없음
                if(i==k){
                    continue;
                }
                // 도착 노드
                for (int j = 0; j < N; j++) {
                    // 도착 노드를 거쳐가는 경우는 계산할 필요 없음
                    if(j==k){
                        continue;
                    }
                    // 자신 노드와의 거리는 계산할 필요 없음
                    if(i==j){
                        continue;
                    }
                    if(matrix[i][k] + matrix[k][j] < matrix[i][j]){
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        matrix[j][i] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if(matrix[0][i] <= K){
                answer++;
            }
        }
        return answer;
    }

    /**
     * 다익스트라 알고리즘
     * 특정 노드에서 모든 노드로의 최소 비용 계산함
     */
    static int[] distance;
    static boolean[] visited;
    public static int solution2(int N, int[][] road, int K){
        int answer = 0;
        // 노드간 거리 초기화
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i==j){
                    matrix[i][j] = 0;
                }
                else {
                    matrix[i][j] = 500001;
                }
            }
        }
        for (int i = 0; i < road.length; i++) {
            int fir = road[i][0];
            int sec = road[i][1];
            int dis = road[i][2];
            if (dis < matrix[fir - 1][sec - 1]) {
                matrix[fir - 1][sec - 1] = dis;
                matrix[sec - 1][fir - 1] = dis;
            }
        }
        // 방문한 노드 확인할 배열
        visited = new boolean[N];
        // 자신 노드는 방문 처리
        visited[0] = true;
        distance = new int[N];
        // 1번 노드에서 다른 노드로 가는 최소 비용 배열
        distance = matrix[0];

        for (int i = 0; i < N; i++) {
            int curr = getMinDisIdx();
            visited[curr] = true;
            for (int j = 0; j < N; j++) {
                if(!visited[j]){
                    if(distance[curr] + matrix[curr][j] < distance[j]){
                        distance[j] = distance[curr] + matrix[curr][j];
                    }
                }
            }
        }
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] <= K) {
                answer++;
            }
        }
        return answer;
    }

    public static int getMinDisIdx(){
        int min = 500001;
        int idx = 0;
        for (int i = 0; i < distance.length; i++) {
            if(distance[i] < min && !visited[i]){
                min = distance[i];
                idx = i;
            }
        }
        return idx;
    }


}
