package LeetCode;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for(int i = 1; i<=amount; i++){
            //dp[i]번째를 일단 제일 큰값으로 설정해놓음
            for (int coin : coins) {
                //해당 숫자가 코인보다 클때만 실행시켜야됨
                if(i-coin>=0) {
                    //그전에 계산된 dp[i]의 값과 dp[i-coin]에 동전 하나를 더한값중 작은값이 최소값임
                    dp[i]=Math.min(dp[i],dp[i-coin]+1);
                }
            }
            
            // dp[i]=;
        }


        return (dp[amount] > amount) ? -1 : dp[amount];
    }
}
