import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[][] dp;

    public static void solution() {
        dp = new int[10001][4];

        dp[1] = new int[]{0, 1, 0, 0};
        dp[2] = new int[]{0, 1, 1, 0};
        dp[3] = new int[]{0, 1, 1, 1};

        for (int i = 1; i <= 10000; i++) {
            if (i > 3) {
                dp[i][1] = dp[i-1][1];
                dp[i][2] = dp[i-2][1] + dp[i-2][2];
                dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
            }
        }
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());

        int T;
        T = Integer.parseInt(st.nextToken());
        solution();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(dp[n][1] + dp[n][2] + dp[n][3]);
        }
    }
}