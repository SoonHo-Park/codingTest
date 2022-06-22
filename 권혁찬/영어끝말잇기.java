package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 2;
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        int[] answer = solution(n, words);
        for(int i=0; i<answer.length; i++){
            System.out.println(answer[i]);
        }
    }

    static ArrayList<String> list = new ArrayList<>();
    public static int[] solution(int n, String[] words) {
        int[] answer = {};
        int[] people = new int[n];

        for(int i=0; i<words.length; i++){
            int idx = i%n;
            // 사람당 말한 단어 수 올리기
            people[idx]++;
            // 말했던 단어 체크
            if(list.contains(words[i])){
                return new int[]{idx+1, people[idx]};
            }
            // 끝, 앞 체크
            if(i >= 1 && list.get(i-1).charAt(list.get(i-1).length()-1) != words[i].charAt(0)){
                return new int[]{idx+1, people[idx]};
            }
            list.add(words[i]);
        }
        return new int[]{0,0};
    }

}
