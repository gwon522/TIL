package LeetCode;

public class MinCostClimbinStairs {
    int[] cntCost = new int[1001];
    public int minCostClimbingStairs(int[] cost) {
        cntCost[0]=0;
        cntCost[1]=0;
        if(cost.length == 1) return cost[0];
        if(cost.length == 2) return Math.min(cost[0],cost[1]);
        
        for(int i = 2; i<cost.length+1;i++){
            cntCost[i]=Math.min(cntCost[i-2]+cost[i-2],cntCost[i-1]+cost[i-1]);
        }
        return cntCost[cost.length];
    }
}
