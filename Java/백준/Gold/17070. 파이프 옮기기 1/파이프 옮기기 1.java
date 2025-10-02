import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int[][] arr;

    private static int cnt = 0;
    /**
     * 1. 한 지점에서 가장 먼 지점까지의 거리를 구함 => 지름의 한 끝점
     * 2. 1에서 구한 지점에서 가장 먼 지점까지의 거리를 구함 => 지름의 다른 끝 점
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solution(0, 1, 0);
        System.out.print(cnt);
    }

    // 0: 가로, 1: 세로, 2: 대각선
    private static void solution(int x, int y, int mode) {
        if (x == N - 1 && y == N - 1) {
            cnt++;
            return;
        }

        int nx = 0, ny = 0;
        switch (mode) {
            case 0:
                // 1. 가로 이동
                nx = x;
                ny = y + 1;

                if (isValid(nx, ny) && arr[nx][ny] == 0)
                    solution(nx, ny, 0);

                // 2. 대각선 이동
                nx = x + 1;
                ny = y + 1;
                if (isValid(nx, ny) && arr[nx][ny] == 0 && arr[nx - 1][ny] == 0 && arr[nx][ny - 1] == 0)
                    solution(nx, ny, 2);
                break;

            case 1:
                // 1. 세로 이동
                nx = x + 1;
                ny = y;

                if (isValid(nx, ny) && arr[nx][ny] == 0)
                    solution(nx, ny, 1);

                // 2. 대각선 이동
                nx = x + 1;
                ny = y + 1;
                if (isValid(nx, ny) && arr[nx][ny] == 0 && arr[nx - 1][ny] == 0 && arr[nx][ny - 1] == 0)
                    solution(nx, ny, 2);
                break;

            case 2:
                // 1. 가로 이동
                nx = x;
                ny = y + 1;
                if (isValid(nx, ny) && arr[nx][ny] == 0)
                    solution(nx, ny, 0);

                // 2. 세로 이동
                nx = x + 1;
                ny = y;
                if (isValid(nx, ny) && arr[nx][ny] == 0)
                    solution(nx, ny, 1);

                // 3. 대각선 이동
                nx = x + 1;
                ny = y + 1;
                if (isValid(nx, ny) && arr[nx][ny] == 0 && arr[nx - 1][ny] == 0 && arr[nx][ny - 1] == 0)
                    solution(nx, ny, 2);
                break;
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}