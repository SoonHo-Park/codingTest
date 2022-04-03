package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] id_list = new String[]{"muzi", "frodo", "apeach", "neo"};
        String[] report = new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        int[] result = solution(id_list, report, k);
        Arrays.stream(result).forEach(i -> {
            System.out.println(i);
        });
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Set<String> report_set = new HashSet<String>(Arrays.asList(report));
        Map<String, ArrayList<String>> report_id_map = new HashMap<>();
        Map<String, Integer> id_map = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            id_map.put(id_list[i], i);
        }
        report_set.forEach(s -> {
            String idFrom = s.split(" ")[0];
            String idTo = s.split(" ")[1];
            if(!report_id_map.containsKey(idTo)){
                report_id_map.put(idTo, new ArrayList<>());
            }
            report_id_map.get(idTo).add(idFrom);
        });

        report_id_map.forEach((key, value) -> {
            if (value.size() >= k) {
                value.forEach(s -> {
                    answer[id_map.get(s)]++;
                });
            }
        });

        return answer;
    }
}
