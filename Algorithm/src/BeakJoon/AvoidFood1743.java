package BeakJoon;

import java.util.*;



class Node{
    private int x;
    private int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    int getX(){
        return this.x;
    }
    int getY(){
        return this.y;
    }
}

public class AvoidFood1743 {
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n,m,foodCnt,cnt, answer;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        foodCnt = sc.nextInt();
        sc.nextLine();//버퍼 삭제용
        answer =1;
        map = new int[n][m];
        visit = new boolean[n][m];

        //입력받음
        for (int i = 0; i < foodCnt; i++) { 
            int x = sc.nextInt();
            int y = sc.nextInt();

            map[x-1][y-1]=1;
        }
        print(map);
        sc.close();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visit[i][j] && map[i][j]!=0){
                    bfs(i,j);
                }
            }
        }
        
        
        System.out.println(answer);
    }
    static void bfs(int num1, int num2){
        Queue<Node> q = new LinkedList<Node>();
        q.add(new Node(num1, num2));
        visit[num1][num2]=true;
        cnt=1;
        while(!q.isEmpty()){
            Node node = q.poll();
            int x = node.getX();
            int y = node.getY();
            
            for (int i = 0; i < 4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                
                if(nx<0||ny<0||nx>=n||ny>=m) continue;
                if(visit[nx][ny])continue;
                if(map[nx][ny]==0)continue;
                
                visit[nx][ny] = true;
                print(visit);
                q.add(new Node(nx,ny));
                cnt++;
            }
        }
        answer = Math.max(answer,cnt);
    }
    static void print(int[][] map){
        for (int[] is : map) {
            System.out.println(Arrays.toString(is));
        }
        System.out.println();
    }   
    static void print(boolean[][] map){
        for (boolean[] is : map) {
            System.out.println(Arrays.toString(is));
        }
        System.out.println();
    }   
}
