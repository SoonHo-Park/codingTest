package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{3,3,3,2,2,2};
        int result = solution(nums);
        System.out.println("result = " + result);
    }

    public static int solution(int[] nums) {
        int answer = 0;
        int total_cnt = nums.length;
        Integer[] nums_integer = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Set<Integer> num_set = new HashSet<Integer>(Arrays.asList(nums_integer));
        int spec_cnt = num_set.size();
        if (total_cnt / 2 < spec_cnt) {
            answer = total_cnt / 2;
        } else {
            answer = spec_cnt;
        }
        return answer;
    }
}
