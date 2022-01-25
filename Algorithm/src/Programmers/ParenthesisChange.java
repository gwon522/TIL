package Programmers;

import java.util.Stack;

public class ParenthesisChange {
    static int index;

    public static String answer(String p){

        //빈 문자열일 경우 공백 반환
        if(p.equals("")){
            return p;
        }

        //올바른 괄호문자열인지 체크
        boolean isCorrect = isCorrct(p);
        
        //균형잡힌 괄호 문자열 u, v로 분할
        String u = p.substring(0,index);
        String v = p.substring(index);

        //올바른 문자열일 경우
        if(isCorrect){
            return u + answer(v);
        }

        //올바른 문자열이 아닐경우
        StringBuilder sb = new StringBuilder();
        // 첫번째에 "(" 붙임 -> v를 1번부터 다시 시작 -> ")" 붙여주기
        sb.append("(").append(answer(v)).append(")");

         // 첫번째 0 과 마지막 length-1의 값을 빼고 괄호방향 반대로 뒤에 붙여줌
         for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(')
                sb.append(')');
            else
                sb.append('(');
        }

        //생성된 문자열 반환
        return sb.toString();
    }

    private static boolean isCorrct(String p){
        boolean isCorrect = true;
        int left = 0, right = 0;
        Stack<String> stack = new Stack<>();
        String [] splitString = p.split("");

        for (int i = 0; i < p.length(); i++) {
            if(splitString[i].equals("(")){
                stack.add("(");
                left++;
            }else{
                right++;
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    isCorrect = false;
                }
            }

            if(left==right){
                index=i+1;
                return isCorrect;
            }
        }
        return isCorrect;
    }
}
