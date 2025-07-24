import java.util.*;
import java.io.*;

public class Main {
    private static boolean[][] visited;
    private static int ans = 0;

    private static void solution(int row, int N) {
        if (row == N) {
            ans++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isValid(row, col, N)) {
                visited[row][col] = true;
                solution(row + 1, N);
                visited[row][col] = false;
            }
        }
    }

    private static boolean isValid(int row, int col, int N) {
        // 위쪽 대각선 왼쪽
        for (int i = 1; i <= row; i++)
            if (col - i >= 0 && visited[row - i][col - i]) return false;

        // 위쪽 대각선 오른쪽
        for (int i = 1; i <= row; i++)
            if (col + i < N && visited[row - i][col + i]) return false;

        // 위쪽 직선만 확인
        for (int i = 0; i < row; i++) if (visited[i][col]) return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        solution(0, N);
        bw.write(String.valueOf(ans));

        // System.gc();
        bw.flush();
        bw.close();
    }
}