import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new long[N][N];
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int moveLength = Integer.parseInt(st.nextToken());

                if (dp[i][j] > 0 && moveLength != 0) {
                    if (j + moveLength < N)
                        dp[i][j + moveLength] += dp[i][j];

                    if (i + moveLength < N)
                        dp[i + moveLength][j] += dp[i][j];
                }
            }

        }
        System.out.println(dp[N - 1][N - 1]);

    }
}
