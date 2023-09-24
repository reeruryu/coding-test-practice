import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, L;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0}; // 동 남 서 북
    static List<int[]> snake = new ArrayList<>();
    static HashMap<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 보드의 크기
        K = Integer.parseInt(br.readLine()); // 사과의 개수

        board = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            board[row - 1][col - 1] = 1;
        }

        L = Integer.parseInt(br.readLine()); // 방향 변환 횟수
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        System.out.println(crawl());

    }

    public static int crawl() {
        int time = 0;
        int cx = 0, cy = 0;
        int d = 0;
        snake.add(new int[]{0, 0});

        while (true) {
            // 1. 시간 재기
            time++;

            // 2. 다음 위치
            int nx = cx + dx[d];
            int ny = cy + dy[d];

            // 3. 종료 구간 체크
            if (isFinish(nx, ny)) break;

            // 4. 사과 유무
            if (board[nx][ny] == 1) board[nx][ny] = 0;
            else snake.remove(0);

            // 5. 뱀 이동
            snake.add(new int[]{nx, ny});

            // 6. 방향 체크
            if (map.containsKey(time)) {
                if (map.get(time).equals("D")) { // 오른쪽
                    d += 1;
                    if (d == 4) d = 0;
                } else { // 왼쪽
                    d -= 1;
                    if (d == -1) d = 3;
                }
            }

            // 7. 머리 업데이트
            cx = nx;
            cy = ny;

        }

        return time;
    }

    private static boolean isFinish(int nx, int ny) {
        if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) return true;
        for (int[] sn : snake) {
            if (nx == sn[0] && ny == sn[1]) return true;
        }
        return false;
    }
}
