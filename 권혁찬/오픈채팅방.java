package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] records = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] answer = solution(records);
        Arrays.stream(answer).forEach(System.out::println);
    }

    public static String[] solution(String[] records) {
        Map<String, String> uidMap = new HashMap<>();
        List<String> answerList = new ArrayList<>();
        for (String record : records) {
            String act = record.split(" ")[0];
            if (!"Leave".equals(act)) {
                String uid = record.split(" ")[1];
                String name = record.split(" ")[2];
                uidMap.put(uid, name);
            }
        }
        for (String record : records) {
            String name = uidMap.get(record.split(" ")[1]);
            String act = record.split(" ")[0];
            if ("Enter".equals(act)) {
                answerList.add(name + "님이 들어왔습니다.");
            } else if ("Leave".equals(act)) {
                answerList.add(name + "님이 나갔습니다.");
            }
        }
        String[] answer = answerList.toArray(new String[answerList.size()]);
        return answer;
    }
}
