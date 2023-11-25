import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer = 0;
    static boolean flag = false;
    static int[][] map, dp;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == 'H') map[i][j] = 10; // 구멍
                else map[i][j] = s.charAt(j) - '0';
            }
        }

        visited[0][0] = true;
        dfs(0, 0, 1);

        if (flag) System.out.println(-1);
        else System.out.println(answer);

    }

    static void dfs(int y, int x, int cnt) {
        // 0. 답 갱신
        answer = Math.max(answer, cnt);

        // 1. 탐색
        dp[y][x] = cnt;
        for (int i = 0; i < 4; i++) {
            int num = map[y][x];
            int ny = dy[i] * num + y;
            int nx = dx[i] * num + x;

            if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 10) continue;
            if (visited[ny][nx]) {
                flag = true;
                return;
            }
            if (dp[ny][nx] > cnt) continue;
            
            visited[ny][nx] = true;
            dfs(ny, nx, cnt + 1);
            visited[ny][nx] = false;
            
        }
    }
}