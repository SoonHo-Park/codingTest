package com.company;
import java.util.*;
import java.io.*;

public class Main {
    static int R, C;
    static int[][] matrix;
    static boolean[] visited = new boolean[26];
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static int ans = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        matrix = new int[R][C];
        for(int i=0; i<R; i++){
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                matrix[i][j] = str.charAt(j) - 'A';
            }
        }
        printMatrix();

        dfs(0,0,0);
        System.out.println("ans = " + ans);

    }

    public static void dfs(int y, int x, int count){
        // 이미 방문한 곳이면 return
        if(visited[matrix[y][x]]){
            ans = Math.max(ans, count);
            return;
        }
        else {
            // 방문 처리
            visited[matrix[y][x]] = true;
            for(int i=0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(ny >= R || nx >= C || ny < 0 || nx < 0){
                    continue;
                }
                dfs(ny, nx, count + 1);
            }
            // 방문 처리 풀면서 넘어감
            visited[matrix[y][x]] = false;
        }

    }

    public static void printMatrix(){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
