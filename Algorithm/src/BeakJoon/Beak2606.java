package BeakJoon;

import java.util.*;

public class Beak2606 {
    static int comCnt,lineCnt,virusCnt;
    static int[][] map;
    static boolean[] visit;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        comCnt = sc.nextInt();
        lineCnt = sc.nextInt();
        sc.nextLine();
        //map, visit 선언
        map = new int[comCnt+1][comCnt+1];
        visit = new boolean[comCnt+1];


        //map에 저장
        for (int i = 0; i < lineCnt; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();

            map[p1][p2] = map[p2][p1] = 1;
        }

        bfs(1);
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        virusCnt=0;
        visit[start]= true;

        while(!q.isEmpty()){
            int x = q.poll();

            for(int i =1; i<comCnt;i++){
                if(!visit[i] && map[x][i]!=0){
                    q.add(i);
                    virusCnt++;
                }
            }
            
        }
        
        System.out.println(virusCnt);
    }
}
