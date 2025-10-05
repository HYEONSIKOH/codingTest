import java.util.*;
import java.io.*;

public class Main {
    private static byte[][] arr;

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
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

        while (true) {
            int[] pos = new int[]{-1, -1, Integer.MAX_VALUE}; // 먹을 수 있는 물고기 위치

            // 1. 상어 위치 파악
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 9) {
                        sharkPos[0] = i;
                        sharkPos[1] = j;
                        break;
                    }
                }
            }

            // 2. 상어 위치에서 각 칸까지의 거리 계산
            int[][] dist = calDistance(sharkPos);

            // 3. 상어 위치와 가장 가까운 먹을 수 있는 물고기 위치 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] != 9 && arr[i][j] != 0 && dist[i][j] != -1 && arr[i][j] < sharkSize) {
                        int depth = dist[i][j];

                        // 더 가까운 경우, 당연히 갱신
                        if (depth < pos[2]) {
                            pos[2] = depth;
                            pos[0] = i;
                            pos[1] = j;
                        }

                        // 거리가 같은 경우
                        else if (depth == pos[2]) {
                            // 더 위에 있는 경우
                            if (i < pos[0]) {
                                pos[0] = i;
                                pos[1] = j;
                            }
                            // 같은 높이에 있는 경우, 더 왼쪽에 있는 물고기를 선택
                            else if (i == pos[0] && j < pos[1])
                                pos[1] = j;
                        }
                    }
                }
            }

            // 3-1. 먹을 수 있는 물고기가 없다면 종료
            if (pos[2] == Integer.MAX_VALUE) break;

            // 4. 상어를 그 위치로 이동
            arr[sharkPos[0]][sharkPos[1]] = 0;
            arr[pos[0]][pos[1]] = 9;
            totalDistance += pos[2];

            // 5. 만약 먹은 물고기 수가 상어 크기와 같다면 상어 크기 증가
            if (++eatFishCnt == sharkSize) {
                sharkSize++;
                eatFishCnt = 0;
            }
        }

        return totalDistance;
    }

    private static int[][] calDistance(int[] sharkPos) {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(dist[i], -1);
        dist[sharkPos[0]][sharkPos[1]] = 0;

        Deque<int[]> q = new LinkedList<>();
        q.add(sharkPos);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isVaild(nx, ny) && dist[nx][ny] == -1 && arr[nx][ny] <= sharkSize) {
                    dist[nx][ny] = dist[x][y] + 1;
                    q.add(new int[] {nx, ny});
                }
            }
        }

        return dist;
    }

    private static boolean isVaild(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}