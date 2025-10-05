import java.util.*;
import java.io.*;

public class Main {
    private static byte[][] arr;

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};
    private static final int[] sharkPos = new int[2];

    private static int N, sharkSize = 2;

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
        int eatFishCnt = 0, totalDistance = 0;

        // 1. 상어 위치 파악
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 9) {
                    sharkPos[0] = i;
                    sharkPos[1] = j;
                    arr[i][j] = 0;
                    break;
                }
            }
        }

        while (true) {
            // 1-1. 상어 위치 0으로 초기화
            arr[sharkPos[0]][sharkPos[1]] = 0;

            // 2. 상어 위치에서 가장 가까운 먹을 수 있는 물고기 위치 탐색
            int[] pos = calDistance();

            // 2-1. 먹을 수 있는 물고기가 없다면 종료
            if (pos[2] == Integer.MAX_VALUE) break;

            // 3. 물고기 먹기 & 상어 위치 갱신 & 이동 거리 누적
            arr[pos[0]][pos[1]] = 0;
            sharkPos[0] = pos[0];
            sharkPos[1] = pos[1];
            totalDistance += pos[2];

            // 4. 만약 먹은 물고기 수가 상어 크기와 같다면 상어 크기 증가
            if (++eatFishCnt == sharkSize) {
                sharkSize++;
                eatFishCnt = 0;
            }
        }

        return totalDistance;
    }

    private static int[] calDistance() {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(dist[i], -1);
        dist[sharkPos[0]][sharkPos[1]] = 0;

        int[] pos = new int[]{-1, -1, Integer.MAX_VALUE};

        Deque<int[]> q = new LinkedList<>();
        q.add(sharkPos);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (arr[x][y] != 0 && arr[x][y] < sharkSize) {
                if (dist[x][y] < pos[2] || (dist[x][y] == pos[2] && (x < pos[0] || (x == pos[0] && y < pos[1])))) {
                    pos[0] = x;
                    pos[1] = y;
                    pos[2] = dist[x][y];
                }
            }

            if (pos[2] < dist[x][y]) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isVaild(nx, ny) && dist[nx][ny] == -1 && arr[nx][ny] <= sharkSize) {
                    if (dist[x][y] + 1 > pos[2]) continue;
                    dist[nx][ny] = dist[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return pos;
    }

    private static boolean isVaild(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}