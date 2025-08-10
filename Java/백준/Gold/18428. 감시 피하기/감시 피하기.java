import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static char[][] arr;

    private static Point[] teachers;
    private static Point[] emptySpaces;

    private static int emptyCnt = 0;
    private static int teacherCnt = 0;

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = st.nextToken().charAt(0);
        }

        solution(N);

        System.out.println("NO");
    }

    private static void solution(int N) {
        emptySpaces = new Point[N * N];
        teachers = new Point[N * N];

        emptyCnt = 0;
        teacherCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'T') teachers[teacherCnt++] = new Point(i, j);
                else if (arr[i][j] == 'X') emptySpaces[emptyCnt++] = new Point(i, j);
            }
        }

        for (int i = 0; i < emptyCnt; i++) {
            arr[emptySpaces[i].x][emptySpaces[i].y] = 'O';
            backtrack(i, 1);
            arr[emptySpaces[i].x][emptySpaces[i].y] = 'X';
        }
    }

    private static void backtrack(int idx, int cnt) {
        if (cnt == 3) {
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            for (int t = 0; t < teacherCnt; t++) {
                Point teacher = teachers[t];

                for (int i = 0; i < 4; i++) {
                    int nx = teacher.x;
                    int ny = teacher.y;

                    while (true) {
                        nx += dx[i];
                        ny += dy[i];

                        if (nx < 0 || ny < 0 || nx >= arr.length || ny >= arr[0].length) break;
                        if (arr[nx][ny] == 'O') break;
                        if (arr[nx][ny] == 'S') return;
                    }
                }
            }

            System.out.println("YES");
            System.exit(0);
        } else {
            for (int i = idx + 1; i < emptyCnt; i++) {
                arr[emptySpaces[i].x][emptySpaces[i].y] = 'O';
                backtrack(i, cnt + 1);
                arr[emptySpaces[i].x][emptySpaces[i].y] = 'X';
            }
        }
    }
}