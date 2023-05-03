import java.util.*;

// 1, 2, 3, 4, 5, ...
// 2, 1, 2, 3, 2, 4, 2, 5, ...
// 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
class Solution1 {
    public int[] solution(int[] answers) {
        
        int[] person1 = {1, 2, 3, 4, 5};
        int[] person2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] person3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] cnt = new int[3];
        int idx1 = 0, idx2 = 0, idx3 = 0;
        for (int answer: answers) {
            if (answer == person1[idx1]) { cnt[0]++; }
            if (answer == person2[idx2]) { cnt[1]++; }
            if (answer == person3[idx3]) { cnt[2]++; }
            
            idx1 = (idx1 + 1) % (person1.length);
            idx2 = (idx2 + 1) % (person2.length);
            idx3 = (idx3 + 1) % (person3.length);
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        int max = Arrays.stream(cnt).max().getAsInt();
        for (int i = 0; i < cnt.length; i++) {
            if (max == cnt[i]) {
                result.add(i + 1);
            }
        }
        
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        
        return res;
    }
}
