package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int[][] maps = new int[][]{{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,1}, {0,0,0,0,1}};
        int answer = solution(maps);
        System.out.println("answer = " + answer);
    }

    public static int solution(int[][] maps) {
        int answer = 0;
        int[][] visit = new int[maps.length][maps[0].length];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length; j++) {
                // 벽은 -1로 체크
                if(maps[i][j] == 0){
                    visit[i][j] = -1;
                }
                // 나머지칸은 최댓값
                else {
                    visit[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        answer = bfs(maps, visit);

        return answer;
    }

    public static int bfs(int[][] maps, int[][] visit){

        // 상하좌우 방향
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, -1, 0, 1};

        Queue<int[]> queue = new LinkedList<>();
        // 시작점
        queue.add(new int[]{0, 0});
        visit[0][0] = 1;
        while (!queue.isEmpty()){
            int[] pollArray = queue.poll();
            int x = pollArray[0];
            int y = pollArray[1];
            int currStep = visit[y][x];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx > maps[0].length-1 || ny < 0 || ny > maps.length-1){
                    continue;
                }
                // 벽이면 다음으로
                if(visit[ny][nx] == -1){
                    continue;
                }
                // 현재자리에서 움직인 것이 기존 자리값보다 작으면 더 적게 움직여서 도착한 것
                if(currStep + 1 < visit[ny][nx]){
                    visit[ny][nx] = currStep + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        // bfs결과 가장 최소로 움직인 값이 들어가 있다.
        int result = visit[visit.length - 1][visit[0].length-1];
        // 도착하지 못하면 최댓값 그대로 -> -1 return
        if(result == Integer.MAX_VALUE){
            result = -1;
        }
        return result;
    }

}
