import java.io.*;
import java.util.*;

public class Main {
    private static int[][] arr;
    private static int[][] visited;

    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {-1, 0, 1, 0};

    private static int N, M;
    private static int ans = Integer.MIN_VALUE;

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

        System.out.print(solution());
    }

    private static int solution() {
        visited = new int[N][M];

        backtrack(0);
        return ans;
    }

    private static void backtrack(int pos) {
        if (pos >= N * M) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    switch (visited[i][j]) {
                        case 1: sum += arr[i][j]; break;
                        case 2: sum += arr[i][j] * 2; break;
                    }
                }
            }
            ans = Math.max(ans, sum);
            return;
        }

        int x = pos / M;
        int y = pos % M;

        if (visited[x][y] == 0) {
            for (int d = 0; d < 4; d++) {
                int[] pos1 = {x + dx[d], y + dy[d]};
                int[] pos2 = {x + dx[(d + 1) % 4], y + dy[(d + 1) % 4]};

                if (isRanged(pos1) || isRanged(pos2)) continue;
                if (visited[pos1[0]][pos1[1]] != 0 || visited[pos2[0]][pos2[1]] != 0) continue;

                visited[x][y] = 2;
                visited[pos1[0]][pos1[1]] = 1;
                visited[pos2[0]][pos2[1]] = 1;

                backtrack(pos + 1);

                visited[x][y] = 0;
                visited[pos1[0]][pos1[1]] = 0;
                visited[pos2[0]][pos2[1]] = 0;
            }
        }

        backtrack(pos + 1);
    }

    private static boolean isRanged (int[] pos) {
        return pos[0] < 0 || pos[0] >= N || pos[1] < 0 || pos[1] >= M;
    }
}