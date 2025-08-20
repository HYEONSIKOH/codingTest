import java.io.*;
import java.util.*;

public class Main {
    private static final double[] arr = new double[4];
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

    private static int[] calPosWithIdx(int idx) {
        // 0: 동, 1: 서, 2: 남, 3: 북
        switch (idx) {
            case 0 : return new int[]{1, 0};  // 동
            case 1 : return new int[]{-1, 0}; // 서
            case 2 : return new int[]{0, 1};  // 남
            case 3 : return new int[]{0, -1}; // 북
            default : return new int[]{-1, -1};
        }
    }

    private static double solution() {
        visited = new boolean[2 * N + 1][2 * N + 1];
        visited[N][N] = true;

        for (int i = 0; i < 4; i++) {
            if (arr[i] != 0) {
                int[] pos = calPosWithIdx(i);

                visited[N + pos[0]][N + pos[1]] = true;
                backtrack(1, N + pos[0], N + pos[1], arr[i]);
                visited[N + pos[0]][N + pos[1]] = false;
            }
        }

        return ans;
    }

    private static void backtrack(int cnt, int x, int y, double totalProb) {
        if (cnt == N) {
            ans += totalProb;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[] pos = calPosWithIdx(i);
            int[] nextPos = {x + pos[0], y + pos[1]};

            if (arr[i] != 0 && !visited[nextPos[0]][nextPos[1]]) {
                visited[nextPos[0]][nextPos[1]] = true;
                backtrack(cnt + 1, nextPos[0], nextPos[1], totalProb * arr[i]);
                visited[nextPos[0]][nextPos[1]] = false;
            }
        }
    }
}