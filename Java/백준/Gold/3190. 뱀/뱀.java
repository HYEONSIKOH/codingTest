import java.io.*;
import java.util.*;

public class Main {
    private static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Pos p = (Pos) o;
            return this.x == p.x && this.y == p.y;
        }
    }

    private static int[][] map;
    private static int[][] moves;

    private static int N, K, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());
        moves = new int[L][2];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            moves[i][0] = Integer.parseInt(st.nextToken());
            moves[i][1] = st.nextToken().charAt(0);
        }

        solution(0, 0);
    }

    private static void solution(int x, int y) {
        Deque<Pos> queue = new LinkedList<>();
        queue.add(new Pos(x, y));

        int idx = 0, ans = 0, dir = 0;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        while (true) {
            x += dx[dir];
            y += dy[dir];
            ans++;

            if (x < 0 || x >= N || y < 0 || y >= N || queue.contains(new Pos(x, y))) {
                System.out.println(ans);
                return;
            }

            queue.add(new Pos(x, y));
            if (map[x][y] == 1) map[x][y] = 0;
            else queue.pop();

            if (idx < L && moves[idx][0] == ans) {
                if (moves[idx][1] == 'L') dir = (dir + 3) % 4; // 좌회전
                if (moves[idx][1] == 'D') dir = (dir + 1) % 4; // 우회전
                idx++;
            }
        }
    }
}