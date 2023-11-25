import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int res = -1;
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static Set<Character> set = new HashSet<>();
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }


        visited[0][0] = true;
        set.add(map[0][0]);
        dfs(0, 0);
        System.out.println(res);
    }

    public static void dfs(int x, int y) {
        // 0. 답 갱신
        res = Math.max(res, set.size());

        // 1. 주변 탐색
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
            if (!visited[nx][ny] && !set.contains(map[nx][ny])) {
                visited[nx][ny] = true;
                set.add(map[nx][ny]);
                dfs(nx, ny);
                visited[nx][ny] = false;
                set.remove(map[nx][ny]);
            }
        }
    }
}
