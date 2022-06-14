package com.company;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String msg = "ABABABABABABABAB";
        int[] answer = solution(msg);
        for(int i=0; i<answer.length; i++){
            System.out.println(answer[i]);
        }
    }

    public static int[] solution(String msg){

        int maxIndex = 1;
        Map<String, Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        // 초기화
        for(int i=65; i<=90; i++){
            String alpha = (char)i + "";
            map.put(alpha, maxIndex++);
        }

        for(int i=0; i<msg.length(); i++){
            int k=0;
            boolean chk = false;
            for(int j=i+1; j<msg.length()+1; j++){
                String target = msg.substring(i, j);
                //System.out.println(target);
                // map에 없으면 문자가 없으면
                if(!map.containsKey(target)){
                    map.put(target, maxIndex++);
                    String prev = msg.substring(i, j-1);
                    result.add(map.get(prev));
                    i += k;
                    break;
                }
                // 시작점 증가를 위해서
                if(map.containsKey(target) && target.length() > 1){
                    k++;
                }
                // 마지막 단어까지 확인 시 종료 체크
                if(j == msg.length()){
                    result.add(map.get(target));
                    chk = true;
                }
            }
            if(chk){
                break;
            }
        }
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}
