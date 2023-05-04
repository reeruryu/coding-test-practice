import java.util.*;

class Solution2 {
    static HashSet<Integer> set = new HashSet<>();
    static boolean[] checked = new boolean[7];
    
    public int solution(String numbers) {
        int answer = 0;
        for(int i = 0; i < numbers.length(); i++) {
            dfs(numbers,"",i + 1);
        }
        
        for (int num: set) {
            if (isPrime(num)) answer++;
        }
        
        return answer;
  
    }
    
	// 백트래킹
	static void dfs(String str, String temp, int m) {
        if (temp.length() == m) {
            int num = Integer.parseInt(temp);
            set.add(num);
        }
        
        for (int i = 0; i < str.length(); i++) {
            if (!checked[i]) {
                checked[i] = true;
                temp += str.charAt(i);
                dfs(str, temp, m);
                checked[i] = false;
                temp = temp.substring(0, temp.length() - 1);
            }
        }
		
	}
	
	public boolean isPrime(int n) {
		if (n < 2) return false;
		
		for(int i = 2; i * i <= n; i++) {
			if(n % i == 0) return false;
		}
		
		return true;
	}

}
