import java.util.*;
import java.io.*;

public class Main {
    private static int[][] arr;
    private static int[][] visited;
    private static final List<int[]> startPoints = new ArrayList<>();

    // ↓, →, ↑, ←
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int N, M, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solution();
    }

    private static void solution() {
        int temp = 1;
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] != 0) continue;
                findRoad(temp++, i, j);
                startPoints.add(new int[] {i, j});
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < startPoints.size(); j++) {
                int x = startPoints.get(j)[0];
                int y = startPoints.get(j)[1];
                moveStartPoint(j, x, y);
            }
        }

        printArr();
    }

    private static void findRoad(int temp, int x, int y) {
        int dir = 0;
        while (true) {
            visited[x][y] = temp;
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (isVaild(nx, ny) && visited[nx][ny] == 0) {
                x = nx;
                y = ny;
            } else {
                dir = dir + 1;
                if (dir == 4) break;

                x += dx[dir];
                y += dy[dir];
            }
        }
    }

    private static void moveStartPoint(int idx, int x, int y) {
        int dir = 0, temp = visited[x][y], num = arr[x][y];

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (!isVaild(nx, ny) || visited[nx][ny] != temp) {
                dir = dir + 1;
                if (dir == 4) break;

                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            int nextNum = arr[nx][ny];
            arr[nx][ny] = num;
            num = nextNum;
            x = nx;
            y = ny;
        }
    }

    private static void printArr() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }


    private static boolean isVaild(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }
}