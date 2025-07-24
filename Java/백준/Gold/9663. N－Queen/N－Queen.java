import java.util.*;
import java.io.*;

public class Main {
    private static boolean[] column;
    private static boolean[] positive;
    private static boolean[] negative;
    private static int ans = 0;

    private static void solution(int depth, int N) {
        if (depth == N) {
            ans++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!column[i] && !positive[depth + i] && !negative[depth - i + N]) {
                column[i] = true;
                positive[depth + i] = true;
                negative[depth - i + N] = true;
                solution(depth + 1, N);
                column[i] = false;
                positive[depth + i] = false;
                negative[depth - i + N] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        column = new boolean[N];
        positive = new boolean[2 * N];
        negative = new boolean[2 * N];

        solution(0, N);
        bw.write(String.valueOf(ans));

        // System.gc();
        bw.flush();
        bw.close();
    }
}