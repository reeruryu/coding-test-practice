import java.util.*;

class Solution1 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
 
        Queue<Integer> queue = new LinkedList<>();
        
        int sum = 0;
        int time = 0;
        for (int truck_weight: truck_weights) {
            while (true) {
                if (queue.isEmpty()) {
                    queue.offer(truck_weight);
                    sum += truck_weight;
                    time++;
                    break;
                }
                
                
                if (queue.size() == bridge_length) {
                    sum -= queue.poll();
                } else {
                    if (sum + truck_weight <= weight) {
                        queue.offer(truck_weight);
                        sum += truck_weight;
                        time++;
                        break;
                    }
                    
                    queue.offer(0);
                    time++;
                }
            }
        }
        
        return time + bridge_length;
    }
}
