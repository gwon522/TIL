package BeakJoon;

import java.util.*;

public class Beak1145 {
    //5개의 정수가 주어지고 3개 이상 배수인거 리턴
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int [] intArr = new int[5];
        for(int i =0; i<5;i++){
            intArr[i]=sc.nextInt();
        }
        int count = 0;
        int val =1;

        while(true){
            for(int i =0; i<5;i++){
                if(val % intArr[i] == 0){
                    count++;
                }
            }
            if(count>=3){
                System.out.println(val);
                return;
            }
            count = 0;
            val++;
        }
    }
}
