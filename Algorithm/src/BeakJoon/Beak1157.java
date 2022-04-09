package BeakJoon;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Beak1157{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new  InputStreamReader(System.in));
        String [] strArr = br.readLine().split("");
        int count = 0;
        int max =0;
        String maxKey="";
        Map<String,Integer> strMap = new HashMap<String,Integer>();
        
        for(int i =0; i<strArr.length;i++){
            String key = strArr[i].toLowerCase();
            strMap.put(key,(strMap.getOrDefault(key, 0))+1);
        }

        Iterator<String> it = strMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            Integer n = strMap.get(key);
            if(max<n){
                count = 0;
                max = n;
                maxKey = key;
            }else if(max==n){
                count = 1;
            }
        }   
        if(count<=1) System.out.println("?");
        else System.out.println(maxKey.toUpperCase());
    }
}