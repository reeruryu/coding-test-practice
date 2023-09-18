import java.util.*;
import java.io.*;

public class Main {
    static char[][] war;
    static boolean[][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, M;
    public static void main(String[] args) throws IOException {
        int cntW = 0, cntB = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 가로 (열)
        M = Integer.parseInt(st.nextToken()); // 세로 (행)
        war = new char[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            for (int j = 0; j < tmp.length(); j++) {
                war[i][j] = tmp.charAt(j);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    if (war[i][j] == 'W') {
                        cntW += bfs(i, j, 'W');
                    } else {
                        cntB += bfs(i, j, 'B');
                    }
                }
                
            }
        }

        System.out.println(cntW + " " + cntB);

    }

    public static int bfs(int x, int y, int color) {
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dirs) {
                int newX = cur[0] + d[0];
                int newY = cur[1] + d[1];

                if (newX < 0 || newX >= M || newY < 0 || newY >= N) continue;
                if (war[newX][newY] != color) continue;
                if (visited[newX][newY]) continue;

                queue.offer(new int[]{newX, newY});
                visited[newX][newY] = true;
                cnt++;

            }
        }

        return cnt * cnt;
    }
}
