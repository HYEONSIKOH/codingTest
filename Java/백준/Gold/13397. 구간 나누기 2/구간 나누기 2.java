import java.util.*;
import java.io.*;

public class Main {
    private static int R;

    private static int solution(int N, int M, int[] arr) {

        int L = 0;
        int ans = Integer.MAX_VALUE;
        while (L <= R) {
            int mid = (L + R) / 2;
            int cnt = 1;

            int max = arr[0], min = arr[0];
            for (int i = 1; i < N; i++) {
                max = Math.max(max, arr[i]);
                min = Math.min(min, arr[i]);

                if (max - min > mid) {
                    cnt++;
                    max = arr[i];
                    min = arr[i];
                }
            }

            if (cnt <= M) {
                ans = Math.min(ans, mid);
                R = mid - 1;
            } else L = mid + 1;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            R = Math.max(R, arr[i]);
        }

        bw.write(String.valueOf(solution(N, M, arr)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}