package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int answer = solution(cacheSize, cities);
        System.out.println("answer = " + answer);
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> cache = new ArrayList<>();
        if(cacheSize == 0){
            return cities.length * 5;
        }
        for(String city : cities){
            city = city.toLowerCase();
            if(cache.contains(city)){
                cache.remove(city);
                cache.add(city);
                answer = answer + 1;
            }
            else {
                answer = answer + 5;
                if(cache.size() >= cacheSize){
                    cache.remove(0);
                    cache.add(city);
                }
                else {
                    cache.add(city);
                }
            }
        }
        return answer;
    }


}
