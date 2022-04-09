package BeakJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak1032 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i]=br.readLine();
        }
        
        for (int i = 0; i < arr[0].length(); i++) {
            char compareData = arr[0].charAt(i);
            boolean isEqual = true;

            for (int j = 0; j < arr.length; j++) {
                if(compareData != arr[j].charAt(i)){
                    isEqual = false;
                    break;
                }
            }

            if(isEqual) sb.append(compareData);
            else sb.append('?');
        }
        System.out.println(sb);

    }

    static void print(String[] arr){
        for (String string : arr) {
            System.out.println(string);
        }
    }
}
