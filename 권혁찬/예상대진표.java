package com.company;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 8;
        int a = 2;
        int b = 3;
        int answer = solution(n, a, b);
        System.out.println(answer);
    }

    public static int solution(int n, int a, int b){
        int answer = 0;
        int totRound = 0;
        int na = (a <= b) ? a : b;
        int nb = (b > a) ? b : a;
        while(true){
            totRound++;
            if(isOdd(na) && (nb==na+1)){
                break;
            }
            na = (na+1)/2;
            nb = (nb+1)/2;
        }
        answer = totRound;
        return answer;
    }

    public static boolean isOdd(int num){
        return (num % 2 != 0);
    }
}
