package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] lottos = new int[]{44, 1, 0, 0, 31, 25};
        int[] win_nums = new int[]{31, 10, 45, 1, 6, 19};
        int[] result = solution(lottos, win_nums);
        Arrays.stream(result).forEach(i -> {
            System.out.println(i);
        });
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int zero_cnt = 0;
        int match_cnt = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                zero_cnt++;
            }
        }
        for (int i = 0; i < win_nums.length; i++) {
            for (int j = 0; j < lottos.length; j++) {
                if(win_nums[i] == lottos[j]){
                    match_cnt++;
                    continue;
                }
            }
        }
        int best_mat = match_cnt + zero_cnt;
        int worst_mat = match_cnt;
        answer[0] = getRank(best_mat);
        answer[1] = getRank(worst_mat);
        return answer;
    }

    public static int getRank(int mat_cnt) {
        int rank;
        if (mat_cnt >= 6) {
            rank = 1;
        } else if (mat_cnt == 5) {
            rank = 2;
        } else if (mat_cnt == 4) {
            rank = 3;
        } else if (mat_cnt == 3) {
            rank = 4;
        } else if (mat_cnt == 2) {
            rank = 5;
        } else {
            rank = 6;
        }
        return rank;
    }
}
