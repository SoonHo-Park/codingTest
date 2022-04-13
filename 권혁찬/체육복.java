package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = new int[]{2, 4};
        int[] reserve = new int[]{1, 3, 5};
        int answer = solution(n, lost, reserve);
        System.out.println("answer = " + answer);
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        Set<Integer> lostSet = Arrays.stream(lost).boxed().collect(Collectors.toSet());
        Set<Integer> lostTempSet = new HashSet<>();
        lostTempSet.addAll(lostSet);
        Set<Integer> reserveSet = Arrays.stream(reserve).boxed().collect(Collectors.toSet());

        lostSet.removeAll(reserveSet);
        reserveSet.removeAll(lostTempSet);

        Iterator<Integer> it = reserveSet.iterator();
        while (it.hasNext()) {
            int reserveValue = it.next();
            if (lostSet.contains(reserveValue-1)) {
                lostSet.remove(reserveValue-1);
            } else if (lostSet.contains(reserveValue+1)) {
                lostSet.remove(reserveValue+1);
            }
        }
        answer = n - lostSet.size();
        return answer;
    }
}
