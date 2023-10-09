import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] A; // 내구도
    static boolean[] robot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸의 개수가 K개 이상이라면 과정 종료
        A = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(simulation(0));

    }

    public static int simulation(int cnt) {
        while (!isDone()) {
            // 1. 벨트 회전 (로봇도)
            int tmp = A[2 * N - 1];
            for (int i = 2 * N - 1; i > 0; i--) {
                A[i] = A[i - 1];
            }
            A[0] = tmp;

            for (int i = N - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;

            // 2. 로봇 이동
            robot[N - 1] = false;
            for (int i = N - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && A[i] >= 1) {
                    robot[i] = true;
                    robot[i - 1] = false;
                    A[i]--;
                }
            }

            // 3. 로봇 올리기
            if (A[0] != 0) {
                robot[0] = true;
                A[0]--;
            }

            cnt++;
        }

        return cnt;
    }

    public static boolean isDone() {
        int cnt = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (A[i] == 0) cnt++;
            if (cnt >= K) return true;
        }

        return false;
    }
}
