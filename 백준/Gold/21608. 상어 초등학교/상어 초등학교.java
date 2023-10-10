import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] students;
    static Map<Integer, Set<Integer>> preferences;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        students = new int[N * N];
        preferences = new HashMap<>();

        StringTokenizer st;
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            students[i] = student;
            preferences.put(student, new HashSet<>());
            for (int j = 0; j < 4; j++) {
                preferences.get(student).add(Integer.parseInt(st.nextToken()));
            }
        }

        // 1. 학생들 자리 배치
        for (int student : students) {
            Seat seat = findSeat(student);
            map[seat.x][seat.y] = student;
        }

        // 2. 점수 합산
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = getStudentSum(i, j, map[i][j]);
                if (cnt > 0)
                    sum += Math.pow(10, cnt - 1);
            }
        }
        System.out.println(sum);

    }

    public static Seat findSeat(int student) {
        Seat seat = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) continue;

                Seat cur = new Seat(i, j, getStudentSum(i, j, student), getEmptySum(i, j));
                if (seat == null) {
                    seat = cur;
                    continue;
                }

                if (seat.compareTo(cur) > 0) seat = cur;

            }
        }

        return seat;
    }

    public static int getStudentSum(int x, int y, int student) {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 벗어나면 skip
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            // 인접한 학생이 좋아하는 학생에 포함되면 count 증가
            if (preferences.get(student).contains(map[nx][ny])) cnt++;

        }

        return cnt;
    }

    public static int getEmptySum(int x, int y) {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 벗어나면 skip
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            // 빈칸이면 count 증가
            if (map[nx][ny] == 0) cnt++;

        }

        return cnt;
    }
}

class Seat implements Comparable<Seat> {
    int x, y;
    int studentSum, emptySum;

    public Seat(int x, int y, int studentSum, int emptySum) {
        this.x = x;
        this.y = y;
        this.studentSum = studentSum;
        this.emptySum = emptySum;
    }


    @Override
    public int compareTo(Seat o) {
        if (studentSum != o.studentSum)
            return o.studentSum - this.studentSum;

        if (emptySum != o.emptySum)
            return o.emptySum - this.emptySum;

        if (this.x != o.x)
            return this.x = o.x;

        return this.y - o.y;
    }

}
