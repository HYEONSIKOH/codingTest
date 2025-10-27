import java.util.*;
import java.io.*;

public class Main {
    private static int[][] minecraft;
    private static int N, M, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = readInt();
        M = readInt();
        B = readInt();
        minecraft = new int[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                minecraft[i][j] = readInt();

        int[] ans = solution();
        System.out.println(ans[0] + " " + ans[1]);
    }

    private static int[] solution() {
        int[] ans = {Integer.MAX_VALUE, Integer.MIN_VALUE };

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                min = Math.min(min, minecraft[i][j]);
                max = Math.max(max, minecraft[i][j]);
            }
        }

        for (int idx = min; idx <= max; idx++) {
            int time = 0, block = B;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (minecraft[i][j] > idx) {
                        block += minecraft[i][j] - idx;
                        time += 2 * (minecraft[i][j] - idx);
                    } else if (minecraft[i][j] < idx) {
                        block -= idx - minecraft[i][j];
                        time += idx - minecraft[i][j];
                    }
                }
            }

            if (block >= 0) {
                if (time < ans[0]) {
                    ans[0] = time;
                    ans[1] = idx;
                } else if (time == ans[0] && idx > ans[1]) {
                    ans[1] = idx;
                }
            }
        }

        return ans;
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}