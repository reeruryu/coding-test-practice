import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1101 -> 3 2 0
        int T = Integer.parseInt(br.readLine()); // 1~10
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            int idx = 0;
            while (n > 0) {
                if (n % 2 == 1) System.out.print(idx + " ");
                n /= 2;
                idx++;
            }
            System.out.println();
        }

    }

}