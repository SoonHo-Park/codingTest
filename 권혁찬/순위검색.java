package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        int[] answer = solution(info, query);
        Arrays.stream(answer).forEach(System.out::print);
    }

    static Map<String, ArrayList<Integer>> map = new HashMap<>();
    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // 나올 수 있는 모든 조합 구하기
        for (int i = 0; i < info.length; i++) {
            dfs("", 0, info[i].split(" "));
        }

        // 얕은 복사로 점수 정렬
        ArrayList<String> keySet = new ArrayList<>(map.keySet());
        for (int i = 0; i < keySet.size(); i++) {
            ArrayList<Integer> valuesTemp = map.get(keySet.get(i));
            Collections.sort(valuesTemp);
        }

        // 이진 탐색
        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replace(" and ", "");
            String[] querySplit = query[i].split(" ");
            int value = Integer.parseInt(querySplit[1]);
            int result = binarySearch(querySplit[0], value);
            answer[i] = result;
        }

        return answer;
    }

    public static void dfs(String key, int depth, String[] info) {
        // 점수를 제외한 모든 기준 다 확인했을 때,
        if(depth == 4){
            if(!map.containsKey(key)){
                ArrayList<Integer> values = new ArrayList<>();
                // value 로 점수 리스트 저장
                values.add(Integer.parseInt(info[4]));
                map.put(key, values);
            }
            else {
                // 똑같은 조건에 다른 점수 추가
                map.get(key).add(Integer.parseInt(info[4]));
            }
            return;
        }
        dfs(key + "-", depth + 1, info);
        dfs(key + info[depth], depth + 1, info);
    }

    // 이진탐색
    public static int binarySearch(String key, int value) {
        if(!map.containsKey(key)){
            return 0;
        }
        ArrayList<Integer> trgtList = map.get(key);
        int start = 0;
        int end = trgtList.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (trgtList.get(mid) < value) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        // 리스트의 총 개수에서 시작점을 빼면 찾는 점수 이상의 개수
        return trgtList.size() - start;
    }

}
