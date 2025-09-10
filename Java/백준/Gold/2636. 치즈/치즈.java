import java.io.*;
import java.util.*;

public class Main {
    private static int[][] arr;

    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {1, 0, -1, 0};

    private static int N, M, ans1, ans2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solution();

        System.out.println(ans1);
        System.out.print(ans2);
    }

    private static void solution() {
        int[][] visited;
        Deque<int[]> q = new ArrayDeque<>();

        while (true) {
            int cnt = 0;
            for (int[] n: arr) {
                for (int m: n) {
                    if (m == 1) cnt++;
                }
            }

            if (cnt == 0) break;
            else {
                ans1++;
                ans2 = cnt;
            }

            visited = new int[N][M];
            q.offer(new int[] {0, 0});
            visited[0][0] = 1;

            while (!q.isEmpty()) {
                int[] cur = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];

                    if (!isValid(nx, ny) || visited[nx][ny] != 0) continue;

                    if (arr[nx][ny] == 0) {
                        visited[nx][ny] = 1;
                        q.offer(new int[] {nx, ny});
                    } else if (arr[nx][ny] == 1)
                        visited[nx][ny] = 2;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] == 2)
                        arr[i][j] = 0;
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }
}