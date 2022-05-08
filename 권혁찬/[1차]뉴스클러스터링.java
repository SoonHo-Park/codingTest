package com.company;
import java.util.*;
import java.util.regex.*;
public class Main {
    public static void main(String[] args) {
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";
        int answer = solution(str1, str2);
        System.out.println(answer);
    }

    public static int solution(String str1, String str2) {

        List<String> strList1 = new ArrayList<>();
        List<String> strList2 = new ArrayList<>();

        // str1, str2 정규식 검사해서 대문자로 리스트 생성
        for(int i=0; i<str1.length()-1; i++){
            String temp = str1.substring(i, i+2).toUpperCase();
            if(Pattern.matches("^[A-Z]*$", temp)){
                strList1.add(temp);
            }
        }
        for(int i=0; i<str2.length()-1; i++){
            String temp = str2.substring(i, i+2).toUpperCase();
            if(Pattern.matches("^[A-Z]*$", temp)){
                strList2.add(temp);
            }
        }

        // 총 건수 strList1 사이즈로 초기화
        int totCnt = strList1.size();
        // 교집합 건수
        int interCnt = 0;

        // strList1 돌면서 strList2에 있는지 검사, 체크한건 삭제
        for(int i=0; i<strList1.size(); i++){
            if(strList2.contains(strList1.get(i))){
                interCnt++;
                strList2.remove(strList1.get(i));
            }
        }
        // strList2 에 남은 수 만큼 총 건수 더함
        totCnt += strList2.size();

        // 계산
        int answer = 0;
        if(interCnt == 0 && totCnt == 0){
            answer = 65536;
        }
        else {
            answer = (int)(((double) interCnt / totCnt) * 65536);
        }
        return answer;
    }
}
