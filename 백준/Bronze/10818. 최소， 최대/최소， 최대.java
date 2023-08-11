import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1101 -> 3 2 0
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int N = Integer.parseInt(st.nextToken()); // 1~10
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (max < n) max = n;
            if (min > n) min = n;
        }

        System.out.println(min + " " + max);

    }

}