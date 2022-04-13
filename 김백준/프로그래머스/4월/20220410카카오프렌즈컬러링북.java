import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class coloringBook {
    public static void main(String[] args){
        int[][] arr = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        int[] ans = solution(6,4,arr);
        int na = ans[0];
        int nb = ans[1];

        System.out.println("area: " + na +", max: " + nb);
    }

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] checked = new boolean[m][n];

        List<List> total = new ArrayList<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int x = j; int y = i;

                if(checked[y][x]){continue;}
                if(picture[y][x] == 0){continue;}
                boolean keep = true;
                List<Map<String, Integer>> road = new ArrayList<>();
                while(keep){
                    //System.out.println("x: "+x+", y: "+y);
                    int color = picture[y][x];
                    int c = 0;
                    Map<String, Integer> pos = new HashMap<>();
                    pos.put("x", x);
                    pos.put("y", y);
                    if(!checked[y][x]){road.add(pos);}
                    checked[y][x] = true;
                    if(x != n-1){
                        c = picture[y][x+1];
                        if(!checked[y][x+1] && color == c && c != 0) {x = x + 1;continue;}
                    }
                    if(y != m-1){
                        c = picture[y+1][x];
                        if(!checked[y+1][x] && color == c && c != 0){y = y + 1;continue;}
                    }
                    if(x != 0){
                        c = picture[y][x-1];
                        if(!checked[y][x-1] && color == c && c != 0){x = x-1;continue;}
                    }
                    if(y != 0){
                        c = picture[y-1][x];
                        if(!checked[y-1][x] && color == c && c != 0){y = y-1;continue;}
                    }

                    //여기까지 오면 막다른 길까지 옷 것.
                    //길 중에 상하좌우가 열린 곳으로 가야 함
                    boolean find = false;
                    int fx = 0;
                    int fy = 0;
                    for(Map<String, Integer> p : road){
                        fx = p.get("x");
                        fy = p.get("y");
                        if(fx != n-1){
                            int co = picture[fy][fx+1];
                            if(!checked[fy][fx+1] && color == co && co != 0 ){find = true;break;}
                        }
                        if(fy != m-1){
                            int co = picture[fy+1][fx];
                            if(!checked[fy+1][fx] && color == co && co != 0){find = true;break;}
                        }
                        if(fx != 0){
                            int co = picture[fy][fx-1];
                            if(!checked[fy][fx-1] && color == co && co != 0){find = true;break;}
                        }
                        if(fy != 0){
                            int co = picture[fy-1][fx];
                            if(!checked[fy-1][fx] && color == co && co != 0){find = true;break;}
                        }
                    }

                    //길 중에 상하좌우가 열린 곳을 못찾으면 while을 끝냄
                    //찾았으면 그 위치로 보냄
                    if(find){
                        x = fx;
                        y = fy;
                    }else{
                        keep = false;
                        total.add(road);
                        break;
                    }
                }

            }
        }

        numberOfArea = total.size();
        for(int k = 0; k < total.size(); k++){
            if (maxSizeOfOneArea <= total.get(k).size()){
                maxSizeOfOneArea = total.get(k).size();
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}

