package Algo;

public class AntSolider {
    public int antSolider(int foodWare, int[] foodCnt){
        int[] dp = new int[foodWare];

        //한칸 이상 떨어진것만 약탈가능 최대한 많은걸 찾아야하기 떄문에 max
        dp[0] = foodCnt[0];
        dp[1] = Math.max(foodCnt[0],foodCnt[1]);
        //f(i) = max(f(i-1),f(i-2)+k(i));
        for (int i = 2; i <foodWare ; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+foodCnt[i]);
        }

        return dp[foodWare-1];
    }
}
