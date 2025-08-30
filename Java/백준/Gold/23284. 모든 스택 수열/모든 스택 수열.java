import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static int[] arr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        backTrack(1, 0, 0);

        System.out.print(sb.toString());
    }

    private static void backTrack(int depth, int next, int visited) {
        // 종료 조건
        if (depth == n + 1) {
            for (int i = 1; i <= n; i++)
                sb.append(arr[i]).append(" ");

            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if ((visited & (1 << i)) != 0) continue;

            if (i > arr[depth - 1] && i < next)
                break;

            arr[depth] = i;
            if (i >= next) backTrack(depth + 1, i + 1, visited | (1 << i));
            else backTrack(depth + 1, next, visited | (1 << i));
        }
    }
}