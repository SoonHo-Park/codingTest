package com.company;
import java.util.*;
import java.util.stream.Stream;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) {
        String s = "a  s  ";
        String answer = solution(s);
        System.out.println("answer = " + answer);
    }

    public static String solution(String s) {
        String answer = "";
        StringBuilder sb = new StringBuilder(s);
        boolean check = true;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if(Pattern.matches("^[0-9*$]", c+"")){
                check = false;
                continue;
            }
            if(c == ' '){
                check = true;
                continue;
            }
            String temp = c + "";
            if(check){
                sb.setCharAt(i, (temp.toUpperCase().charAt(0)));
                check = false;
            }
            else {
                sb.setCharAt(i, (temp.toLowerCase().charAt(0)));
                check = false;
            }
        }
        answer = sb.toString();
        return answer;
    }
}
