import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        int[] nj = new int[9];
        for (int i = 0; i < 9; i++) {
            nj[i] = Integer.parseInt(br.readLine());
            sum += nj[i];
        }
        Arrays.sort(nj);

        // 40
        // 7 8 10 13 15 19 20 23 25
        int hap = sum - 100;
        int left = 0, right = 8;
        while (left < right) {
            int cur = nj[left] + nj[right];
            if (cur == hap) break;
            else if (cur > hap) right--;
            else left++;
        }

        for (int i = 0; i < 9; i++) {
            if (i == left || i == right) continue;
            System.out.println(nj[i]);
        }

    }

}