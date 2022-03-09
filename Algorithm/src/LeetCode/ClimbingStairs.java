package LeetCode;

public class ClimbingStairs {
    //최대 45개 까지만 있기때문에 선언
    int[] climbStairsCnt = new int[45];

    public int climbStairs(int n){
        //계단을 오를 수 있는 방법 계산 climbStairs(3)일 경우
        
        //1,1,1  & 1,2 & 2,1 로 3가지 경우가 가능 bottom-up 방식으로 계산
        //c(1) = 1, c(2) = 2 c(3) = ? 2걸음이 최대이기 때문에 n 번째 계단오르는방식은 n-1번째에서 오는방법과 n-2에서 오는방법 -> 피보나치랑 비슷함
        // c(3) = c(1)+ c(2)
        if(n == 1 ) return 1;
        if(n == 2 ) return 2;
        
        climbStairsCnt[0] = 1;
        climbStairsCnt[1] = 2;
    
        for(int i = 2; i<n+1;i++){
            climbStairsCnt[i] = climbStairsCnt[i-1] + climbStairsCnt[i-2];
        }
        return climbStairsCnt[n];

    }
}
