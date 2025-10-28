import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static int[][] arr;
    private static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = readInt();
        M = readInt();
        arr = new int[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                arr[i][j] = readInt();

        K = readInt();
        for (int i = 0; i < K; i++) {
            int[] pos1 = {readInt(), readInt()};
            int[] pos2 = {readInt(), readInt()};
            solution(pos1, pos2);
        }

        System.out.println(sb);
    }

    private static void solution(int[] pos1, int[] pos2) {
        int sum = 0;
        for (int i = pos1[0] - 1; i < pos2[0]; i++) {
            for (int j = pos1[1] - 1; j < pos2[1]; j++) {
                sum += arr[i][j];
            }
        }

        sb.append(sum).append('\n');
    }

    private static int readInt() throws IOException {
        int c = System.in.read();

        while (c <= 32) {
            if (c == -1) return -1;
            c = System.in.read();
        }

        int sign = 1;
        if (c == '-') {
            sign = -1;
            c = System.in.read();
        }

        int n = 0;
        while (c > 32) {
            n = (n << 3) + (n << 1) + (c & 15); // n*10 + digit
            c = System.in.read();
        }
        return sign * n;
    }
}