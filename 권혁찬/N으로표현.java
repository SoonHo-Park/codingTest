package com.company;
import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] list;
    public static void main(String[] args) {
        int answer = 0;
        int N = 5;
        int number = 12;
        answer = solution(N, number);
        System.out.println("answer = " + answer);
    }

    public static int solution(int N, int number) {
        initList(N);
        if (number == N) {
            return 1;
        }
        // 숫자 개수 조합별로 계산
        for (int i = 2; i <= 8; i++) {
            for(int j = 1; j < i; j++){
                for(int info1 : list[i-j]){
                    for(int info2 : list[i-(i-j)]){
                        list[i].add(info1 * info2);
                        list[i].add(info1 + info2);
                        list[i].add(info1 - info2);
                        if(info2 != 0){
                            list[i].add(info1 / info2);
                        }
                        String temp = "";
                        for (int k = 0; k < i; k++) {
                            temp += Integer.toString(N);
                        }
                        list[i].add(Integer.parseInt(temp));
                    }
                }
            }
            if (list[i].contains(number)){
                return i;
            }
        }
        return -1;
    }

    public static void initList(int N){
        // 8보다 크면 -1 리턴
        list = new ArrayList[9];
        // 리스트배열 초기화
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        // 하나만 사용하는 경우는 N만 있음
        list[1].add(N);
    }

    public static void printList(){
        for(int i = 1; i < list.length; i++){
            for (int j = 0; j < list[i].size(); j++) {
                System.out.print(list[i].get(j) + " ");
            }
            System.out.println();
        }
    }
}

/***
 * 5
 * 5*5 5+5 5/5 5-5 55
 * 5*5*5 5*5+5 5*5/5 5*5-5
 * 5+5*5 5+5+5 5+5/5 5+5-5
 * 5/5*5 5/5+5 5/5/5 5/5-5
 * 5-5*5 5-5+5 5-5/5 5-5-5
 * 55*5  55+5  55/5  55-5
 *
 */