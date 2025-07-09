import java.util.*;
import java.io.*;

public class Main {
    private static long minDiffAbsSum = Long.MAX_VALUE;

    private static int[] solution(int N, int[] arr) {
        Arrays.sort(arr);
        int[] ans = new int[3];

        for (int i = 0; i < N; i++)
            binarySearch(N, arr, i, ans);

        Arrays.sort(ans);
        return ans;
    }

    private static int[] binarySearch(int N, int[] arr, int targetIdx, int[] ans) {
        int L = 0, R = N - 1;
        int target = arr[targetIdx];

        while (L < R) {
            if (targetIdx == L || targetIdx == R) {
                if (targetIdx == L) L++;
                else R--;

                continue;
            }

            long sum = (long)target + arr[L] + arr[R];
            if (Math.abs(sum) <= minDiffAbsSum) {
                minDiffAbsSum = Math.abs(sum);
                ans[0] = arr[L];
                ans[1] = arr[R];
                ans[2] = target;
            }

            if (sum < 0) L++;
            else R--;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int[] res = solution(N, arr);
        bw.write(res[0] + " " + res[1] + " " + res[2]);

        // System.gc();
        bw.flush();
        bw.close();
    }
}