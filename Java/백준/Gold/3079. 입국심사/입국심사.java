import java.util.*;
import java.io.*;

public class Main {
    private static long solution(int N, int M, long[] time) {
        Arrays.sort(time);

        long L = 1, R = time[0] * M;
        long ans = 0;
        while (L <= R) {
            long m = (L + R) / 2;

            long cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += m / time[i];
                if (cnt > M) break;
            }

            if (cnt < M) L = m + 1;
            else {
                ans = m;
                R = m - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] time = new long[N];
        for (int i = 0; i < N; i++)
            time[i] = Long.parseLong(br.readLine());

        bw.write(String.valueOf(solution(N, M, time)));

        bw.flush();
        bw.close();
    }
}