import java.util.*;
import java.io.*;

public class Main {
    private static int[][] tomato;

    private static Deque<int[]> q = new ArrayDeque<>();

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        tomato = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                tomato[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    private static int solution() {
        // 토마토 위치 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomato[i][j] == 1) q.add(new int[]{i, j});
            }
        }

        // 익히기
        int cnt = ripe();

        // 안익은 토마토가 있는지 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomato[i][j] == 0) return -1;
            }
        }

        return cnt == -1 ? 0 : cnt;
    }

    private static int ripe() {
        int ans = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if (!isVaild(nx, ny) || tomato[nx][ny] != 0) continue;

                tomato[nx][ny] = tomato[cur[0]][cur[1]] + 1;
                ans = Math.max(ans, tomato[nx][ny]);
                q.add(new int[]{nx, ny});
            }
        }

        return ans - 1;
    }

    private static boolean isVaild(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}