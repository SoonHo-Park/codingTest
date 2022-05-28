package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 6, 8, 14};
        int answer = solution(arr);
        System.out.println("answer = " + answer);
    }

    public static int solution(int[] arr) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int temp : arr){
            queue.add(temp);
        }
        while (queue.size() > 1){
            if(queue.size() >= 2){
                int a = queue.poll();
                int b = queue.poll();
                int lcm = getLcm(a, b);
                queue.add(lcm);
            }
        }
        answer = queue.poll();
        return answer;
    }

    // 최소공배수 => (a*b)/최대공약수
    // 최대공약수 => r = a % b, 유클리드 호제법
    public static int getLcm(int a, int b){
        int initA = a;
        int initB = b;
        int r = Integer.MAX_VALUE;
        while (true) {
            r = a % b;
            if(r == 0){
                break;
            }
            else {
                a = b;
                b = r;
            }
        }
        int gcd = b;
        return initA * (initB / gcd);
    }
}
