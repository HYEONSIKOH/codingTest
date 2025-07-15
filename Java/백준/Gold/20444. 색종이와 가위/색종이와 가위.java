import java.util.*;
import java.io.*;

public class Main {

    private static long calculate(long a, long b) {
        return (a + 1L) * (b + 1L);
    }


    private static String solution(long N, long K) {
        long L = 0, R = N;
        while (L < R) {
            long mid = (L + R) / 2;
            long a = mid, b = N - mid;

            if (calculate(a, b) == K) return "YES";
            else if (calculate(a, b) < K) L = mid + 1;
            else R = mid;
        }

        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        bw.write(solution(N, K));

        // System.gc();
        bw.flush();
        bw.close();
    }
}