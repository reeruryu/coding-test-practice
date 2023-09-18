import java.io.*;
import java.util.*;

public class Main {

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        boolean[][] visited = new boolean[N][M];
        int[][] miro = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            int j = 0;
            for (String s: tmp.split("")) {
                miro[i][j++] = Integer.parseInt(s);
            }
        }
        
        
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            
            for (int[] d: dirs) { // 11
                int newX = xy[0] + d[0];
                int newY = xy[1] + d[1];
                
                if (newX < 0 || newY < 0 || newX > N - 1 || newY > M - 1) {
                    continue;
                }
                
                if (!visited[newX][newY] && miro[newX][newY] != 0) {
                    queue.offer(new int[]{newX, newY});
                    miro[newX][newY] = miro[xy[0]][xy[1]] + 1;
                    visited[newX][newY] = true;
                }
            }
   
        }
        
        System.out.println(miro[N - 1][M - 1]);
        
	}

}
