package BeakJoon;

import java.util.LinkedList;
import java.util.Queue;

//bfs로 풀어볼것
public class War3 {

    public void sol(int n, int m, char[][] solider){
        int w=0,b=0;
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        int cnt = 0;
        boolean[][] visited = new boolean[n][m];

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j]){
                    cnt=1;
                    visited[i][j] = true;
                    q.offer(new int[]{i,j});

                    while(!q.isEmpty()){
                        int[] pos= (int[])q.poll();

                        for (int k = 0; k < dx.length; k++) {
                            int nx = pos[0]+dx[k];
                            int ny = pos[1]+dy[k];

                            if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                            if(visited[nx][ny]) continue;
                            if(solider[nx][ny]!=solider[i][j]) continue;
                            cnt++;
                            visited[nx][ny] = true;
                            q.offer(new int[]{nx,ny});
                        }
                    }

                    if(solider[i][j]=='W')
                        w+= (cnt*cnt);
                    else
                        b+= (cnt*cnt);
                }
            }
        }

        System.out.println(w+" "+b);
    }
}
