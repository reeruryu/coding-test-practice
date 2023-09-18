import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static boolean[][] food;
    static boolean[][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 개수
        food = new boolean[N][M];
        visited  = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            food[r - 1][c - 1] = true;
        }

        int max = 0;
        for (int i = 0; i < food.length; i++) {
            for (int j = 0; j < food[0].length; j++) {
                if (!visited[i][j] && food[i][j]) {
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        System.out.println(max);

    }

    public static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] d : dirs) {
                int newX = cur[0] + d[0];
                int newY = cur[1] + d[1];

                if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;
                if (!food[newX][newY] || visited[newX][newY]) continue;

                queue.offer(new int[]{newX, newY});
                visited[newX][newY] = true;
                cnt++;

            }
        }

        return cnt;
    }
}