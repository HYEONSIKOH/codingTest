import java.util.*;
import java.io.*;

public class Main {
    private static byte[][] arr;
    private static int[][] visited;

    private static final byte[] dx = {0, 0, 1, -1};
    private static final byte[] dy = {1, -1, 0, 0};

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st /*= new StringTokenizer(br.readLine())*/;

        N = Integer.parseInt(br.readLine());

        arr = new byte[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Byte.parseByte(st.nextToken());
        }

        System.out.println(solution());
    }

    private static int solution() {
        visited = new int[N][N];

        // 1. 섬 범위 찾기
        int num = 1, ans = Integer.MAX_VALUE;
        for (byte i = 0; i < N; i++) {
            for (byte j = 0; j < N; j++) {
                if (arr[i][j] == 1 && visited[i][j] == 0)
                    ans = Math.min(ans, findIslandRangeBfs(i, j, num++));
            }
        }

        return ans;
    }

    private static int findIslandRangeBfs(int X, int Y, int num) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {X, Y});

        // 2. 섬의 가장자리 찾기 & 다리 최소 길이 탐색
        Deque<int[]> bridgeQ = new ArrayDeque<>();
        boolean[][] visitedEdge = new boolean[N][N];

        visited[X][Y] = (byte) num;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            boolean isEdge = false;
            for (byte dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (isVaild(nx, ny) && visited[nx][ny] == 0) {
                    if (arr[nx][ny] == 1) {
                        visited[nx][ny] = num;
                        q.add(new int[] { nx, ny });
                    }
                    else { isEdge = true; }
                }
            }

            if (isEdge) {
                bridgeQ.add(new int[] {x, y, 0});
                visitedEdge[x][y] = true;
            }
        }

        return findBridgeBfs(bridgeQ, visitedEdge, num);
    }

    private static int findBridgeBfs(Deque<int[]> q, boolean[][] visitedEdge, int num) {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int len = cur[2];

            for (byte dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (isVaild(nx, ny) && !visitedEdge[nx][ny]) {
                    if (arr[nx][ny] == 1 && visited[nx][ny] != num) {
                        q.clear();
                        return len;
                    }

                    visitedEdge[nx][ny] = true;
                    q.add(new int[] {nx, ny, len + 1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static boolean isVaild(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}