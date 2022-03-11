package BeakJoon;

public class To1 {
    
    public int solution(int n){
        if(n<=3) return 1;

        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=0;

        for(int i = 2; i<=n;i++){
            dp[i]=dp[i-1]+1;
            if(i%3==0) dp[i]=Math.min(dp[i], dp[i/3]+1);
            if(i%2==0) dp[i]=Math.min(dp[i], dp[i/2]+1);
        }
        
        return dp[n];

    }
}
