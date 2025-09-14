import java.io.*;
import java.util.*;

public class Main {
    private static int[][] input;
    private static int[][] arr;
    private static boolean[][] visited;

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        input = new int[N*N][5];
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++)
                input[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    private static int solution() {
        for (int i = 0; i < N * N; i++) seat(i);

        int ans = 0;
        for (int i = 0; i < N * N; i++)
            ans += getScore(i);

        return ans;
    }

    private static void seat(int idx) {
        int[] pos = new int[]{-1, -1};
        int maxNum1 = -1, maxNum2 = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) continue;
                int tempNum1 = 0, tempNum2 = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if (!isValid(nx, ny)) continue;

                    // 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 얼마나 있는가?
                    for (int k = 1; k < 5; k++) {
                        if (arr[nx][ny] == input[idx][k]) {
                            tempNum1++;
                            break;
                        }
                    }

                    // 2. 인접한 칸이 얼마나 비어있는가?
                    if (arr[nx][ny] == 0) tempNum2++;
                }

                if (tempNum1 > maxNum1) {
                    maxNum1 = tempNum1;
                    pos[0] = i;
                    pos[1] = j;
                    maxNum2 = tempNum2;
                    continue;
                }

                if (tempNum1 == maxNum1) {
                    if (tempNum2 > maxNum2) {
                        maxNum2 = tempNum2;
                        pos[0] = i;
                        pos[1] = j;
                        continue;
                    }
                    if (tempNum2 == maxNum2) {
                        if (i < pos[0]) {
                            pos[0] = i;
                            pos[1] = j;
                        } else if (i == pos[0]) {
                            if (j < pos[1]) {
                                pos[0] = i;
                                pos[1] = j;
                            }
                        }
                    }
                }
            }
        }

        arr[pos[0]][pos[1]] = input[idx][0];
    }

    private static int getScore(int idx) {
        int score = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != input[idx][0]) continue;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if (!isValid(nx, ny)) continue;

                    for (int k = 1; k < 5; k++) {
                        if (arr[nx][ny] == input[idx][k]) {
                            score++;
                            break;
                        }
                    }
                }
            }
        }

        switch (score) {
            case 1: return 1;
            case 2: return 10;
            case 3: return 100;
            case 4: return 1000;
            default: return 0;
        }
    }

    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }
}