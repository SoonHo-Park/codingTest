package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {4, 1, 2, 1};
        int target = 4;
        int answer = solution(numbers, target);
        System.out.println("answer = " + answer);
    }

    public static int dfs(int prev, int[] numbers, int idx, int target) {
        // +/- 재귀를 배열의 마지막 수 까지 하고 다음 호출된 경우에
        // 이전 최종 금액이 target 과 같으면 1 return
        if (idx == numbers.length) {
            if (prev == target) {
                return 1;
            } else {
                return 0;
            }
        }
        int ans = 0;
        ans += dfs(prev + numbers[idx], numbers, idx+1, target);
        ans += dfs(prev - numbers[idx], numbers, idx+1, target);
        // 재귀 return 으로 최종 값 0 또는 1 반환
        return ans;
    }

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        int curr = 0;
        // 0부터 시작해서 numbers[0] 부터 +/- 재귀
        // 모든 경우의 수의 return 값 더하기
        answer += dfs(curr, numbers, 0, target);
        return answer;
    }

    // 다른사람의 풀이 1
    public int solution2(int[] numbers, int target) {
        int answer = 0;
        answer = dfs2(numbers, 0, 0, target);
        return answer;
    }
    int dfs2(int[] numbers, int n, int sum, int target) {
        if(n == numbers.length) {
            if(sum == target) {
                return 1;
            }
            return 0;
        }
        // => return dfs2() + dfs2() + dfs2() + dfs2() + dfs2() + dfs2() + dfs2() + dfs2()
        return dfs2(numbers, n + 1, sum + numbers[n], target) + dfs2(numbers, n + 1, sum - numbers[n], target);
    }

    // 다른사람의 풀이2
    // 재귀에 깊이만을 사용하고 각 숫자 조합은 cache 배열에 저장함
    static int ans, goal, n, num[], cache[];
    public int solution3(int[] numbers, int target) {
        n = numbers.length;
        num = numbers;
        cache = new int[n];
        goal = target;
        dfs3(0);
        return ans;
    }
    static void dfs3 (int depth) {
        // 마지막 숫자까지 확인했을 때 cache 배열의 합 확인
        if (depth == n) {
            int sum = 0;
            for (int a : cache) sum += a;
            if (sum == goal) ans++;
            return;
        }
        cache[depth] = - num[depth];
        dfs3(depth + 1);
        cache[depth] = num[depth];
        dfs3(depth + 1);
    }
}
