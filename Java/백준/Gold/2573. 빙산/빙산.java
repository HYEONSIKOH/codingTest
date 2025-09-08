import java.io.*;
import java.util.*;

public class Main {
    private static int[][] arr;
    private static boolean[][] visited;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

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

        System.out.println(solution());
    }

    private static int solution() {
        int ans = 0;

        while (true) {
            visited = new boolean[N][M];
            int cnt = 0;

            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (arr[n][m] != 0 && !visited[n][m]) {
                        dfs(n, m);
                        cnt++;
                    }
                }
            }

            if (cnt >= 2) return ans;
            else if (cnt == 0) return 0;
            else ans++;
        }
    }

    private static void dfs(int x, int y) {
        int cnt = 0;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny) && arr[nx][ny] == 0 && !visited[nx][ny]) cnt++;
        }

        arr[x][y] = Math.max(arr[x][y] - cnt, 0);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isValid(nx, ny) || visited[nx][ny] || arr[nx][ny] == 0) continue;
            dfs(nx, ny);
        }
    }

    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }
}