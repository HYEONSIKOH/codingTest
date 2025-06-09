import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int solution(int N, int[] top) {
        int ans = -1;
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            // 왼쪽: 기울기가 작아져야 보임
            double maxLeft = Double.POSITIVE_INFINITY;
            for (int j = i - 1; j >= 0; j--) {
                double g = (double)(top[j] - top[i]) / (j - i);
                if (g < maxLeft) {
                    maxLeft = g;
                    cnt++;
                }
            }

            // 오른쪽: 기울기가 커져야 보임
            double maxRight = Double.NEGATIVE_INFINITY;
            for (int j = i + 1; j < N; j++) {
                double g = (double)(top[j] - top[i]) / (j - i);
                if (g > maxRight) {
                    maxRight = g;
                    cnt++;
                }
            }


            ans = Math.max(ans, cnt);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] top = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            top[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, top));
    }
}