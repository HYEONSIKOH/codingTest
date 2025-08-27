import java.io.*;
import java.util.*;

public class Main {
    private static int[][] sudoku;
    private static List<int[]> zeroList;

    private static boolean isSolved;
    private static boolean isVaild;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        while (N-- != 0) {
            sudoku = new int[9][9];
            zeroList = new ArrayList<>();
            isSolved = false;
            isVaild = true;
            StringBuilder sb = new StringBuilder();

            // Input
            for (int i = 0; i < 9; i++) {
                char[] ch = br.readLine().toCharArray();
                for (int j = 0; j < 9; j++) {
                    sudoku[i][j] = ch[j] - '0';
                    if (sudoku[i][j] == 0)
                        zeroList.add(new int[]{i, j});
                }
            }

            // 검증
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudoku[i][j] != 0 && !isPossible(i, j, sudoku[i][j])) {
                        isVaild = false;
                        break;
                    }
                }
            }

            // 실행
            if (isVaild) solutuion(0);

            // Output
            if (!isSolved || !isVaild) {
                System.out.println("Could not complete this grid.");
                System.out.println();
                continue;
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++)
                    sb.append(sudoku[i][j]);
                sb.append('\n');
            }
            sb.append('\n');
            System.out.print(sb);
        }
    }

    private static boolean isPossible(int x, int y, int value) {
        // 가로 & 세로
        for (int i = 0; i < 9; i++) {
            if (i != y && sudoku[x][i] == value) return false;
            if (i != x && sudoku[i][y] == value) return false;
        }

        // 3x3
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (i != x && j != y && sudoku[i][j] == value)
                    return false;
            }
        }

        return true;
    }

    private static void solutuion(int idx) {
        if (isSolved) return;

        if (idx == zeroList.size()) {
            isSolved = true;
            return;
        }

        int x = zeroList.get(idx)[0];
        int y = zeroList.get(idx)[1];

        for (int i = 1; i <= 9; i++) {
            if (isPossible(x, y, i)) {
                sudoku[x][y] = i;
                solutuion(idx + 1);

                if (isSolved) return;
                else sudoku[x][y] = 0;
            }
        }
    }
}