package Algo;

import java.util.Arrays;

public class Test {
    public int ans(int n, int[][]l){
        int[][] graph = new int[12][12];
        int[][] reat = new int[n][4];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], 0);
        }
        for (int i = 0; i < n; i++) {
            int mx = Math.max(l[i][0], l[i][2]);
            int mnx = Math.min(l[i][0], l[i][2]);
            int my = Math.max(l[i][1], l[i][3]); 
            int mny = Math.min(l[i][1], l[i][3]); 
            reat[i]=new int[]{mny,my,mnx,mx};
            boolean overlap = false;
            for (int x = mnx; x <= mx; x++) {
                for (int y = mny; y <= my; y++) {
                    if(graph[x][y]!=0){
                        overlap=true;
                        graph[x][y]++;
                        continue;
                    }
                    graph[x][y]=1;
                }
                if(overlap){
                    //겹치면 옮겨야 함
                }
            }
        }
        return 0;
    } 
    public int bfs(int max_X, int min_X, int max_Y, int min_Y, int[][]graph){
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        for (int i = 0; i < 4; i++) {
            int nx = min_X + dx[i];
            int ny = min_Y + dy[i];
            
            if(nx<0||nx>=32767||ny<0||ny>=32767) continue;
        }

        return 0;
    };
    public void print(int[][]graph){
        for (int i = 0; i < graph.length; i++) {
            int[] inArr = graph[i];
            for (int j = 0; j < inArr.length; j++) {
            System.out.print(inArr[j] + " ");
            }
            System.out.println();
        }
    }
}
