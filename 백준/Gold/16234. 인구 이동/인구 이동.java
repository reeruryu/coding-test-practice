import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer = 0;
    static int N, L, R;
    static int[][] countries;
    static boolean[][] visited;
    static ArrayList<int[]> list;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        countries = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        System.out.println(answer);
    }

    public static void solution() {
        while (true) {
            boolean flag = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = open(i, j);
                        int num = sum / list.size();
                        if (list.size() > 1) {
                            for (int[] l : list) {
                                countries[l[0]][l[1]] = num;
                            }
                            flag = true;
                        }
                    }
                }
            }

            if (!flag) return;
            answer++;
        }
    }

    public static int open(int x, int y) {
        list = new ArrayList<>();

        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.offer(new int[]{x, y});
        list.add(new int[]{x, y});

        int sum = countries[x][y];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (!visited[nx][ny]) {
                    int gap = Math.abs(countries[nx][ny] - countries[cur[0]][cur[1]]);

                    if (gap >= L && gap <= R) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        list.add(new int[]{nx, ny});

                        sum += countries[nx][ny];
                    }
                }

            }

        }

        return sum;
    }
}
