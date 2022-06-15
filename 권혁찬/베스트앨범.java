package com.company;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] answer = solution(genres, plays);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    static Map<String, Integer> genreMap = new HashMap<>();
    static Map<String, Integer> playMap = new HashMap<>();
    static ArrayList<Integer> result = new ArrayList<>();
    public static int[] solution(String[] genres, int[] plays) {

        for (int i = 0; i < genres.length; i++) {
            if(!genreMap.containsKey(genres[i])){
                genreMap.put(genres[i], plays[i]);
            }
            else {
                genreMap.put(genres[i], genreMap.get(genres[i]) + plays[i]);
            }
            String playKey = i + " " + genres[i];
            if(!playMap.containsKey(playKey)){
                playMap.put(playKey, plays[i]);
            }
            else {
                playMap.put(playKey, playMap.get(playKey) + plays[i]);
            }
        }

        // genreMap value로 정렬
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(genreMap.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // playMap value로 정렬
        ArrayList<Map.Entry<String, Integer>> playEntries = new ArrayList<>(playMap.entrySet());
        Collections.sort(playEntries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // 총 횟수 높은 장르 부터
        for(Map.Entry<String, Integer> entry : entries) {
            int cnt = 0;
            for(Map.Entry<String, Integer> playEntry : playEntries) {
                if(cnt == 2){
                    break;
                }
                if(playEntry.getKey().split(" ")[1].equals(entry.getKey())){
                    result.add(Integer.parseInt(playEntry.getKey().split(" ")[0]));
                    cnt++;
                }
            }
        }
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
