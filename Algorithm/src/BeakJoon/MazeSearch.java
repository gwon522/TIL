package BeakJoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MazeSearch {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int col = sc.nextInt();
        int row = sc.nextInt();
        sc.nextLine();

        int[][] map = new int[col][row];
        boolean[][] visit = new boolean[col][row];
        
        for(int i = 0; i<col;i++){
            String str = sc.nextLine();
            for(int j = 0; j<row;j++){
                map[i][j]=str.charAt(j)-'0';
            }
        }
        sc.close();
        Queue<int[]> q = new LinkedList<int[]>();
        
        visit[0][0]=true;
        q.add(new int[]{0,0});
        
        while(!q.isEmpty()){
            int[] pos = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = pos[0]+dx[i];
                int ny = pos[1]+dy[i];
                
                if(nx<0 || nx>=col || ny<0 || ny>=row) continue;
                if(visit[nx][ny]) continue;
                if(map[nx][ny]==0) continue;
                visit[nx][ny]=true;
                map[nx][ny]=map[pos[0]][pos[1]]+1;
                q.add(new int[]{nx,ny});
            }
        }
        System.out.println(map[col-1][row-1]);
    }
}
