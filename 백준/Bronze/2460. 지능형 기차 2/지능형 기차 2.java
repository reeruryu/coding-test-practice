import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int out = Integer.parseInt(st.nextToken());
            int in = Integer.parseInt(st.nextToken());
            sum = sum - out + in;
            if (sum >= 10000) {
                System.out.println(10000);
                return;
            }
            if (sum > max) max = sum;
        }
        System.out.println(max);

    }

}