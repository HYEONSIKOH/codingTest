import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x, y, dist;
        boolean broke; // true: 벽을 한 번 부쉈음

        Node(int x, int y, int dist, boolean broke) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broke = broke;
        }
    }

    static int N, M;
    static char[][] map;
    static int[][] state; // 0: 미방문, 2: breaker 방문, 3: unbreaker 방문
    static final int MOVEABLE = 0, WALL = 1, BREAKER_VISITED = 2, UNBREAKER_VISITED = 3;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 1, false));
        state[0][0] = UNBREAKER_VISITED;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == N - 1 && cur.y == M - 1) return cur.dist;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (map[nx][ny] == '0') {
                    if (!cur.broke && state[nx][ny] != UNBREAKER_VISITED) {
                        state[nx][ny] = UNBREAKER_VISITED;
                        q.add(new Node(nx, ny, cur.dist + 1, false));
                    } else if (cur.broke && state[nx][ny] != BREAKER_VISITED && state[nx][ny] != UNBREAKER_VISITED) {
                        state[nx][ny] = BREAKER_VISITED;
                        q.add(new Node(nx, ny, cur.dist + 1, true));
                    }
                } else if (map[nx][ny] == '1') {
                    if (!cur.broke && state[nx][ny] != BREAKER_VISITED && state[nx][ny] != UNBREAKER_VISITED) {
                        state[nx][ny] = BREAKER_VISITED;
                        q.add(new Node(nx, ny, cur.dist + 1, true));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        state = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }
}