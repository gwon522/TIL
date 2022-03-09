package LeetCode;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        //메모제이션할 배열 만들어주고 (0,0) 값 넣어줌
        int [][] memo = new int[row][col];
        memo[0][0] = grid[0][0];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //i,j 0일 때는 스킵
                if(i==0 && j==0) continue;
                //i,j값이 0보다 작으면 어쩌피 값 없기 때문에 최고 값 넣어줘서 오류처리
                int up = (i > 0) ? memo[i-1][j] : Integer.MAX_VALUE;
                int left = (j > 0) ? memo[i][j-1] : Integer.MAX_VALUE;

                memo[i][j]=Math.min(left,up)+grid[i][j];
            }
        }

        return memo[row-1][col-1];
    }
}
