import java.io.*;
import java.util.*;

public class Main {
    private static void solution(int N) {
        StringBuilder sb = new StringBuilder();

        for (char c = '1'; c <= '3'; c++) {
            sb.append(c);
            backtrack(sb, N);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static void backtrack(StringBuilder sb, int N) {
        int size = sb.length();

        for (int i = 1; i <= size / 2; i++) {
            if (sb.substring(size - i).equals(sb.substring(size - 2 * i, size - i)))
                return;
        }

        if (size == N) {
            System.out.println(sb);
            System.exit(0);
        }

        for (char c = '1'; c <= '3'; c++) {
            sb.append(c);
            backtrack(sb, N);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        solution(N);

        br.close();
    }
}