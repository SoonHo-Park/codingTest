package com.company;
import java.util.*;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {
        int n = 5;
        int answer = solution(n);
        System.out.println("answer = " + answer);
    }

    static Map<Integer, Integer> map = new HashMap<>();
    public static int solution(int n) {
        int answer = 0;

        for (int i = 0; i <= n; i++) {
            int result = pivo(i);
            if (!map.containsKey(i)) {
                map.put(i, result);
            }
        }
        answer = map.get(n) % 1234567;
        return answer;
    }

    public static int pivo(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 0){
            return 0;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }
        return pivo(n - 1)% 1234567 + pivo(n - 2)% 1234567;
    }
}
