package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "[)(]";
        int answer = solution(s);
        System.out.println("answer = " + answer);
    }

    static String target;
    public static int solution(String s){
        int answer = 0;
        target = s;
        int sLen = target.length();
        for(int i=0; i<sLen; i++){
            // 올바른 문자열인지 체크
            answer += chkRight();
            // 문자열 왼쪽 회전
            target = target.substring(1) + target.substring(0, 1);
        }
        return answer;
    }

    // 올바른 문자열인지 체크
    public static int chkRight(){
        Stack<Character> stack = new Stack<>();
        for(char chara : target.toCharArray()){
            if(stack.isEmpty()){
                stack.add(chara);
                continue;
            }
            if(chara == ']' && stack.peek() == '['){
                stack.pop();
                continue;
            }
            if(chara == '}' && stack.peek() == '{'){
                stack.pop();
                continue;
            }
            if(chara == ')' && stack.peek() == '('){
                stack.pop();
                continue;
            }
            stack.add(chara);
        }
        if(stack.isEmpty()){
            return 1;
        }
        else {
            return 0;
        }
    }


}
