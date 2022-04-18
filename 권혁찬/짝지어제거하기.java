package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "baabaa";
        int answer = solution(s);
        System.out.println("answer = " + answer);
    }

    public static int solution(String s)
    {
        int answer = -1;
        Stack<Character> stack = new Stack<>();
        char[] charArray = s.toCharArray();
        for (char charA : charArray) {
            if (!stack.isEmpty()) {
                char peekA = stack.peek();
                if (peekA == charA) {
                    stack.pop();
                } else {
                    stack.push(charA);
                }
            } else {
                stack.push(charA);
            }
        }
        answer = (stack.isEmpty()) ? 1 : 0;
        return answer;
    }
}
