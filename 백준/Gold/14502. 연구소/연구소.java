import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static int N, M;
    static int[][] region;
//    static boolean[][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        region = new int[N][M];
//        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                region[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        wall(0);
        System.out.println(answer);


    }

    public static void wall(int cnt) {
        if (cnt == 3) {

            // copy
            int[][] tmp = new int[N][M];
            for (int i = 0; i < N; i++) {
                tmp[i] = region[i].clone();
            }

            // virus 퍼뜨리기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tmp[i][j] == 2) {
                        virus(tmp, i, j);
                    }
                }
            }

            // 안전영역 크기 세기
            int ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tmp[i][j] == 0) ans++;
                }
            }
            answer = Math.max(ans, answer);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (region[i][j] == 0) {
                    region[i][j] = 1;
                    wall(cnt + 1);
                    region[i][j] = 0;
                }
            }
        }

    }

    public static void virus(int[][] tmp, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
//        tmp[x][y] = 3;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
//                if (tmp[nx][ny] == 1) continue;
                if (tmp[nx][ny] == 0) {
                    tmp[nx][ny] = 3;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}