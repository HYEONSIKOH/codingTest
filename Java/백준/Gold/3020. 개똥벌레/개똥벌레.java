import java.util.*;
import java.io.*;

public class Main {
    private static int[] solution(int N, int H, int[] up, int[] down) {
        Arrays.sort(up);
        Arrays.sort(down);

        int[] ans = new int[]{Integer.MAX_VALUE, 0};
        for (int i = 1; i <= H; i++) {
            int cnt = binarySearch(up, i, N/2) + binarySearch(down, H - i + 1, N/2);
            if (ans[0] > cnt) {
                ans[0] = cnt;
                ans[1] = 1;
            } else if (ans[0] == cnt) ans[1]++;
        }

        return ans;
    }

    private static int binarySearch(int[] arr, int target, int R) {
        int L = 0;

        while (L < R) {
            int M = (L + R) / 2;

            if (arr[M] < target) L = M + 1;
            else R = M;
        }

        return arr.length - R;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] up = new int[N/2];
        int[] down  = new int[N/2];
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) up[i/2] = Integer.parseInt(br.readLine());
            else down[i/2] = Integer.parseInt(br.readLine());
        }

        int[] res = solution(N, H, up, down);

        bw.write(res[0] + " " + res[1]);

        bw.flush();
        bw.close();
    }
}