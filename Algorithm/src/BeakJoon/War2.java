package BeakJoon;

public class War2 {
    int w=0,b=0,cnt=0;
    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};

    public void sol(int n, int m, char[][] solider){
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j]){
                    cnt=1;
                    dfs(solider,i,j,visited);

                    if(solider[i][j]=='W')
                        w+=(cnt*cnt);
                    else   
                        b+=(cnt*cnt);
                }
            }
        }
        System.out.println(w+" "+b);
    }

    void dfs(char[][] solider, int n, int m, boolean[][] visited){
        visited[n][m]=true;
        for (int i = 0; i < dx.length; i++) {
            int nx = n+dx[i];
            int ny = m+dy[i];

            if(nx<0 || nx>=visited.length || ny<0 || ny>=visited.length) continue;
            if(visited[nx][ny]) continue;
            if(solider[n][m]!=solider[nx][ny]) continue;
            cnt++;
            dfs(solider,nx,ny,visited);
        }
    }
}
