package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] board = new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = new int[]{1,5,3,5,1,2,1,4};
        int answer = solution(board, moves);
        System.out.println("answer = " + answer);
    }

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            for (int i = 0; i < board.length; i++) {
                int num = board[i][move - 1];
                if (num != 0) {
                    board[i][move - 1] = 0;
                    if (stack.size() > 0) {
                        Integer peekNum = stack.peek();
                        if (peekNum == num) {
                            stack.pop();
                            answer += 2;
                        }
                        else {
                            stack.push(num);
                        }
                    } else {
                        stack.push(num);
                    }
                    break;
                }
            }
        }
        return answer;
    }
}
