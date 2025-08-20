import java.io.*;
import java.util.*;

public class Main {
    private static final double[] arr = new double[4];
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static boolean[][] visited;

    private static int N;
    private static double ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++)
            arr[i] = Double.parseDouble(st.nextToken()) / 100;

        System.out.print(String.valueOf(solution()));
    }

    private static double solution() {
        visited = new boolean[2 * N + 1][2 * N + 1];
        visited[N][N] = true;

        backtrack(0, N, N, 1.0);

        return ans;
    }

    private static void backtrack(int cnt, int x, int y, double totalProb) {
        if (cnt == N) {
            ans += totalProb;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[] nextPos = {x + dx[i], y + dy[i]};

            if (arr[i] != 0 && !visited[nextPos[0]][nextPos[1]]) {
                visited[nextPos[0]][nextPos[1]] = true;
                backtrack(cnt + 1, nextPos[0], nextPos[1], totalProb * arr[i]);
                visited[nextPos[0]][nextPos[1]] = false;
            }
        }
    }
}