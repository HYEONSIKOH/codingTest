import java.util.*;
import java.io.*;

import static java.lang.Integer.*;

public class Main {
    private static int[][] arr = new int[11][11];
    private static int ans = MIN_VALUE;

    private static int solution(int N) {
        int visited = 0;

        for (int i = 0; i < 11; i++) {
            if (arr[0][i] != 0)
                backtrack(0, visited | (1 << i), arr[0][i]);
        }

        return ans;
    }

    private static void backtrack(int idx, int visited, int sum) {
        if (visited == (1 << 11) - 1) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if ((visited & (1 << i)) == 0 && arr[idx + 1][i] != 0)
                backtrack(idx + 1, visited | (1 << i), sum + arr[idx + 1][i]);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = parseInt(br.readLine());

        while (N-- > 0) {
            ans = MIN_VALUE;
            
            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++)
                    arr[i][j] = parseInt(st.nextToken());
            }

            bw.write(solution(N) + "\n");
        }

        // System.gc();
        bw.flush();
        bw.close();
    }
}