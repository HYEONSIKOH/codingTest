import java.io.*;
import java.util.*;

public class Main {
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[][] temp;

    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] airPurifier = new int[4];
    
    private static int R, C, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 세로
        C = Integer.parseInt(st.nextToken()); // 가로
        T = Integer.parseInt(st.nextToken()); // 시간

        arr = new int[R][C];
        int idx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    airPurifier[idx++] = i;
                    airPurifier[idx++] = j;
                }
            }
        }

        System.out.print(solution());
    }

    private static int solution() {
        visited = new boolean[R][C];
        temp = new int[R][C];

        int time = 0;
        while (time++ < T) {
            // 초기화
            for (int i = 0; i < R; i++) {
                Arrays.fill(visited[i], false);
                Arrays.fill(temp[i], 0);
            }

            // 탐색 - dfs
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (arr[r][c] > 0 && !visited[r][c])
                        dfs_spread(r, c);
                }
            }

            // 복사
            for (int i = 0; i < R; i++)
                for (int j = 0; j < C; j++)
                    arr[i][j] += temp[i][j];

            // 공기청정기 작동
            operateAirPurifier();
        }

        int ans = 0;
        for (int[] a : arr) {
            for (int b : a) {
                if (b > 0) ans += b;
            }
        }

        return ans;
    }

    private static void dfs_spread(int x, int y) {
        visited[x][y] = true;

        // 확산
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny) && arr[nx][ny] != -1) {
                temp[nx][ny] += arr[x][y] / 5;
                temp[x][y] -= arr[x][y] / 5;
            }
        }

        // 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny) && arr[nx][ny] > 0 && !visited[nx][ny])
                dfs_spread(nx, ny);
        }
    }

    private static void operateAirPurifier() {
        int upperX = airPurifier[0];
        int lowerX = airPurifier[2];

        for (int i = upperX - 1; i > 0; i--) arr[i][0] = arr[i - 1][0];
        for (int j = 0; j < C - 1; j++) arr[0][j] = arr[0][j + 1];
        for (int i = 0; i < upperX; i++) arr[i][C - 1] = arr[i + 1][C - 1];
        for (int j = C - 1; j > 1; j--) arr[upperX][j] = arr[upperX][j - 1];
        arr[upperX][1] = 0;
        arr[upperX][0] = -1;

        for (int i = lowerX + 1; i < R - 1; i++) arr[i][0] = arr[i + 1][0];
        for (int j = 0; j < C - 1; j++) arr[R - 1][j] = arr[R - 1][j + 1];
        for (int i = R - 1; i > lowerX; i--) arr[i][C - 1] = arr[i - 1][C - 1];
        for (int j = C - 1; j > 1; j--) arr[lowerX][j] = arr[lowerX][j - 1];
        arr[lowerX][1] = 0;
        arr[lowerX][0] = -1;
    }

    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= R || y < 0 || y >= C);
    }
}