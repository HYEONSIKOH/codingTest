import java.util.*;
import java.io.*;

public class Main {
    private static boolean[][] arr;
    private static final boolean[][] temp = {
            {true, false, true, false, true, false, true, false},
            {false, true, false, true, false, true, false, true}
    };

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++)
                arr[i][j] = temp.charAt(j) == 'W';
        }

        System.out.println(solution());
    }

    private static int solution() {
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                ans = Math.min(bruteForce(i, j, 0), ans);
                ans = Math.min(bruteForce(i, j, 1), ans);
            }
        }

        return ans;
    }

    private static int bruteForce(int startX, int startY, int startTempIdx) {
        int cnt = 0;
        for (int i = startX; i < startX + 8; i++) {
            startTempIdx = 1 - startTempIdx;
            for (int j = startY; j < startY + 8; j++) {
                if (arr[i][j] == temp[startTempIdx][j - startY]) cnt++;
            }
        }

        return cnt;
    }
}