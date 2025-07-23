import java.util.*;
import java.io.*;

public class Main {
    private static int[][] arr = new int[9][9];
    private static BufferedWriter bw;

    private static void solution(int x, int y) throws IOException {
        // 해당 행이 다 채워졌을 경우
        if (y == 9) {
            solution(x + 1, 0);
            return;
        }

        // 행과 열이 모두 채워졋을 경우
        if (x == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++)
                    bw.write(arr[i][j] + " ");
                bw.newLine();
            }

            bw.flush();
            bw.close();
            System.exit(0);
        }

        if (arr[x][y] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (isValid(x, y, i)) {
                    arr[x][y] = i;
                    solution(x, y + 1);
                }
            }
            arr[x][y] = 0;
            return;
        }

        solution(x, y + 1);
    }

    private static boolean isValid(int x, int y, int num) {
        // 같은 행, 같은 열에 같은 숫자가 있는가?
        for (int i = 0; i < 9; i++) {
            if (arr[i][y] == num || arr[x][i] == num)
                return false;
        }

        // 같은 3x3 박스에 같은 숫자가 있는가?
        int boxX = (x / 3) * 3;
        int boxY = (y / 3) * 3;

        for (int i = boxX; i < boxX + 3; i++) {
            for (int j = boxY; j < boxY + 3; j++) {
                if (arr[i][j] == num) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0, 0);

        // System.gc();
        bw.flush();
        bw.close();
    }
}