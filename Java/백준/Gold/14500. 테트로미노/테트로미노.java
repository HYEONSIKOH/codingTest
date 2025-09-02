import java.io.*;
import java.util.*;

public class Main {
    private static int[][] arr;
    private static int N, M;
    private static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());

    }

    // 첫번째 도형
    private static int isOne(int x, int y) {
        int sum = 0;

        if (x + 1 < N && x + 2 < N && x + 3 < N)
            sum = Math.max(sum, arr[x][y] + arr[x + 1][y] + arr[x + 2][y] + arr[x + 3][y]);

        if (y + 1 < M && y + 2 < M && y + 3 < M)
            sum = Math.max(sum, arr[x][y] + arr[x][y + 1] + arr[x][y + 2] + arr[x][y + 3]);

        return sum;
    }

    // 두번째 도형
    private static int isTwo(int x, int y) {
        if (x + 1 < N && y + 1 < M)
            return arr[x][y] + arr[x + 1][y] + arr[x][y + 1] + arr[x + 1][y + 1];
        else
            return 0;
    }

    // 세번째 도형
    private static int isThree(int x, int y) {
        int sum = 0;

        if (x - 2 >= 0 && y + 1 < M)
            sum = arr[x][y] + arr[x - 1][y] + arr[x - 2][y] + arr[x][y + 1];

        if (x - 2 >= 0 && y - 1 >= 0)
            sum = Math.max(sum, arr[x][y] + arr[x - 1][y] + arr[x - 2][y] + arr[x][y - 1]);

        if (x + 2 < N && y + 1 < M)
            sum = Math.max(sum, arr[x][y] + arr[x + 1][y] + arr[x + 2][y] + arr[x][y + 1]);

        if (x + 2 < N && y - 1 >= 0)
            sum = Math.max(sum, arr[x][y] + arr[x + 1][y] + arr[x + 2][y] + arr[x][y - 1]);

        if (x + 1 < N && y + 2 < M)
            sum = Math.max(sum, arr[x][y] + arr[x][y + 1] + arr[x][y + 2] + arr[x + 1][y]);

        if (x - 1 >= 0 && y + 2 < M)
            sum = Math.max(sum, arr[x][y] + arr[x][y + 1] + arr[x][y + 2] + arr[x - 1][y]);

        if (x + 1 < N && y - 2 >= 0)
            sum = Math.max(sum, arr[x][y] + arr[x][y - 1] + arr[x][y - 2] + arr[x + 1][y]);

        if (x - 1 >= 0 && y - 2 >= 0)
            sum = Math.max(sum, arr[x][y] + arr[x][y - 1] + arr[x][y - 2] + arr[x - 1][y]);

        return sum;
    }

    // 네번째 도형
    private static int isFour(int x, int y) {
        int sum = 0;

        if (x + 2 < N && y + 1 < M)
            sum = arr[x][y] + arr[x + 1][y] + arr[x + 1][y + 1] + arr[x + 2][y + 1];

        if (x - 1 >= 0 && y + 2 < M)
            sum = Math.max(sum, arr[x][y] + arr[x][y + 1] + arr[x - 1][y + 1] + arr[x - 1][y + 2]);

        if (x + 2 < N && y - 1 >= 0)
            sum = Math.max(sum, arr[x][y] + arr[x + 1][y] + arr[x + 1][y - 1] + arr[x + 2][y - 1]);

        if (x + 1 < N && y + 2 < M)
            sum = Math.max(sum, arr[x][y] + arr[x][y + 1] + arr[x + 1][y + 1] + arr[x + 1][y + 2]);

        return sum;
    }

    // 다섯번째 도형
    private static int isFive(int x, int y) {
        int sum = 0;

        if (x + 1 < N && x - 1 >= 0 && y + 1 < M)
            sum = arr[x][y] + arr[x + 1][y] + arr[x - 1][y] + arr[x][y + 1];

        if (x + 1 < N && x - 1 >= 0 && y - 1 >= 0)
            sum = Math.max(sum, arr[x][y] + arr[x + 1][y] + arr[x - 1][y] + arr[x][y - 1]);

        if (x + 1 < N && y + 1 < M && y - 1 >= 0)
            sum = Math.max(sum, arr[x][y] + arr[x + 1][y] + arr[x][y + 1] + arr[x][y - 1]);

        if (x - 1 >= 0 && y + 1 < M && y - 1 >= 0)
            sum = Math.max(sum, arr[x][y] + arr[x - 1][y] + arr[x][y + 1] + arr[x][y - 1]);

        return sum;
    }

    private static int solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, isOne(i, j));
                ans = Math.max(ans, isTwo(i, j));
                ans = Math.max(ans, isThree(i, j));
                ans = Math.max(ans, isFour(i, j));
                ans = Math.max(ans, isFive(i, j));
            }
        }

        return ans;
    }


}