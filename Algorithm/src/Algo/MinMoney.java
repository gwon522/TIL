package Algo;

import java.util.Arrays;

public class MinMoney {
    public int minMoney(int count,int amount, int[] costs){
        int[] dp = new int[amount+1];
        Arrays.fill(dp, 10001);
        dp[0]=0;
        for (int i = 0; i < count; i++) {
            for (int j = costs[i]; j <= amount; j++) {
                System.out.println("costs = "+costs[i]);
                System.out.println("dp"+j+" = "+dp[j]);
                if(dp[j-costs[i]]!=10001) dp[j] = Math.min(dp[j], dp[j-costs[i]]+1);
            }
        }

        if(dp[amount]==10001) return -1;
        return dp[amount];
    }
}
