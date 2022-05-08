package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        String expression = "50*6-3*2";
        long answer = solution(expression);
        System.out.println();
        System.out.println("answer = " + answer);
    }
    // 우선순위 순열 담을 리스트
    static List<List<String>> calcPerm = new ArrayList<>();
    public static long solution(String expression) {
        long answer = 0;
        String[] calc = new String[]{"*", "+", "-"};
        boolean[] visited = new boolean[calc.length];
        // 순열로 우선순위 배열 구하기
        perm(calc, new String[3], visited, 0, 3);

        for(int i=0; i< calcPerm.size(); i++){
            // 졍규식으로 숫자 리스트, 연산자 리스트 나누기
            List<String> numList = new ArrayList<>(Arrays.asList(expression.split("[*+-]")));
            List<String> expList = new ArrayList<>(Arrays.asList(expression.split("[0-9]+")));
            // 숫자로 짤랐을 때, 첫자리 공백 제거
            expList.remove(0);

            // 우선순위 케이스 반복
            for(int j=0; j<calcPerm.get(0).size(); j++){
                String exp = calcPerm.get(i).get(j);
                // 케이스의 우선순위 반복
                for(int k=0; k<expList.size(); k++){
                    // 우선순위와 같은 연산
                    if(calcPerm.get(i).get(j).equals(expList.get(k))){
                        // 연산자기준 index 와 그 다음 index 로 값을 구한다.
                        int pre = k;
                        int next = k+1;
                        long result = 0;
                        if(exp.equals("*")){
                            result = Long.parseLong(numList.get(pre)) * Long.parseLong(numList.get(next));
                        } else if(exp.equals("+")){
                            result = Long.parseLong(numList.get(pre)) + Long.parseLong(numList.get(next));
                        } else if(exp.equals("-")){
                            result = Long.parseLong(numList.get(pre)) - Long.parseLong(numList.get(next));
                        }
                        // 구한 값을 숫자리스트 앞에 넣어주고 next 는 제거
                        numList.set(pre, Long.toString(result));
                        numList.remove(next);
                        // 연산한 연산자 제거
                        expList.remove(k);
                        // k를 제거했으므로 땡겨진 k 자리부터 다시 연산
                        k--;
                    }
                }
            }
            // 절대값 최댓값
            long temp = Math.abs(Long.parseLong(numList.get(0)));
            if(answer < temp){
                answer = temp;
            }
        }

        return answer;
    }

    public static void perm(String[] calc, String[] out, boolean[] visited, int depth, int r){
        if(depth == r){
            calcPerm.add(new ArrayList<>(Arrays.asList(out)));
            return;
        }
        else {
            for(int i=0; i<calc.length; i++){
                if(!visited[i]){
                    visited[i] = true;
                    out[depth] = calc[i];
                    perm(calc, out, visited, depth+1, r);
                    visited[i] = false;
                }
            }
        }
    }

}
