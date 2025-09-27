import java.util.*;
import java.io.*;

public class Main {
    private static int[][][] tomato;

    private static Deque<int[]> q = new ArrayDeque<>();

    private static final int[] dx = {1, -1, 0, 0, 0, 0};
    private static final int[] dy = {0, 0, 1, -1, 0, 0};
    private static final int[] dz = {0, 0, 0, 0, 1, -1};

    private static int N, M, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomato = new int[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    tomato[h][n][m] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(solution());
    }

    private static int solution() {
        // 토마토 위치 찾기
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomato[h][n][m] == 1) q.add(new int[]{h, n, m});
                }
            }
        }

        // 익히기
        int cnt = ripe();

        // 안익은 토마토가 있는지 찾기
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomato[h][n][m] == 0) return -1;
                }
            }
        }

        return cnt == -1 ? 0 : cnt;
    }

    private static int ripe() {
        int ans = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int dir = 0; dir < 6; dir++) {
                int nz = cur[0] + dz[dir];
                int nx = cur[1] + dx[dir];
                int ny = cur[2] + dy[dir];

                if (!isVaild(nz, nx, ny) || tomato[nz][nx][ny] != 0) continue;

                tomato[nz][nx][ny] = tomato[cur[0]][cur[1]][cur[2]] + 1;
                ans = Math.max(ans, tomato[nz][nx][ny]);
                q.add(new int[]{nz, nx, ny});
            }
        }

        return ans - 1;
    }

    private static boolean isVaild(int z, int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M && z >= 0 && z < H;
    }
}