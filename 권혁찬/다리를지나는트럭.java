package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7,4,5,6};
        int answer = solution(bridge_length, weight, truck_weights);
        System.out.println("answer = " + answer);
    }


    static Queue<Integer> queue = new LinkedList<>();
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int time = 0;
        int weight_sum = 0;
        for(int i=0; i<truck_weights.length; i++){
            while (true){
                if(queue.size() == bridge_length){
                    weight_sum -= queue.poll();
                }
                if(weight_sum + truck_weights[i] <= weight){
                    queue.add(truck_weights[i]);
                    weight_sum += truck_weights[i];
                    time++;
                    break;
                }
                else if(weight_sum + truck_weights[i] > weight) {
                    queue.add(0);
                    time++;
                }
            }
        }
        answer = time + bridge_length;
        return answer;
    }

}
