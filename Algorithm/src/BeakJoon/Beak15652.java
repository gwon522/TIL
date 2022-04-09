package BeakJoon;

import java.util.Scanner;

public class Beak15652{
    static int n = 0;
    static int m = 0;
    static int [] arr;
    static StringBuilder sb;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[m];
        sc.close();

        dfs(1,0);
        System.out.println(sb);
    }
    static void dfs(int start, int depth){
        if(depth == m){
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = start; i<=n;i++){
            arr[depth]=i;
            dfs(i,depth+1);
        }
    }
}