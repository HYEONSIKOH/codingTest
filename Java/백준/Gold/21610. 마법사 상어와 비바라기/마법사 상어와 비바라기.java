import java.util.*;
import java.io.*;

public class Main {
    private static int[][] arr;
    private static int[][] moves;

    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    private static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    private static List<int[]> clouds = new ArrayList<>();
    private static int cloudCnt = 4;

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        moves = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            moves[i][0] = Integer.parseInt(st.nextToken()) - 1; // 방향
            moves[i][1] = Integer.parseInt(st.nextToken());     // 횟수
        }

        System.out.print(solution());
    }

    private static void printClouds(int[][] clouds) {
        for (int i = 0; i < clouds.length; i++) {
            System.out.print("{" + clouds[i][0] + ", " + clouds[i][1] + "} ");
        }
        System.out.println();
    }

    private static int solution() {
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});

        // M번 반복
        for (int i = 0; i < M; i++) {
            // 1. 모든 구름이 di 방향으로 si칸 이동
            problem1(moves[i][0], moves[i][1]);

            // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
            problem2();

            // 4. 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가
            problem4();

            // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어듬 (단, 3에서 구름이 사라진 칸은 제외)
            problem5();
        }

        int ans = 0;
        for (int[] a: arr) {
            for (int b: a)
                ans += b;
        }

        return ans;
    }

    private static void problem1(int d, int s) {
        for (int i = 0; i < cloudCnt; i++) {
            int nx = (clouds.get(i)[0] + dx[d] * s) % N;
            int ny = (clouds.get(i)[1] + dy[d] * s) % N;

            if (nx < 0) nx += N;
            if (ny < 0) ny += N;

            clouds.get(i)[0] = nx;
            clouds.get(i)[1] = ny;
        }
    }

    private static void problem2() {
        for (int i = 0; i < cloudCnt; i++)
            arr[clouds.get(i)[0]][clouds.get(i)[1]]++;
    }

    private static void problem4() {
        for (int i = 0; i < cloudCnt; i++) {
            int x = clouds.get(i)[0];
            int y = clouds.get(i)[1];
            int count = 0;

            for (int j = 1; j < 8; j += 2) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (isVaild(nx, ny) && arr[nx][ny] > 0) count++;
            }

            arr[x][y] += count;
        }
    }

    private static void problem5() {
        List<int[]> newClouds = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] >= 2 && !isCloud(i, j)) {
                    arr[i][j] -= 2;
                    newClouds.add(new int[]{i, j});
                }
            }
        }

        clouds.clear();
        clouds = newClouds;
        cloudCnt = clouds.size();
    }

    private static boolean isCloud(int x, int y) {
        for (int i = 0; i < cloudCnt; i++) {
            if (clouds.get(i)[0] == x && clouds.get(i)[1] == y)
                return true;
        }

        return false;
    }

    private static boolean isVaild(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }
}