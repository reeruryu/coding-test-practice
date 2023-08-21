import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> used = new HashSet<>();
        int cnt = 0;

        for (int i = 0; i < K; i++) {
            // 이미 사용 중인 경우, 무시
            if (used.contains(arr[i])) {
                continue;
            }

            // 멀티탭에 빈 자리가 있는 경우
            if (used.size() < N) {
                used.add(arr[i]);
            } else {
                int idxToRemove = -1;
                int farthestUsed = -1;
                
                // 이미 꽂혀 있는 콘센트 중에서 나중에 사용되거나 사용되지 않는 콘센트 찾기
                for (int plug : used) {
                    int nextUse = -1;
                    for (int j = i + 1; j < K; j++) {
                        if (arr[j] == plug) {
                            nextUse = j;
                            break;
                        }
                    }
                    if (nextUse == -1) {
                        idxToRemove = plug;
                        break;
                    }
                    if (nextUse > farthestUsed) {
                        farthestUsed = nextUse;
                        idxToRemove = plug;
                    }
                }
                used.remove(idxToRemove);
                used.add(arr[i]);
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
