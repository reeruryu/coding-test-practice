import java.util.*;

class Solution4 {  
    static ArrayList<String> list = new ArrayList<>();
    static boolean visited[];
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        
        dfs(tickets, "ICN", "ICN", 0);
        Collections.sort(list);
        
        return list.get(0).split(" ");
    }
    
    public void dfs(String[][] tickets, String now, String path, int depth) {
        if (depth == tickets.length) {
            list.add(path);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(now)) {
                visited[i] = true;
                dfs(tickets, tickets[i][1], path + " " + tickets[i][1], depth + 1);
                visited[i] = false;
            }
        }
    }        
}
