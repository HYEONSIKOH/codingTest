import java.util.*;
import java.io.*;

public class Main {
    private static long solution(int N, int[] arr) {
        Arrays.sort(arr);

        long ans = 0;
        for (int i = 0; i < N - 2; i++) {
            if (arr[i] > 0) break;

            int L = i + 1, R = N - 1;
            while (L < R) {
                long sum = (long)arr[i] + arr[L] + arr[R];
                if (sum > 0) R--;
                else if (sum < 0) L++;
                else {
                    if (arr[L] == arr[R]) {
                        ans += (R - L) * (R - L + 1) / 2;
                        break;
                    } else {
                        int cntL = 1, cntR = 1;
                        while (L + 1 < R && arr[L] == arr[L + 1]) {
                            L++;
                            cntL++;
                        }

                        while (R - 1 > L && arr[R] == arr[R - 1]) {
                            R--;
                            cntR++;
                        }

                        L++; R--;
                        ans += cntL * cntR;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(solution(N, arr)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}