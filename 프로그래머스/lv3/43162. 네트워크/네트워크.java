import java.util.*;

class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i]) {
                bfs(computers, n, i);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void bfs(int[][] computers, int n, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int i = 0; i < n; i++) {
                if (computers[cur][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}