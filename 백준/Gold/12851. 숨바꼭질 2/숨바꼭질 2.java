import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
     static int cnt = 0;
    static int time = Integer.MAX_VALUE;
    static int N, K;
    static int[] visited = new int[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println((N - K) + "\n1");
            return;
        }

        bfs();

        System.out.println(time + "\n" + cnt);
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        visited[N] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (time < visited[cur]) return;

            for (int i = 0; i < 3; i++) {
                int next;
                if (i == 0) next = cur + 1;
                else if (i == 1) next = cur - 1;
                else next = cur * 2;

                if (next < 0 || next > 100000) continue;

                if (next == K) {
                    time = visited[cur];
                    cnt++;
                }

                if (visited[next] == 0 || visited[next] == visited[cur] + 1) {
                    queue.offer(next);
                    visited[next] = visited[cur] + 1;
                }
            }
        }
    }

}