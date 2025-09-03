import java.io.*;
import java.util.*;

public class Main {
    private static int[][] MAP;
    private static int[] MOVE;
    private static int N, M, K;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        MAP = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                MAP[i][j] = Integer.parseInt(st.nextToken());
        }

        MOVE = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++)
            MOVE[i] = Integer.parseInt(st.nextToken());

        solution(X, Y);
        System.out.println(sb);
    }

    private static void solution(int x, int y) {
        // 1: 동, 2: 서, 3: 북, 4: 남
        int[] dx = {2, 0, 0, -1, 1};
        int[] dy = {2, 1, -1, 0, 0};

        int[] dice = new int[6];

        for (int m : MOVE) {
            x += dx[m];
            y += dy[m];

            if (x < 0 || x >= N || y < 0 || y >= M) {
                x -= dx[m];
                y -= dy[m];
                continue;
            }

            int temp;
            switch(m) {
                case 1: // 동
                    temp = dice[1];
                    dice[1] = dice[5];
                    dice[5] = dice[3];
                    dice[3] = dice[4];
                    dice[4] = temp;
                    break;
                case 2: // 서
                    temp = dice[1];
                    dice[1] = dice[4];
                    dice[4] = dice[3];
                    dice[3] = dice[5];
                    dice[5] = temp;
                    break;
                case 3: // 북
                    temp = dice[1];
                    dice[1] = dice[2];
                    dice[2] = dice[3];
                    dice[3] = dice[0];
                    dice[0] = temp;
                    break;
                case 4: // 남
                    temp = dice[1];
                    dice[1] = dice[0];
                    dice[0] = dice[3];
                    dice[3] = dice[2];
                    dice[2] = temp;
                    break;
            }

            if (MAP[x][y] == 0) MAP[x][y] = dice[1];
            else {
                dice[1] = MAP[x][y];
                MAP[x][y] = 0;
            }

            sb.append(dice[3]).append('\n');
        }
    }
}