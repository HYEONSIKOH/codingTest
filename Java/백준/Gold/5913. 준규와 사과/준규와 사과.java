import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static final int maxSize = 5;
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        int visited = 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            visited |= (1 << (x * maxSize + y));
        }

        backtrack(0, 0, visited);
        System.out.print(ans);
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < maxSize && 0 <= y && y < maxSize;
    }

    private static void backtrack(int x, int y, int visited) {
        if (x == 4 && y == 4) {
            if (visited == (1 << 25) - 1)
                ans++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            int index = nx * maxSize + ny;
            if (isValid(nx, ny) && (visited & (1 << index)) == 0)
                backtrack(nx, ny, visited | (1 << index));
        }
    }
}