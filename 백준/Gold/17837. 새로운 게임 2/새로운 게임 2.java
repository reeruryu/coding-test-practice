import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    
    static int n, k;
    static int[][] board;
    static HashMap<Integer, Horse> horses;
    static ArrayList<Integer>[][] list;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        list = new ArrayList[n][n];
        board = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                list[i][j] = new ArrayList<>();
            }
        }
        
        horses = new HashMap<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            horses.put(i + 1, new Horse(x - 1, y - 1, dir - 1));
            list[x - 1][y - 1].add(i + 1);
        }

        int ans = 0;
        outer:while (++ans <= 1000) {

            for (int i = 1; i <= k; i++) {
                Horse horse = horses.get(i);
                ArrayList<Integer> up_horse = new ArrayList<>();
                int start_idx = 0;
                int x = horse.x;
                int y = horse.y;
                
                // 위에 있는 말 정보 얻기
                for (int p = 0; p < list[horse.x][horse.y].size(); p++) {
                    if (list[horse.x][horse.y].get(p) == i) {
                        start_idx = p;
                        break;
                    }
                }

                for (int p = start_idx; p < list[horse.x][horse.y].size(); p++) {
                    up_horse.add(list[horse.x][horse.y].get(p));
                }

                // move
                int nx = horse.x + dx[horse.dir];
                int ny = horse.y + dy[horse.dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 2) {
                    nx -= dx[horse.dir];
                    ny -= dy[horse.dir];
                    // 방향 변경
                    int dir = (horse.dir % 2 == 0 ? horse.dir + 1 : horse.dir - 1);
                    nx += dx[dir];
                    ny += dy[dir];
                    horse.dir = dir;
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 2) continue;
                    if (board[nx][ny] == 1) {
                        for (int p = up_horse.size() - 1; p >= 0; p--) {
                            list[nx][ny].add(up_horse.get(p));
                            Horse hh = horses.get(up_horse.get(p));
                            horses.put(up_horse.get(p), new Horse(nx, ny, hh.dir));
                        }
                    } else {
                        for (int h : up_horse) {
                            list[nx][ny].add(h);
                            Horse hh = horses.get(h);
                            horses.put(h, new Horse(nx, ny, hh.dir));
                        }
                    }
                } else if (board[nx][ny] == 1) {
                    for (int p = up_horse.size() - 1; p >= 0; p--) {
                        list[nx][ny].add(up_horse.get(p));
                        Horse hh = horses.get(up_horse.get(p));
                        horses.put(up_horse.get(p), new Horse(nx, ny, hh.dir));
                    }
                } else if (board[nx][ny] == 0) {
                    for (int h : up_horse) {
                        list[nx][ny].add(h);
                        Horse hh = horses.get(h);
                        horses.put(h, new Horse(nx, ny, hh.dir));
                    }
                }

                if (list[nx][ny].size() >= 4) break outer;

                // 말 빼기
                for (int p = list[x][y].size() - 1; p >= start_idx; p--) {
                    list[x][y].remove(p);
                }


            }

        }

        System.out.println(ans > 1000 ? -1 : ans);
    }
}

class Horse {
    int x;
    int y;
    int dir;
    public Horse(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
