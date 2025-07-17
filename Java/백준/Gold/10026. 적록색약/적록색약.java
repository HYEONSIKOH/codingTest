import java.util.*;
import java.io.*;

public class Main {

    private static boolean isRange(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static int bfs(int N, char[][] arr, boolean isColorBlind) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        boolean[][] visited = new boolean[N][N];
        Deque<int[]> q = new ArrayDeque<>();

        int ans = 0;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (visited[x][y]) continue;
                else ans++;

                visited[x][y] = true;
                q.addLast(new int[] {x, y});

                char targetColor = arr[x][y];
                if (isColorBlind && targetColor != 'B')
                    targetColor = 'R'; // 적록색약은 R과 G를 동일하게 취급

                while (!q.isEmpty()) {
                    int[] cur = q.poll();

                    for (int i = 0; i < 4; i++) {
                        int nextX = cur[0] + dx[i];
                        int nextY = cur[1] + dy[i];

                        // 범위 밖 or 이미 방문한 곳
                        if (!isRange(nextX, nextY, N) || visited[nextX][nextY]) continue;

                        char nextColor = arr[nextX][nextY];

                        // 적록색약인 경우 R과 G를 동일하게 취급
                        if (isColorBlind && nextColor != 'B')
                            nextColor = 'R';

                        if (nextColor == targetColor) {
                            visited[nextX][nextY] = true;
                            q.addLast(new int[] {nextX, nextY});
                        }
                    }
                }
            }
        }

        return ans;
    }

    private static int[] solution(int N, char[][] arr) {
        // True: 적록색약, False: 일반인
        return new int[] { bfs(N, arr, false), bfs(N, arr, true) };
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        char[][] arr = new char[N][N];
        for (int i = 0; i < N; i++)
            arr[i] = br.readLine().toCharArray();

        int[] res = solution(N, arr);

        bw.write(res[0] + " " + res[1]);

        // System.gc();
        bw.flush();
        bw.close();
    }
}