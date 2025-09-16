import java.io.*;
import java.util.*;

public class Main {
    private static class Pos {
        byte x, y;

        public Pos(byte x, byte y) {
            this.x = x;
            this.y = y;
        }
    }

    private static byte[][] arr;

    private static final byte[] dx = {-1, 0, 1, 0};
    private static final byte[] dy = {0, 1, 0, -1};

    private static byte N, M, D;
    private static short ans = 0;
    private static Pos start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Byte.parseByte(st.nextToken());
        M = Byte.parseByte(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = new Pos(Byte.parseByte(st.nextToken()), Byte.parseByte(st.nextToken()));
        D = Byte.parseByte(st.nextToken()); // 0: 북, 1: 동, 2: 남쪽, 3: 서

        arr = new byte[N][M];
        for (byte i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (byte j = 0; j < M; j++)
                arr[i][j] = Byte.parseByte(st.nextToken());
        }

        System.out.println(solution());
    }

    private static short solution() {
        dfs(start);

        return ans;
    }

    private static void dfs(Pos p) {
        byte x = p.x, y = p.y;
        if(arr[x][y] == 0) {
            ans++;
            arr[x][y] = 2;
        }

        byte isClean = 0, tempD = D;
        byte nx = 0, ny = 0;
        for (byte i = 0; i < 4; i++) {
            tempD = (byte)((tempD + 3) % 4);
            nx = (byte)(x + dx[tempD]);
            ny = (byte)(y + dy[tempD]);

            if (!isValid(nx, ny) || arr[nx][ny] != 0) continue;
            isClean = 1;
            break;
        }

        if (isClean == 1) {
            D = tempD;
            dfs(new Pos(nx, ny));
        } else {
            int backX = x - dx[D];
            int backY = y - dy[D];

            // 뒤쪽이 범위 밖이거나, 벽인 경우
            if (!isValid(backX, backY) || arr[backX][backY] == 1) {
                System.out.print(ans);
                System.exit(0);
            }

            // 벽이 아니면 다시 DFS
            dfs(new Pos((byte) backX, (byte) backY));
        }
    }

    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }
}