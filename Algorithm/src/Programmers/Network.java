package Programmers;


public class Network {
    public static int solution(int n, int[][] computers) {
        int answer = 0;

        //방문 체크 해줄 boolean배열 
        boolean [] visited = new boolean[n];

        //컴퓨터 대수만큼 forans
        for(int i = 0; i < n;i++){
            //방문 안했을때만 dfs
            if(!visited[i]){
                answer += dfs(i,computers,visited);
            }
        }
        
        return answer;
    }

    private static int dfs(int i, int [][] computers, boolean [] visited){
        //방문 체크
        if(visited[i]){
            return 0; 
        }
        //방문 안했으면 방문 체크하고 로직 돌림
        visited[i] = true;
        
        //i번째 컴퓨터 정보만큼 for문
        for(int j=0;j<computers[i].length;j++){
            //[x,y]좌표가 1일경우( 연결되어 있는경우 )
            if(computers[i][j] == 1){
                dfs(j,computers,visited);
            }
        }
        return 1;
    }
}
