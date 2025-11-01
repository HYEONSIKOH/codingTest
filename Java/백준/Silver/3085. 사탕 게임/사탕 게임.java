import java.util.*;
import java.io.*;

public class Main {
    private static char[][] candies;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        candies = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            candies[i] = line.toCharArray();
        }

        System.out.print(solution());
    }

    private static int solution() {
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                ans = Math.max(ans, checkMax(new int[]{i, j}));
        }

        if (ans == N) return N;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d = 0; d < 2; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (isVaild(nx, ny) && candies[i][j] != candies[nx][ny]) {
                        swap(new int[]{i, j}, new int[]{nx, ny});
                        ans = Math.max(ans, Math.max(checkMax(new int[]{i, j}), checkMax(new int[]{nx, ny})));
                        swap(new int[]{i, j}, new int[]{nx, ny});
                    }
                }
            }
        }

        return ans;
    }

    private static int checkMax(int[] p) {
        int X = p[0], Y = p[1];
        char c = candies[X][Y];

        int cnt = 1;
        for (int i = Y + 1; i < N; i++) {
            if (candies[X][i] == c) cnt++;
            else break;
        }

        for (int i = Y - 1; i >= 0; i--) {
            if (candies[X][i] == c) cnt++;
            else break;
        }

        int cnt2 = 1;
        for (int i = X + 1; i < N; i++) {
            if (candies[i][Y] == c) cnt2++;
            else break;
        }

        for (int i = X - 1; i >= 0; i--) {
            if (candies[i][Y] == c) cnt2++;
            else break;
        }

        cnt = cnt == 1 ? 0: cnt;
        cnt2 = cnt2 == 1 ? 0: cnt2;

        return Math.max(cnt, cnt2);
    }

    private static void swap(int[] p1, int[] p2) {
        char temp = candies[p1[0]][p1[1]];
        candies[p1[0]][p1[1]] = candies[p2[0]][p2[1]];
        candies[p2[0]][p2[1]] = temp;
    }

    private static boolean isVaild(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}