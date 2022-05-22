package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        String numbers = "011";
        int answer = solution(numbers);
        System.out.println("answer = " + answer);
    }
    static ArrayList<Integer> permList = new ArrayList<>();
    public static int solution(String numbers) {
        int answer = 0;
        int n = numbers.length();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(numbers.substring(i, i + 1));
        }
        int[] output = new int[n];
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++){
            perm(arr, output, visited, 0, n, i + 1);
        }
        for (Integer i : permList) {
            if(check(i)){
                answer++;
            }
        }
        return answer;
    }
    // 모든 순열뽑기
    public static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r){
        if(depth == r){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < r; i++) {
                sb = sb.append(output[i]);
            }
            Integer temp = Integer.parseInt(sb.toString());
            // 011 11 중복검사
            if(!permList.contains(temp)){
                permList.add(temp);
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    // 소수인지 체크
    public static boolean check(int number) {
        boolean rtnValue = true;
        if (number == 0 || number == 1) {
            rtnValue = false;
        }
        for(int i=2; i<number; i++){
            if(number % i == 0){
                rtnValue = false;
            }
        }
        return rtnValue;
    }

}
