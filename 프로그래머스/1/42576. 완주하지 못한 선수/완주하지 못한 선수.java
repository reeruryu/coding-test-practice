import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        
        for (String c : completion) {
            int n = map.get(c);
            if (n == 1) map.remove(c);
            else map.put(c, n - 1);
        }
        
        for (String k : map.keySet()) answer = k;
        return answer;

    }
}