import java.util.*;

class Solution1 {
    public int solution(int[][] triangle) {

        int i;
        for (i = 0; i < triangle.length - 1; i++) {
            for (int j = 0; j < triangle[i + 1].length; j++) {
                int max = 0;
                if (j == 0) {
                    max = triangle[i][j];
                } else if (j == triangle[i + 1].length - 1) {
                    max = triangle[i][j - 1];
                } else {
                    max = Math.max(triangle[i][j - 1], triangle[i][j]);
                }
                triangle[i + 1][j] += max; 
            }
        }
        
        return Arrays.stream(triangle[i]).max().getAsInt();        
    }
}
