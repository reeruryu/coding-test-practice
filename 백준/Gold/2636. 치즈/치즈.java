import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;

    static int[][] map;
    static boolean[][] visited;

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cnt++;
            }
        }

        int preCnt = cnt;
        int time = 0;
        while (true) {
            time++;
            visited = new boolean[n][m];
            int remove = bfs(new Location(0, 0));
            cnt -= remove;

            if (cnt == 0) break;
            else preCnt = cnt;
        }

        System.out.println(time);
        System.out.println(preCnt);

    }

    static int bfs(Location location) {
        ArrayList<Location> removeList = new ArrayList<>();
        Queue<Location> queue = new LinkedList<>();
        queue.offer(location);
        visited[location.x][location.y] = true;

        while (!queue.isEmpty()) {
            Location current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;

                if (map[nx][ny] == 1) { // 모서리 치즈 발견
                    visited[nx][ny] = true;
                    removeList.add(new Location(nx, ny));
                } else if (map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.add(new Location(nx, ny));
                }
            }

        }

        // 치즈 녹이기
        int size = removeList.size();
        for (Location l : removeList) {
            map[l.x][l.y] = 0;
        }

        return size;
    }

    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x= x;
            this.y = y;
        }
    }
}
