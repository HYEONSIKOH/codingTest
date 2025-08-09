import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[][] chickenShop;
    private static int[][] homes;
    private static int[][] arr;
    private static int[][] dist;

    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solution(N, M);

        System.out.println(ans);
    }

    private static void solution(int N, int M) {
        chickenShop = new int[13][2];
        homes = new int[2 * N][2];

        int chickenShopCnt = 0;
        int homeCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 2) chickenShop[chickenShopCnt++] = new int[]{i, j};
                if (arr[i][j] == 1) homes[homeCnt++] = new int[]{i, j};
            }
        }

        dist = new int[chickenShopCnt][homeCnt];
        for (int i = 0; i < chickenShopCnt; i++) {
            for (int j = 0; j < homeCnt; j++) {
                dist[i][j] = Math.abs(chickenShop[i][0] - homes[j][0]) + Math.abs(chickenShop[i][1] - homes[j][1]);
            }
        }

        for (int i = 0; i < chickenShopCnt; i++)
            backtrack(i, 1, M, chickenShopCnt, dist[i]);
    }

    private static void backtrack(int idx, int cnt, int M, int chickenShopCnt, int[] minDist) {
        if (cnt == M) {
            int sum = 0;
            for (int i = 0; i < minDist.length; i++)
                sum += minDist[i];

            ans = Math.min(ans, sum);
            return;
        }

        for (int i = idx + 1; i < chickenShopCnt; i++) {
            int[] newMinDist = minDist.clone();
            for (int j = 0; j < minDist.length; j++)
                newMinDist[j] = Math.min(newMinDist[j], dist[i][j]);

            backtrack(i, cnt + 1, M, chickenShopCnt, newMinDist);
        }
    }
}