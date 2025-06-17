import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int countStars(int x, int y, int L ,int[][] stars) {
        int cnt = 0;

        for (int[] s : stars) {
            if (x <= s[0] && s[0] <= x + L && y <= s[1] && s[1] <= y + L)
                cnt++;
        }

        return cnt;
    }

    private static int solution(int L, int K, int[][] stars) {
        int ans = -1;

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                ans = Math.max(ans, countStars(stars[i][0], stars[j][1], L, stars));
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 행
        int M = Integer.parseInt(st.nextToken()); // 열
        int L = Integer.parseInt(st.nextToken()); // 트램펄린
        int K = Integer.parseInt(st.nextToken()); // 별똥별 개수

        int[][] stars = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(K - solution(L, K, stars));
    }
}