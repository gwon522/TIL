package Programmers;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Collections;
import java.util.LinkedList;

// 문제 다시한번 생각더 해봐야 할듯 
public class Printer {
    
    //우선순위 큐로 푼 문제
    public static int answer(int [] priorityList, int location){
        int answer = 0;
        //우선 순위 큐를 선언 (큰수 부터)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : priorityList) {
            pq.add(num);
        }

        while (!pq.isEmpty()) {
            for (int i = 0; i < priorityList.length; i++) {
                if(pq.peek()==priorityList[i]){
                    pq.poll();
                    answer++;
                    if(location==i){
                        return answer;
                    }
                }
            }
        }
        return answer;
    }


    // 큐로 문제풀이
    static class Task{
        int priority;
        int location;
        public Task(int location, int priority){
            this.priority = priority;
            this.location = location;
        }
    }
    public static int answer2(int[] priorityList, int location){
        int answer = 0;
        Queue<Task> q = new LinkedList<>();

        for (int i = 0; i < priorityList.length; i++) {
            q.add(new Task(i, priorityList[i]));
        }

        int now = 0;
        while(!q.isEmpty()){
            //큐에서 하나 꺼냄
            Task current = q.remove();

            //큐의 값 비교
            boolean isHigh = false;
            for (Task task : q) {
                if(task.priority>current.priority){
                    isHigh = true;
                }
            }
            if(isHigh){
                q.add(current);
            }else{
                now++;
                if(current.location==location){
                    answer = now;
                    break;
                }
            }
        }

        return answer;
    }

}
