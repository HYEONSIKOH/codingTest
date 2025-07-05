import java.util.*;
import java.io.*;

public class Main {
    private static int[] solution(int N, int H, int[] up, int[] down) {
        for (int i = H; i > 0; i--) down[i] += down[i + 1];
        for (int i = 1; i <= H; i++) up[i] += up[i - 1];

        int[] ans = new int[]{Integer.MAX_VALUE, 0};
        for (int i = 1; i <= H; i++) {
            int cnt = up[i] + down[i];
            if (ans[0] > cnt) {
                ans[0] = cnt;
                ans[1] = 1;
            } else if (ans[0] == cnt) ans[1]++;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] up = new int[H + 2];
        int[] down  = new int[H + 2];
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) up[H - h + 1]++;
            else down[h]++;
        }

        int[] res = solution(N, H, up, down);

        bw.write(res[0] + " " + res[1]);

        bw.flush();
        bw.close();
    }
}