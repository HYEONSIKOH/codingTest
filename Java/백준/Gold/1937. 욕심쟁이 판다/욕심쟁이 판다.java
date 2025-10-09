import java.util.*;
import java.io.*;

public class Main {
    private static int[][] map;
    private static int[][] dp;

    private final static int[] dx = {-1, 1, 0, 0};
    private final static int[] dy = {0, 0, -1, 1};

    private static int N = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st /*= new StringTokenizer(br.readLine())*/;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    private static int solution() {
        dp = new int[N][N];

        int ans = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(dfsWithDp(i, j), ans);
            }
        }

        return ans;
    }

    private static int dfsWithDp(int x, int y) {
        if (dp[x][y] != 0)
            return dp[x][y];

        dp[x][y] = 1; // 자기 자신
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isVaild(nx, ny)) continue;
            if (map[x][y] < map[nx][ny])
                dp[x][y] = Math.max(dp[x][y], 1 + dfsWithDp(nx, ny));
        }

        return dp[x][y];
    }

    private static boolean isVaild(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}