package com.company;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /***
     * 특정 조건에 해당하는 가능성이 있는 암호들을 모두 구하는 프로그램
     * 암호 범위(C)와 암호수(L)의 범위 : 3<=L<=C<=15
     * 1. 가능성이 있는 모든 암호를 구해야함
     * 2. 암호를 제한하는 조건이 있음
     * => 가능한 암호범위로 조합을 구하면서 제한 조건 적용
     */
    public static int l, c;
    public static String[] target;
    public static String[] out;
    public static boolean[] visited;
    public static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        target = new String[c];
        visited = new boolean[c];
        out = new String[l];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            target[i] = st.nextToken();
        }

        // 조합구하기(최소 한 개의 모음, 최소 두 개의 자음)
        // 모음이 1개~(l-2)개
        // 사전순 비밀번호
        Arrays.sort(target);
        combi(target, out, visited, 0, l, 0);
        for(String info : list){
            System.out.println(info);
        }
    }

    public static void combi(String[] source, String[] out, boolean[] visited, int start, int r, int depth){
        if(depth == l){
            // 모음 개수 체크 1~(l-2)개
            if(isOk(out)){
                Arrays.sort(out);
                list.add(String.join("", out));
            }
            return;
        }
        for(int i=start; i<source.length; i++){
            if(!visited[i]){
                visited[i] = true;
                out[depth] = source[i];
                combi(source, out, visited, i+1, r, depth+1);
                visited[i] = false;
            }
        }
    }

    public static boolean isOk(String[] out){
        int cnt = 0;
        for(String s : out){
            if("a".equals(s) || "e".equals(s) || "i".equals(s) || "o".equals(s) || "u".equals(s)){
                cnt++;
            }
        }
        if(cnt >= 1 && cnt <= (l-2)){
            return true;
        }
        return false;
    }
}


