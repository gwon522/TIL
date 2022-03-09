package Programmers;

import java.util.ArrayList;

public class ReportResult {
    class User{
        String name;
        ArrayList<String> reportUser = new ArrayList<>();

        void User(String name, String reportUser){
            this.name = name;
        }

        void add(String reportUser){
            this.reportUser.add(reportUser);
        }
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        //한번에 한명의 유저를 신고 가능하고 여러번 신고 가능 but 똑같은 대상을 여러번 신고해도 1번만 신고됨
        
        //k번 이상시 정지 신고한 유저한테 메일로 발송 return에는 신고한 유저에게 메일 몇통가는지 배열 반환
        
        
        return answer;
    }
}
