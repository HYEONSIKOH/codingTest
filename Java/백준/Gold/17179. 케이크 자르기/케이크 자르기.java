import java.util.*;
import java.io.*;

public class Main {
    private static int solution(int L, int K, int[] arr) {
        int size = arr.length;

        int left = 1, right = L;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int lastIdx = 0, cnt = 0;

            for (int i = 1; i < size; i++) {
                int minus = arr[i] - arr[lastIdx];
                if (minus >= mid) {
                    lastIdx = i;
                    cnt++;
                }
            }

            if (cnt > K) {
                left = mid + 1;
                res = Math.max(res, mid);
            }
            else right = mid - 1;
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 자르는 지점의 개수
        int M = Integer.parseInt(st.nextToken()); // 롤 케이크를 자르는 횟수
        int L = Integer.parseInt(st.nextToken()); // 롤 케이크 길이

        int[] arr = new int[M + 2];
        arr[0] = 0;
        for (int i = 1; i <= M; i++)
            arr[i] = Integer.parseInt(br.readLine());
        arr[M + 1] = L;

        Arrays.sort(arr);
        for (int i = 0; i < N; i++)
            bw.write(solution(L, Integer.parseInt(br.readLine()), arr) + "\n");

        // System.gc();
        bw.flush();
        bw.close();
    }
}