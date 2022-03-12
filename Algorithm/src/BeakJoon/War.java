package BeakJoon;

import java.util.Arrays;
import java.util.Scanner;

public class War {
    static boolean[][] visited;
    static char[][] map;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int n,m,cnt;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //n m 입력 받음
        n = sc.nextInt();
        m = sc.nextInt();

        //입력받은 값 저장할 배열생성
        map = new char[n][m];
        //방문표기하기 위한 배열생성
        visited = new boolean[n][m];

        //n번만큼 입력받아서 배열에 저장해줌
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        cnt = 0;

        int w = 0, b = 0;

        //배열 다 돌아야함
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //만약 방문 안했으면
                if(!visited[i][j]){
                    //카운트 증가
                    cnt=1;
                    //dfs돌림 상하좌우 다돌아야해서
                    dfs(map[i][j],i,j);

                    if(map[i][j]=='W'){
                        w+=(cnt*cnt);
                    }else{
                        b+=(cnt*cnt);
                    }
                }
            }
        }
        System.out.println(w+" "+b);
        sc.close();
    }

    static void dfs(char ch, int i, int j){
        //다시 돌수도 있으니 방문체크해줌
        visited[i][j]=true;
        //nx, ny 상하좌우 이동시키기 위해서 사용하는 임시변수
        for (int j2 = 0; j2 < dx.length; j2++) {
            int nx = i+dx[j2];
            int ny = j+dy[j2];

            //0보다 작거나 n보다크면 배열크기 초과 스킵
            if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
            //방문한적 있으면 스킵
            if(visited[nx][ny]) continue;
            //현재값이 임시변수 선언된 값이랑 다르면 스킵
            if(map[nx][ny]!=ch) continue;

            //cnt 증가시켜주고
            cnt++;
            //dfs 또 돌려줌
            dfs(ch,nx,ny);
        }
    }

    static void print(char[][] map){
        for (char[] cs : map) {
            System.out.println(Arrays.toString(cs));
        }
    }
}
