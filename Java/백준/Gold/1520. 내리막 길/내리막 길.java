import java.util.*;
import java.io.*;

public class Main {
    private static int[][] map;
    private static int[][] dp;

    private final static int[] dx = {-1, 1, 0, 0};
    private final static int[] dy = {0, 0, -1, 1};

    private static int M, N, ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    private static int solution() {
        dp = new int[M][N];
        for (int i = 0; i < M; i++)
            Arrays.fill(dp[i], -1);

        return dfsWithDp(0, 0);
    }

    private static int dfsWithDp(int x, int y) {
        if (x == M - 1 && y == N - 1)
            return 1;

        if (dp[x][y] != -1)
            return dp[x][y];

        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isVaild(nx, ny) || map[nx][ny] >= map[x][y]) continue;

            dp[x][y] += dfsWithDp(nx, ny);
        }

        return dp[x][y];
    }

    private static boolean isVaild(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}