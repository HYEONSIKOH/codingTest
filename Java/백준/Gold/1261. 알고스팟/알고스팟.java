import java.util.*;
import java.io.*;

public class Main {
    private static int[][] arr;

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++)
                arr[i][j] = line[j] - '0';
        }

        System.out.println(solution());
    }

    private static int solution() {
        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], cost = cur[2];

            if (visited[x][y] <= cost) continue;
            else visited[x][y] = cost;

            if (x == N-1 && y == M-1) return cost;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isVaild(nx, ny) && visited[nx][ny] > cost + arr[nx][ny])
                    pq.add(new int[]{nx, ny, cost + arr[nx][ny]});
            }
        }

        return visited[N-1][M-1];
    }

    private static boolean isVaild(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}