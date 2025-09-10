import java.io.*;
import java.util.*;

public class Main {
    private static int[][] arr;
    private static int[][] info;
    private static boolean[][] visited;

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    private static int N, M;

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

        System.out.println(solution(0,0));
    }

    private static int solution(int x, int y) {
        Deque<int[]> q = new ArrayDeque<>();
        int ans = 0;

        while (true) {
            int cnt = 0;
            for (int[] n: arr) {
                for (int m: n) {
                    if (m == 1) cnt++;
                }
            }

            if (cnt == 0) return ans;
            else ans++;

            info = new int[N][M];
            visited = new boolean[N][M];
            q.offer(new int[] {x, y});

            // 외부공기 찾기 (외부공기: 1)
            dfs(x, y);

            // 치즈 찾기 (치즈: 2)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 1) dfs(i, j);
                }
            }

            // 전체 - 외부공기 - 치즈 = 내부공기 (내부공기: 3)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (info[i][j] == 0) info[i][j] = 3;
                }
            }

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                visited[cur[0]][cur[1]] = true;
                int outAirCnt = 0;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];

                    if (!isValid(nx, ny)) continue;
                    if (info[cur[0]][cur[1]] == 2 && info[nx][ny] == 1) outAirCnt++;

                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                }

                if (outAirCnt > 1)
                    info[cur[0]][cur[1]] = 4;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (info[i][j] == 4)
                        arr[i][j] = 0;
                }
            }
        }
    }

    private static void dfs(int x, int y) {
        int posNum = arr[x][y];
        info[x][y] = posNum + 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny) && info[nx][ny] == 0 && arr[nx][ny] == posNum) {
                if (posNum == 0) info[nx][ny] = 1;
                if (posNum == 1) info[nx][ny] = 2;
                dfs(nx, ny);
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }
}