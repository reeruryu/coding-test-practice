import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] d = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    static private class Position {
        int x, y, wall, cnt;

        public Position(int x, int y, int wall, int cnt) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        boolean[][][] visited = new boolean[2][N][M]; // 0은 벽 안 부수고, 1은 벽 부수고 이동

        Queue<Position> q = new LinkedList<>();
        q.add(new Position(0, 0, 0, 1)); // 시작
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Position cur = q.poll();

            if (cur.x == N - 1 && cur.y == M - 1) return cur.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + d[i][0];
                int ny = cur.y + d[i][1];
                int cnt = cur.cnt;
                int wall = cur.wall;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[wall][nx][ny]) continue;

                if (map[nx][ny] == 0) {
                    visited[wall][nx][ny] = true;
                    q.add(new Position(nx, ny, wall, cnt + 1));
                } else if (map[nx][ny] == 1 && wall == 0) { // 1이라면 벽을 부수지 않은 경우만 이동
                    visited[wall][nx][ny] = true;
                    q.add(new Position(nx, ny, wall + 1, cnt + 1));
                }
            }
        }

        return -1; // 이동 불가능
    }
}
