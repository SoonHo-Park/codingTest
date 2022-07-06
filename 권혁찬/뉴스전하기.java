package com.company;
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Info>[] list;
    static int result = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        // 리스트 배열, 인덱스를 이용하기 위함
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
            int master = Integer.parseInt(st.nextToken());
            // 최상위 노드
            if (master == -1) {
                continue;
            }
            // 초기화
            // 부모노드 인덱스에 자식노드와 탐색시간을 가지는 Info
            list[master].add(new Info(i, 0));
        }
        // 최상위 노드부터 dfs 탐색 시작
        result = calcTime(0);
        System.out.println(result);

    }

    public static class Info implements Comparable<Info> {
        int node;
        int time;
        Info(int node, int time){
            this.node = node;
            this.time = time;
        }
        @Override
        public int compareTo(Info o) {
            // 시간 역순 정렬
            return Integer.compare(o.time, this.time);
        }
    }

    public static int calcTime(int cur){
        int max = 0;
        int p = 0;
        // 자신을 부모노드로 가지는 노드들의 총 탐색시간 계산
        for (Info info : list[cur]) {
            info.time = calcTime(info.node);
        }
        // 탐색시간 긴 순으로 정렬
        Collections.sort(list[cur]);
        for (Info next : list[cur]) {
            // 정렬한 노드들의 시간에 다음탐색으로 넘어가기 위해 1씩 증가되는 시간 합으로 max 구함
            max = Math.max(max, next.time + (++p));
        }
        return max;
    }

    public static void printList(){
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                System.out.println(list[i].get(j).node + ":" + list[i].get(j).time);
            }
            System.out.println();
        }

    }
}
