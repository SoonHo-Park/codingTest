package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int[] numbers = new int[]{3, 30, 34, 5, 9};
        String  answer = solution(numbers);
        System.out.println("answer = " + answer);
    }

    public static String solution(int[] numbers) {
        String answer = "";
        // 정렬을 위해 Integer 타입으로 변환
        Integer[] nums = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        // 정렬
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String so1 = Integer.toString(o1);
                String so2 = Integer.toString(o2);
                Integer tr1 = Integer.parseInt(so1 + so2);
                Integer tr2 = Integer.parseInt(so2 + so1);
                return tr2 - tr1;
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<nums.length; i++){
            if(nums[0] == 0){
                return "0";
            }
            sb.append(Integer.toString(nums[i]));
        }
        answer = sb.toString();
        return answer;
    }

}
