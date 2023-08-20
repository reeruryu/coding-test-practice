import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];
        for (int i = 1; i <= V ; i++) {
            parent[i] = i;
        }
        
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new int[]{u, v, w});
        }

        edges.sort(Comparator.comparingInt(arr -> arr[2])); // 간선 크기로 오름차순 정렬

        int sum = 0;
        for (int[] edge : edges) {
            int u = findUnion(edge[0]);
            int v = findUnion(edge[1]);

            if (u != v) {
                sum += edge[2];
                parent[u] = v;
            }
        }

        System.out.println(sum);
    }

    static int findUnion(int a) {
        if (parent[a] == a) return a;
        return parent[a] = findUnion(parent[a]);
    }
}