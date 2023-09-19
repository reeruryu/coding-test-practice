import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 2][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 기간
            arr[i][1] = Integer.parseInt(st.nextToken()); // 금액
        }

        int max = -1;
        int[] dp = new int[N + 2];
        for (int i = 1; i <= N + 1; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }

            int k = i + arr[i][0];
            if (k <= N + 1) {
                dp[k] = Math.max(dp[k], max + arr[i][1]);
            }

        }

        System.out.println(max);

    }
}