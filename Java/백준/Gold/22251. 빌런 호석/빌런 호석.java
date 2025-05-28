import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[][] led = {
            {1, 1, 1, 0, 1, 1, 1}, // 0
            {0, 0, 1, 0, 0, 0, 1}, // 1
            {0, 1, 1, 1, 1, 1, 0}, // 2
            {0, 1, 1, 1, 0, 1, 1}, // 3
            {1, 0, 1, 1, 0, 0, 1}, // 4
            {1, 1, 0, 1, 0, 1, 1}, // 5
            {1, 1, 0, 1, 1, 1, 1}, // 6
            {0, 1, 1, 0, 0, 0, 1}, // 7
            {1, 1, 1, 1, 1, 1, 1}, // 8
            {1, 1, 1, 1, 0, 1, 1}  // 9
    };

    // N: 숫자 범위, K: 자릿수, P: 반전 led 개수, X: 현재 층 수
    private static void solution(int N, int K, int P, int X) {
        int ans = 0;
        List<int[]> curFloor = new ArrayList<>();

        // 현재 층을 led 배열로 변환
        int temp = X;
        for (int i = 0; i < K; i++) {
            curFloor.add(led[temp % 10]);
            temp /= 10;
        }

        for (int i = 1; i <= N; i++) {
            if (i == X) continue;
            List<int[]> targetFloor = new ArrayList<>();
            int target = i;

            // 비교 층을 led 배열로 변환
            for (int j = 0; j < K; j++) {
                targetFloor.add(led[target % 10]);
                target /= 10;
            }

            // 현재 층 vs 비교 층
            int cnt = 0;
            for (int j = 0; j < K; j++) {
                for (int l = 0; l < 7; l++) {
                    if (curFloor.get(j)[l] != targetFloor.get(j)[l])
                        cnt++;
                }
            }

            if (cnt <= P) ans++;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N, K, P, X;

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        solution(N, K, P, X);
    }
}
