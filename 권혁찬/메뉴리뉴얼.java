package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] orders = new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = new int[]{2,3,5};
        String[] answer = solution(orders, course);
        Arrays.stream(answer).forEach(System.out::println);
    }

    static List<String> combination = new ArrayList<>();
    public static String[] solution(String[] orders, int[] course) {
        combination = new ArrayList<>();
        for (int i = 0; i < orders.length; i++) {
            String[] chars = orders[i].split("");
            Arrays.sort(chars);
            for (int j = 0; j < chars.length - 1; j++) {
                for (int k = 0; k < course.length; k++) {
                    dfs(chars, j, 1, course[k], chars[j]);
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();
        for(String menu : combination) {
            map.put(menu, map.getOrDefault(menu, 0)+1);
        }

        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return map.get(o2)-map.get(o1);
            }
        });

        List<String> res = new ArrayList<>();
        for(int i=0; i< course.length; i++) {
            int max =0;
            for(String courseMenu : list) {
                if(map.get(courseMenu)>1 && courseMenu.length() == course[i]) {
                    if(map.get(courseMenu) >= max) {
                        res.add(courseMenu);
                        max = map.get(courseMenu);
                    }
                }
            }
        }

        Collections.sort(res);

        String[] answer = new String[res.size()];
        res.toArray(answer);

        return answer;
    }

    private static void dfs(String[] chars, int idx, int len, int courseValue, String str) {
        if (len == courseValue) {
            combination.add(str);
        }
        for(int i= idx+1; i<chars.length; i++) {
            dfs(chars, i, len+1, courseValue, str+chars[i]);
        }
    }


    /*다른 사람의 풀이1*/
    static HashMap<String,Integer> map;
    static int m;
    public String[] solution2(String[] orders, int[] course) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i=0;i<course.length;i++){
            map = new HashMap<>();
            m=0;
            for (int j=0;j<orders.length;j++) {
                find(0, "", course[i], 0, orders[j]);
            }
            for (String s : map.keySet()){
                if (map.get(s)==m&&m>1){
                    pq.offer(s);
                }
            }
        }
        String  ans[] = new String[pq.size()];
        int k=0;
        while (!pq.isEmpty()){
            ans[k++] = pq.poll();
        }
        return ans;
    }
    static void find(int cnt,String str,int targetNum,int idx,String word){
        if (cnt==targetNum){
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String temps="";
            for (int i=0;i<c.length;i++)temps+=c[i];
            map.put(temps,map.getOrDefault(temps,0)+1);
            m = Math.max(m,map.get(temps));
            return;
        }
        for (int i=idx;i<word.length();i++){
            char now =word.charAt(i);
            find(cnt+1,str+now,targetNum,i+1,word);
        }
    }

    /*다른 사람의 풀이2*/
    Map<String, Integer> courseMap = new HashMap<>();
    List<String> answerList = new ArrayList<>();
    public String[] solution3(String[] orders, int[] course) {

        // orders를 모두 오름차순으로 정렬
        for (int i=0; i<orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        // course수의 모든 조합 구하기
        for (int i=0; i<course.length; i++) {
            for (int j=0; j<orders.length; j++) {
                String order = orders[j];

                if (course[i] <= order.length()) {
                    boolean[] visited = new boolean[order.length()];
                    combination(order.toCharArray(), 0, course[i], visited);
                }
            }

            // 가장 많이 주문한 단품메뉴 조합 구하기, answer에 넣기
            if (!courseMap.isEmpty()) {
                findPopularCourse();
                courseMap.clear();
            }
        }

        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        for (int i=0; i<answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    public void combination(char[] order,int start, int r, boolean[] visited) {
        if (r == 0) {
            addCourse(order, visited);
            return;
        }

        for (int i=start; i<order.length; i++) {
            visited[i] = true;
            combination(order, i+1, r-1, visited);
            visited[i] = false;
        }
    }

    public void addCourse(char[] order, boolean[] visited) {
        String course = "";
        for (int i=0; i<order.length; i++) {
            if (visited[i]) {
                course += order[i];
            }
        }
        courseMap.put(course, courseMap.getOrDefault(course, 0) + 1);
    }

    public void findPopularCourse() {
        List<Integer> countList = new ArrayList<>(courseMap.values());
        int max = Collections.max(countList);

        if (max >= 2) {
            for (String key: courseMap.keySet()) {
                if (courseMap.get(key) == max){
                    answerList.add(key);
                    System.out.println(key);
                }
            }
        }
    }



}
