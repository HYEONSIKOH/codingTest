import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int ans = 0;
    private static char[][] arr;
    private static boolean[] visited = new boolean[26];
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int X, Y;

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < X && y < Y;
    }

    private static void dfs(int x, int y, int depth) {
        depth++;
        ans = Math.max(ans, depth);
        visited[arr[y][x] - 'A'] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isValid(nx, ny)) continue;
            else {
                int ni = arr[ny][nx] - 'A';

                if (!visited[ni]) {
                    visited[ni] = true;
                    dfs(nx, ny, depth);
                    visited[ni] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new char[Y][X];
        for (int y = 0; y < Y; y++) {
            String str = br.readLine();
            for (int x = 0; x < X; x++) {
                arr[y][x] = str.charAt(x);
            }
        }

        dfs(0, 0, 0);
        System.out.println(ans);
    }
}
