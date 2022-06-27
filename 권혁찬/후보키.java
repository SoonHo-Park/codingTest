package com.company;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[][] relation = {{"100","ryan","music","2"},
                               {"200","apeach","math","2"},
                               {"300","tube","computer","3"},
                               {"400","con","computer","4"},
                               {"500","muzi","music","3"},
                               {"600","apeach","music","2"}};
        int answer = solution(relation);
        System.out.println(answer);
    }

    static ArrayList<List<Integer>> combiList = new ArrayList<>();
    public static int solution(String[][] relation){
        int answer = 0;
        int colCnt = relation[0].length;
        // 조합만들기
        for(int r=1; r<=colCnt; r++){
            int[] inArray = {0,1,2,3,4,5,6,7};
            int[] outArray = new int[r];
            boolean[] visited = new boolean[8];
            combination(inArray, outArray, visited, 0, 0, r, colCnt);
        }
        //printList();
        for(int i=0; i<combiList.size(); i++){
            Set<String> set = new HashSet<>();
            for(int r=0; r<relation.length; r++){
                String uniqueTemp = "";
                for(int idx : combiList.get(i)){
                    if(idx >= colCnt){
                        break;
                    }
                    uniqueTemp += relation[r][idx];
                }
                set.add(uniqueTemp);
            }
            if (relation.length == set.size()) {
                answer++;
                // 해당 인덱스가 포함된 combiList삭제
                deltList(combiList.get(i), i+1);
            }
        }
        return answer;
    }

    public static void deltList(List<Integer> copyList, int start){
        int totLen = copyList.size();
        int chkCnt = 0;
        for(int i=start; i<combiList.size(); i++){
            chkCnt = 0;
            for(int copyInfo : copyList){
                if(combiList.get(i).contains(copyInfo)){
                    chkCnt++;
                }
            }
            // combi의 전체요소를 가지고 있으면 제거하고 인덱스 오류 방지를 위해 빈 리스트 추가
            if(totLen == chkCnt){
                combiList.remove(i);
                combiList.add(i, new ArrayList<>());
            }
        }
    }

    public static void combination(int[] inArray, int[] outArray, boolean[] visited, int start, int depth, int r, int colCnt){
        if(depth == r){
            combiList.add(Arrays.stream(outArray).boxed().collect(Collectors.toList()));
            return;
        }
        for(int i=start; i<colCnt; i++){
            if(!visited[i]){
                visited[i] = true;
                outArray[depth] = inArray[i];
                combination(inArray, outArray, visited, i+1, depth+1, r, colCnt);
                visited[i] = false;
            }
        }
    }

    public static void printList(){
        for(List list : combiList){
            for(Object info : list){
                System.out.print(info + " ");
            }
            System.out.println();
        }
    }

}
