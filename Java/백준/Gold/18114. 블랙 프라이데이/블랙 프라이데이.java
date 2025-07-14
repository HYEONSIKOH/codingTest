import java.util.*;
import java.io.*;

public class Main {
    private static int solution(int N, int C, int[] arr) {
        Arrays.sort(arr);

        // 1개
        int L = 0, R = N - 1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] == C) return 1;
            else if (arr[mid] < C) L = mid + 1;
            else R = mid - 1;
        }

        // 2개
        L = 0; R = N - 1;
        while (L < R) {
            int sum = arr[L] + arr[R];
            if (sum == C) return 1;
            else if (sum < C) L++;
            else R--;
        }

        // 3개
        for (int i = 0; i < N - 2; i++) {
            L = i + 1; R = N - 1;
            while (L < R) {
                int sum = arr[i] + arr[L] + arr[R];
                if (sum == C) return 1;
                else if (sum < C) L++;
                else R--;
            }

        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(solution(N, C, arr)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}