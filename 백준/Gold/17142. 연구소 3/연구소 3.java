import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer = Integer.MAX_VALUE;
    static int emptyCnt;
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] activeVirus;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 바이러스 개수
        map = new int[N][N];

        emptyCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    list.add(new int[]{i, j});
                } else if (map[i][j] == 0) {
                    emptyCnt++;
                }
            }
        }

        activeVirus = new boolean[list.size()];
        if (emptyCnt == 0) {
            System.out.println(0);
            return;
        }

        dfs(0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    // 활성화 바이러스 조합
    public static void dfs(int cnt, int idx) {
        if (cnt == M) {
            int res = bfs();
            if (res != -1) answer = Math.min(answer, res);
            return;
        }

        for (int i = idx; i < list.size(); i++) {
            if (!activeVirus[i]) {
                // 활성화 3으로
                activeVirus[i] = true;
                int[] xy = list.get(i);
                map[xy[0]][xy[1]] = 3;

                // dfs
                dfs(cnt + 1, i);

                // 원상태로 back
                activeVirus[i] = false;
                map[xy[0]][xy[1]] = 2;
            }
        }
    }

    // 활성화 바이러스 조합
    public static int bfs() {
        // visited
        visited = new boolean[N][N];
        int cnt = emptyCnt;

        // active 큐에 넣기
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if (activeVirus[i]) {
                int x = list.get(i)[0];
                int y = list.get(i)[1];

                queue.offer(new int[]{x, y});
                visited[x][y] = true;
            }
        }

        // 큐 돌리기
        int time = 0;
        while (!queue.isEmpty()) {
            boolean flag = false;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] peek = queue.poll();
                int x = peek[0];
                int y = peek[1];

                // 퍼지기
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    // 벗어났을 때
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    // 벽이거나 이미 방문했을 때
                    if (map[nx][ny] == 1 || visited[nx][ny]) continue;

                    // 빈 칸이었을 때만 flag true
                    if (map[nx][ny] == 0 || map[nx][ny] == 2) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        if (map[nx][ny] == 0) cnt--;
                    }
                    
                }

            }
            // 시간 증가
            time++;
            if (cnt == 0) break;
        }

        if (cnt != 0) return -1;
        return time;
    }
}

