import java.io.*;
import java.util.*;

public class Main {
    private static final char[][] arr = new char[12][6];
    private static boolean[][] visited;

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 12; i++)
            arr[i] = br.readLine().toCharArray();

        System.out.println(solution());
    }

    private static int solution() {
        int chains = 0;
        while (true) {
            visited = new boolean[12][6];
            boolean[][] toPop = new boolean[12][6];
            boolean any = false;

            // 1) 보드 전체 탐색: 같은 색 연결 컴포넌트 수집
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (arr[i][j] == '.' || visited[i][j]) continue;
                    bfsCollect(i, j, toPop);
                }
            }

            // 2) 이번 턴에 터질 게 있는지 확인
            outer:
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (toPop[i][j]) { any = true; break outer; }
                }
            }
            if (!any) break;

            // 3) 한 번에 터뜨리기
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (toPop[i][j]) arr[i][j] = '.';
                }
            }

            // 4) 중력 적용
            applyGravity();

            chains++;
        }
        return chains;
    }

    private static void bfsCollect(int sx, int sy, boolean[][] toPop) {
        char color = arr[sx][sy];
        Deque<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> comp = new ArrayList<>();
        visited[sx][sy] = true;
        q.add(new int[]{sx, sy});
        comp.add(new int[]{sx, sy});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!isValid(nx, ny) || visited[nx][ny] || arr[nx][ny] != color) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                comp.add(new int[]{nx, ny});
            }
        }

        if (comp.size() >= 4)
            for (int[] p : comp) toPop[p[0]][p[1]] = true;
    }

    private static void applyGravity() {
        for (int j = 0; j < 6; j++) {
            int write = 11;
            for (int i = 11; i >= 0; i--) {
                if (arr[i][j] != '.') {
                    char c = arr[i][j];
                    arr[i][j] = '.';
                    arr[write][j] = c;
                    write--;
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= 12 || y < 0 || y >= 6);
    }
}