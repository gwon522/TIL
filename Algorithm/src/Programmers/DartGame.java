package Programmers;

public class DartGame {
    public static int solution(String dartResult) {
        int answer = 0;
        //3번의기회 0점~10점
        //S D T 제곱
        //*은 바로전점수 2배, 해당점수 2배 #은 해당점수 마이너스
        //*은 첫번째도 가능 하지만 첫점수만 2배
        //스타상은 중첩가능, 아차상도 중첩가능
        String temp="";
        String [] data = dartResult.split("");
        int [] score = new int[3];
        int index =0;

        for (int i = 0; i < data.length; i++) {
            String value = data[i];
            System.out.println(value);
            System.out.println("index :"+index);
            if(value.equals("*")){
                score[index-1]*=2;
                if(index>1){
                    score[index-2]*=2;
                }
            }else if(value.equals("#")){
                score[index-1]*=-1;
            }else if(value.equals("S")){
                score[index]=(int)Integer.parseInt(temp);
                temp="";
                index++;
            }else if(value.equals("D")){
                score[index]=(int)Math.pow(Integer.parseInt(temp), 2) ;
                temp="";
                index++;
            }else if(value.equals("T")){
                score[index]=(int)Math.pow(Integer.parseInt(temp), 3) ;
                temp="";
                index++;
            }else{
                temp+=String.valueOf(value);
            }
        }
        for (int i = 0; i < score.length; i++) {
            answer+=score[i];
        }
        
        return answer;
    }
}
