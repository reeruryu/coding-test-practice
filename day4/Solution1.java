import java.util.*;

class Solution1 {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        ArrayList<Integer>[] adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int[] e: edge) {
            adjList[e[0]].add(e[1]);
            adjList[e[1]].add(e[0]);
        }
        
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[1] = 0;
       
        for (int i = 1; i <= n; i++) {
            // 방문한 적 없고 최소 distance를 가진 노드 구하기
            int min = Integer.MAX_VALUE;
            int v = 0;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && min > distance[j]) {
                    min = distance[j];
                    v = j;
                }
            }
            
            // 인접 노드 거리 계산
            visited[v] = true;
            for (int j = 0; j < adjList[v].size(); j++) {
                int u = adjList[v].get(j);
                if (distance[u] > distance[v] + 1) {
                    distance[u] = distance[v] + 1;
                }
            }
        }
        
        int max = Arrays.stream(distance).max().getAsInt();
        for (int i = 1; i <= n; i++) {
            if (distance[i] == max) {
                answer++;
            }
        }
        
        return answer;
    }
}
