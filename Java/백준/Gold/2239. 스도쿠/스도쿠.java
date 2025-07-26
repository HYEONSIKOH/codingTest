import java.util.*;
import java.io.*;

public class Main {
    private static final int N = 9;
    private static int[][] sudoku = new int[N][N];
    private static BufferedWriter bw;

    private static void solution(int y, int x) throws IOException {
        // 총 9줄을 다 탐색했을 경우
        if (y == N) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    bw.write(String.valueOf(sudoku[i][j]));
                bw.newLine();
            }

            bw.flush();
            bw.close();
            System.exit(0);
        }

        // 한줄을 다 탐색했을 경우
        if (x == N) {
            solution(y + 1, 0);
            return;
        }

        if (sudoku[y][x] == 0) {
            for (int i = 1; i <= N; i++) {
                if (isValid(y, x, i)) {
                    sudoku[y][x] = i;
                    solution(y, x + 1);
                    sudoku[y][x] = 0;
                }
            }
        } else solution(y, x + 1);
    }

    private static boolean isValid(int y, int x, int num) {
        for (int i = 0; i < N; i++)
            if (sudoku[y][i] == num || sudoku[i][x] == num)
                return false;

        int startY = (y / 3) * 3;
        int startX = (x / 3) * 3;

        for (int i = startY; i < startY + 3; i++)
            for (int j = startX; j < startX + 3; j++)
                if (sudoku[i][j] == num)
                    return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++)
                sudoku[i][j] = str.charAt(j) - '0';
        }

        solution(0, 0);

        // System.gc();
        bw.flush();
        bw.close();
    }
}