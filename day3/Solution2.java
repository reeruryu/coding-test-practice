import java.util.*;

class Solution2 {
    static Set<Integer>[] set = new HashSet[9];
    
    public int solution(int N, int number) {
        
        int n = 0;
        for (int i = 1; i < 9; i++) {
            n = n * 10 + N;
            set[i] = new HashSet<>();
            set[i].add(n);
        }
        
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < i; j++) {
                for (int a: set[j]) {
                    for (int b: set[i - j]) {
                        set[i].add(a + b);
                        set[i].add(a - b);
                        set[i].add(a * b);
                        if (a != 0) {
                            set[i].add(b / a);
                        }
                        if (b != 0) {
                            set[i].add(a / b);
                        }
                    }
                }
            }
            
            if (set[i].contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}
