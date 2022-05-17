package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        String s = "(())()";
        boolean answer = solution(s);
        System.out.println("answer = " + answer);
    }

    public static boolean solution(String s) {
        boolean answer = false;
        Stack<Character> stack = new Stack<>();
        if(s.substring(0,1).equals(")") || s.substring(s.length()-1).equals("(")){
            return false;
        }
        for (char a : s.toCharArray()) {
            if(stack.size() > s.length() / 2){
                return false;
            }
            if(a == '('){
                stack.push(a);
            }
            else {
                if(stack.isEmpty() && a == ')'){
                    return false;
                }
                else {
                    stack.pop();
                }
            }
        }
        if(stack.size() == 0){
            answer = true;
        }
        return answer;
    }
}
