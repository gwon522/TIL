package BeakJoon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFSandBFS1260 {
    static int[][] map;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //노드의 갯수 간선의 갯수 시작값
        int nodeCnt = sc.nextInt();
        int lineCnt = sc.nextInt();
        int start = sc.nextInt();

        //0은 무시
        map= new int[nodeCnt+1][nodeCnt+1];

        //1부터 시작하기 위해서 +1선언해줌
        boolean[] dfsVisit = new boolean[nodeCnt+1];
        boolean[] bfsVisit = new boolean[nodeCnt+1];

        //양방향
        for(int i=0; i<lineCnt;i++){
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            //(x,y) 위치와 (y,x)위치 둘이 연결됬다고 보기때문에 (양방향)
            map[node1][node2]=map[node2][node1]=1;
        }
        sc.close();

        //깊이우선 탐색 시작
        dfs(dfsVisit,start);
        System.out.println();
        bfs(bfsVisit,start);
        // print(map);
    }

    static void dfs(boolean[] visit, int start){
        visit[start]=true;
        System.out.print(start+" ");

        for (int i = 1; i < map.length; i++) {
            if(map[start][i]==1 && !visit[i]){
                dfs(visit,i);
            }
        }
    }

    static void bfs(boolean[] visit, int start){
        Queue<Integer> q = new LinkedList<>();
        visit[start]=true;
        System.out.print(start+" ");
        q.add(start);

        while(!q.isEmpty()){
            int num = q.poll();
            for (int i = 0; i < map.length; i++) {
                if(map[num][i]==1&& !visit[i]){
                    q.add(i);
                    visit[i]=true;
                    System.out.print(i+" ");
                }
            }
        }
    }   

    static void print(int[][] map){
        for (int[] is : map) {
            System.out.println(Arrays.toString(is));
        }
    }
}
