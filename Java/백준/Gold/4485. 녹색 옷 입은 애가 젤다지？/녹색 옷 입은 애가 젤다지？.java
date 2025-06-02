import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static final Integer INF = Integer.MAX_VALUE;
    private static int[][] g;
    private static int[][] dist;

    private static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int cost;

        public Pair(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    private static boolean range(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static void solution(int N) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0, g[0][0]));
        dist[0][0] = g[0][0];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nX = p.x + dx[i];
                int nY = p.y + dy[i];// p.cost: 현재 노드까지의 비용, nextP.cost:현재 노드에서 다음 노드까지의 비용

                // 좌표 범위 밖
                if (!range(nX, nY, N)) continue;

                int nDist = p.cost + g[nX][nY];
                if (nDist < dist[nX][nY]) {
                    dist[nX][nY] = nDist;
                    pq.add(new Pair(nX, nY, nDist));
                }
            }
        }

        System.out.println(dist[N-1][N-1]);
    }

    public static void main(String[] args) throws IOException {
        int num = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            g = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                Arrays.fill(dist[i], INF);
                for (int j = 0; j < N; j++) {
                    g[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.print("Problem " + num++ + ": ");
            solution(N);
        }
    }
}
