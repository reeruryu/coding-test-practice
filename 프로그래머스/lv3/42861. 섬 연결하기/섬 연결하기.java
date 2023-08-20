import java.util.*;

class Solution {
    
    static int[] island;
	public static int solution(int n, int[][] costs) {
		int answer = 0;		
		Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
		island = new int[n];
		for (int i = 0; i < n; i++) {
            island[i] = i;
        }
        
		for (int[] cost: costs) {
			int u = findUnion(cost[0]);
			int v = findUnion(cost[1]);
            System.out.println(cost[0] +" "+u+", "+cost[1]+" "+v);
			if (u != v) {
				answer += cost[2];
				island[u] = v;
			}
				
		}
        
		return answer;
	}
    
    public static int findUnion(int u) {
		if (island[u] == u) return u;
		return island[u] = findUnion(island[u]);
	}
}