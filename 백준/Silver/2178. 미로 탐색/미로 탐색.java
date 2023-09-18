import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] miro;
    static boolean[][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        miro = new int[N][M];
        visited  = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < tmp.length(); j++) {
                miro[i][j] = tmp.charAt(j) - '0';
            }
        }
        bfs();
    }

    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == N - 1 && cur[1] == M - 1) {
                System.out.println(miro[cur[0]][cur[1]]);
                return;
            }

            for (int[] d : dirs) {
                int newX = cur[0] + d[0];
                int newY = cur[1] + d[1];

                if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;
                if (miro[newX][newY] <= 0 || visited[newX][newY]) continue;

                miro[newX][newY] = miro[cur[0]][cur[1]] + 1;
                queue.offer(new int[]{newX, newY});
                visited[newX][newY] = true;

            }
        }
    }
}
