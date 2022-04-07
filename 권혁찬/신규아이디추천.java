package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        String answer_id = solution(new_id);
        System.out.println("answer_id = " + answer_id);
    }

    public static String solution(String new_id) {
        String answer = "";
        answer = new_id.toLowerCase();
        String filter = "[^a-z0-9-_.]";
        answer = answer.replaceAll(filter, "");
        System.out.println("answer pre = " + answer);

        while (answer.contains("..")){
            answer = answer.replace("..", ".");
        }
        StringBuilder sb_answer = new StringBuilder(answer);
        if (sb_answer.length() > 0 && sb_answer.charAt(0) == '.'){
            sb_answer.delete(0, 1);
        }
        if (sb_answer.length() > 0 && sb_answer.charAt(sb_answer.length()-1) == '.') {
            sb_answer.delete(sb_answer.length() - 1, sb_answer.length());
        }
        if (sb_answer.length() == 0) {
            sb_answer = sb_answer.append("a");
        }
        if (sb_answer.length() >= 16) {
            sb_answer.delete(15, sb_answer.length());
        }
        if (sb_answer.length() > 0 && sb_answer.charAt(sb_answer.length()-1) == '.') {
            sb_answer.delete(sb_answer.length() - 1, sb_answer.length());
        }
        while (sb_answer.length() <= 2) {
            sb_answer.append(sb_answer.charAt(sb_answer.length() - 1));
        }
        return sb_answer.toString();
    }



}
