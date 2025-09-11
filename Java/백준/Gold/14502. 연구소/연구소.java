import java.io.*;
import java.util.*;

public class Main {
    private static int[][] arr;
    private static boolean[][] visited;

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    private static int N, M, ans = Integer.MIN_VALUE;

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
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    backTrack(calIdx(i, j), 1);
                    arr[i][j] = 0;
                }
            }
        }

        return ans;
    }

    private static void backTrack(int idx, int depth) {
        if (depth == 3) {
            int[][] temp = new int[N][M];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    temp[i][j] = arr[i][j];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 2 && !visited[i][j])
                        spreadDfs(i, j);
                }
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 0) cnt++;
                    arr[i][j] = temp[i][j];
                    visited[i][j] = false;
                }
            }

            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = idx; i < N * M; i++) {
            int x = i / M;
            int y = i % M;

            if (arr[x][y] != 0) continue;

            arr[x][y] = 1;
            backTrack(i + 1, depth + 1);
            arr[x][y] = 0;
        }
    }

    private static void spreadDfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isValid(nx, ny) || arr[nx][ny] != 0 || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            arr[nx][ny] = 2;
            spreadDfs(nx, ny);
        }
    }

    private static int calIdx(int x, int y) {
        return x * M + y;
    }

    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }
}