import java.util.*;
import java.io.*;

import static java.lang.Integer.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static int standard;

    private static String solution(int N) {
        standard = (int)Math.pow(10, N - 1);

        backtrack(2);
        backtrack(3);
        backtrack(5);
        backtrack(7);

        return sb.toString();
    }

    private static void backtrack(int n) {
        if (n >= standard * 10) return;
        
        if (standard <= n && n < standard * 10)
            sb.append(n).append("\n");

        for (int i = 1; i < 10; i++) {
            int next = n * 10 + i;

            if (isPrimeNumber(next)) backtrack(next);
        }
    }

    private static boolean isPrimeNumber(int n) {
        if (n < 2) return false;

        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int N = parseInt(br.readLine());

        bw.write(solution(N));

        // System.gc();
        bw.flush();
        bw.close();
    }
}