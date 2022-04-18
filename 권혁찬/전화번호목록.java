package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] phone_book = new String[]{"119", "97674223", "1195524421"};
        boolean answer = solution(phone_book);
        System.out.println("answer = " + answer);
    }

    public static boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length-1; i++) {
            if (phone_book[i+1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}
