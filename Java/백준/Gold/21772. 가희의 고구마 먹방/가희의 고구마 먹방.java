import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static char[][] map;
    static int ans = 0;

    // 상, 하, 좌, 우, 제자리
    static final int[] dx = {-1, 1, 0, 0, 0};
    static final int[] dy = {0, 0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        int sx = -1, sy = -1;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'G') { // 시작 위치
                    sx = i; sy = j;
                    map[i][j] = '.'; // 편하게 처리하려고 빈칸으로 바꿔둠
                }
            }
        }

        dfs(sx, sy, 0, 0);
        System.out.println(ans);
    }

    static void dfs(int x, int y, int t, int eaten) {
        // 남은 시간이 없어도 현재까지 먹은 개수로 갱신
        ans = Math.max(ans, eaten);
        if (t == T) return;

        for (int dir = 0; dir < 5; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 제자리(dir==4)는 항상 범위 안, 나머지는 범위/장애물 체크
            if (dir != 4) {
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (map[nx][ny] == '#') continue;
            } else { // 제자리
                nx = x; ny = y;
            }

            if (map[nx][ny] == 'S') {
                map[nx][ny] = '.';          // 먹는다(임시로 제거)
                dfs(nx, ny, t + 1, eaten + 1);
                map[nx][ny] = 'S';          // 복구(백트래킹)
            } else {
                dfs(nx, ny, t + 1, eaten);
            }
        }
    }
}