import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> clouds;
    static int[][] dirs = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0} ,{1, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // init
        clouds = new ArrayList<>();
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 2, 1});
        clouds.add(new int[]{N - 1, 1});

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            boolean[][] visited = new boolean[N][N];
            ArrayList<int[]> tmpClouds = new ArrayList<>();
            for (int[] cloud : clouds) {
                // 1. 구름 이동
                int x = cloud[0];
                int y = cloud[1];

                int sn = s % N;
                int nx = (x + sn * dirs[d][0] + N) % N;
                int ny = (y + sn * dirs[d][1] + N) % N;

                // 2. 물 증가
                map[nx][ny]++;
                tmpClouds.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }

            // 3. 대각선 물이 있는 바구니 수만큼 증가 1, 3, 5, 7
            for (int[] tmpCloud : tmpClouds) {
                for (int k = 1; k < 8; k += 2) {
                    int x = tmpCloud[0];
                    int y = tmpCloud[1];

                    int nx = x + dirs[k][0];
                    int ny = y + dirs[k][1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    if (map[nx][ny] > 0) map[x][y]++;

                }
            }

            // 4. 새 구름 리스트 구하기
            clouds = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] >= 2 && !visited[j][k]) {
                        clouds.add(new int[]{j, k});
                        map[j][k] -= 2;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += map[i][j];
            }
        }

        System.out.println(answer);

    }

}
