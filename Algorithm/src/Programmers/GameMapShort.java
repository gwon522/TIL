package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class GameMapShort {
        static int [] dx = {1,0,-1,0};
        static int [] dy = {0,1,0,-1};
        
        public int solution(int[][] maps) {
            int answer = 0;
            //2차원 배열. 1<= n,m <= 100
            //n과 m 같을수 있음 n과 m은 둘다 1일수 없음. 시작점 1,1 종점 n-1,m-1
            //n,m 숫자 찾기
            int n = maps.length-1;
            int m = maps[0].length;
            
            //visited 만들기
            boolean[][] visited = new boolean[n][m];
            answer = bfs(maps,visited);
            return answer;
        }
        
        static int bfs(int[][] maps, boolean[][] visited){
            int x = 0,y = 0;
            Queue<int[]> q = new LinkedList<>();
            
            visited[x][y]=true;
            
            q.add(new int[]{x,y});
            
            while(!q.isEmpty()){
                int [] position = q.remove();
                
                for(int i=0; i<4; i++){
                    int nextX = position[0] + dx[i];
                    int nextY = position[1] + dy[i];
                    
                    if(0<nextX || nextX>maps.length-1 || nextY>maps[0].length-1||nextY<0){
                        continue;
                    }
                    if(visited[nextX][nextY]==false&&maps[nextX][nextY]==1){
                        q.add(new int[]{nextX,nextY});
                    }
                }
            }
            return 1;
        }

}
