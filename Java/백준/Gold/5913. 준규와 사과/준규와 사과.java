import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][] visited = new boolean[5][5];

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static final int maxSize = 5;
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            visited[x-1][y-1] = true;
        }

        visited[0][0] = true;

        backtrack(0, 0);
        System.out.print(ans);
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < maxSize && 0 <= y && y < maxSize && !visited[x][y];
    }

    private static void backtrack(int x, int y) {
        if (x == 4 && y == 4) {
            for (boolean[] row : visited) {
                for (boolean cell : row) {
                    if (!cell) return;
                }
            }
            ans++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny)) {
                visited[nx][ny] = true;
                backtrack(nx, ny);
                visited[nx][ny] = false;
            }
        }
    }
}