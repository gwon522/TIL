package BeakJoon;

import java.io.*;

public class Beak1110 {
    /* 
        0 <= n <= 99
        10보다 작으면 앞에 0을 붙여서 2자리로 만들고 더하기 시작
        ex) 1 -> 01 => 1+1 = 2 ..12  i=1
            1 + 2 = 3 .. 23 i = 2
            2 + 3 = 5 .. 35 i = 3
            3 + 5 = 8 .. 58 i = 4
            5 + 8 = 13 .. 83 i =5
            8 + 3 = 11 .. 31 i =6
            3 + 1 = 4 .. 14  i=7
            1 + 4 = 5 .. 45 i=8
            4 + 5 = 9 .. 59 i=9
            5 + 9 = 14 .. 94 i=10
            ..... i = 60에 종료
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count =0;
        int number = Integer.parseInt(br.readLine());
        int copyNumber = number;

        do{
            number = ((number%10)*10) + (((number/10)+(number%10))%10);
            count++;
        }while (copyNumber!=number);
        System.out.println(count);
    }
}
