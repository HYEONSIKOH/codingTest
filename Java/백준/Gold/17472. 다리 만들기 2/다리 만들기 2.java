import java.util.*;
import java.io.*;

public class Main {
    private static int[][] arr;

    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};
    private static int[] parent;

    private static PriorityQueue<int[]> pq;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                arr[i][j] = st.nextToken().charAt(0) == '0' ? 0 : -1;
        }

        System.out.println(solution());
    }

    private static int solution() {
        // 인스턴스 초기화
        pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        int landNum = 1;

        // 1. 섬 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == -1)
                    findLandWithBfs(i, j, landNum++);
            }
        }

        // 2. 다리 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                findBrdige(i, j);
            }
        }

        // 3. 크루즈칼 알고리즘 (MST)
        if (pq.isEmpty()) return -1;
        else return kruskal(landNum);
    }

    private static void findLandWithBfs(int i, int j, int landNum) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j});
        arr[i][j] = landNum;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            // 다음 좌표 탐색 - BFS
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (!isVaild(nx, ny) || arr[nx][ny] != -1) continue;
                else {
                    arr[nx][ny] = landNum;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static void findBrdige(int x, int y) {
        int landNum = arr[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            int length = 0;

            while (true) {
                nx += dx[i];
                ny += dy[i];
                length++;

                // 섬을 벗어나거나 같은 섬인 경우
                if (!isVaild(nx, ny) || arr[nx][ny] == landNum) break;

                // 바다인 경우
                if (arr[nx][ny] != 0) {
                    if (--length >= 2)
                        pq.add(new int[]{arr[x][y], arr[nx][ny], length});
                    break;
                }
            }
        }
    }

    private static int kruskal(int landNum) {
        boolean[] visited2 = new boolean[landNum];

        parent = new int[landNum];
        for (int i = 1; i < landNum; i++) parent[i] = i;

        landNum -= 1;

        int ans = 0, cnt = 0;
        while(true) {
            if (cnt == landNum - 1) return ans;
            if (pq.isEmpty()) return -1;

            int[] cur = pq.poll();
            int a = cur[0];
            int b = cur[1];
            int cost = cur[2];

            int pa = find(a), pb = find(b);
            if (pa != pb) {
                parent[pa] = pb;
                visited2[a] = true;
                visited2[b] = true;
                ans += cost;
                cnt++;
            }
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    private static boolean isVaild(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}