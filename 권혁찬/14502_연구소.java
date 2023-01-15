package com.company;
import java.io.*;
import java.util.*;

public class Main{
    /***
     * 바이러스가 퍼지는 방에 벽 3개를 쌓아서 최대한 많은 방에 바이러스를 막는 문제
     * 접근 방법 :
     * 1. 조건으로 벽은 반드시 3개를 세워야 하며 벽을 먼저 세우고 바이러스가 진행되기 때문에 벽을 세우는 모든 경우 확인이 필요하다고 생각
     * -> 조합을 편하게 구하기 위해 빈 방의 위치를 ArrayList<int[]>에 저장하고, ArrayList의 인덱스로 조합을 구했음
     * 2. 조합별로 바이러스를 bfs로 진행
     * 3. 바이러스가 침투되지 않은 방 개수 세기
     */
    public static int n, m;
    public static int[][] matrix;
    public static int result = 0;
    public static ArrayList<int[]> blankList = new ArrayList<>();
    public static int[] targetIdxs;
    public static boolean[] visits;
    public static ArrayList<int[]> combiList = new ArrayList<>();
    public static int[] outs = new int[3];
    public static int[][] tMatrix;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                // 벽 세울 위치 조합 구하기 위해 0 위치 저장
                if(matrix[i][j] == 0){
                    blankList.add(new int[]{i, j});
                }
            }
        }
        // 인덱스 조합으로 벽 세울 위치 구하기
        targetIdxs = new int[blankList.size()];
        visits = new boolean[blankList.size()];
        for(int i=0; i<blankList.size(); i++){
            targetIdxs[i] = i;
        }
        combination(targetIdxs, outs, visits, 0, 3, 0);
        // 벽 세우기
        setWall();
        System.out.println(result);
    }

    // 벽 세울 수 있는 인덱스 조합 구하기
    public static void combination(int[] sources, int[] outs, boolean[] visits, int start, int r, int depth){
        if(depth == r){
            combiList.add(outs.clone());
            return;
        }
        for(int i=start; i<sources.length; i++){
            if(!visits[i]){
                visits[i] = true;
                outs[depth] = sources[i];
                combination(sources, outs, visits, i+1, 3, depth+1);
                visits[i] = false;
            }
        }
    }

    // 벽 세우기
    public static void setWall(){
        for(int[] idxs : combiList){
            // 깊은 복사
            tMatrix = new int[matrix.length][matrix[0].length];
            for(int i=0; i< matrix.length; i++){
                tMatrix[i] = matrix[i].clone();
            }
            // 벽 세우기
            for(int idx : idxs){
                tMatrix[blankList.get(idx)[0]][blankList.get(idx)[1]] = 1;
            }
            // bfs로 바이러스 뿌리기
            setVirus();
            // 남은 공간 개수 세기
            int cnt = getBlankCnt();
            result = Math.max(result, cnt);
        }
    }
    // bfs로 바이러스 뿌리기
    public static void setVirus() {
        LinkedList<int[]> q = new LinkedList<>();
        int[] dy = {0,1,0,-1};
        int[] dx = {1,0,-1,0};
        for (int i = 0; i < tMatrix.length; i++) {
            for (int j = 0; j < tMatrix[0].length; j++) {
                if (tMatrix[i][j] == 2) {
                    q.add(new int[]{i, j});
                }
            }
        }
        while (!q.isEmpty()) {
            int[] ts = q.poll();
            int ty = ts[0];
            int tx = ts[1];
            for(int i=0; i<4; i++){
                int ny = ty + dy[i];
                int nx = tx + dx[i];
                if(ny >= n || nx >= m || ny < 0 || nx < 0){
                    continue;
                }
                if(tMatrix[ny][nx] == 0){
                    tMatrix[ny][nx] = 2;
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }

    public static int getBlankCnt(){
        int sum = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(tMatrix[i][j] == 0){
                    sum++;
                }
            }
        }
        return sum;
    }
}


