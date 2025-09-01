import java.io.*;
import java.util.*;

public class Main {
    private static int[] cookTimes;
    private static int N, K, C;
    private static long ans = Long.MAX_VALUE;
    private static final long maxTime = 1000000L * 1000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 요리사 수
        K = Integer.parseInt(st.nextToken()); // 총 요리의 개수
        C = Integer.parseInt(st.nextToken()); // 파이팅! 파이팅!

        cookTimes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            cookTimes[i] = Integer.parseInt(st.nextToken());

        backTrack(0);
        System.out.println(ans);
    }

    private static long getCookMinTime() {
        long L = 0, R = maxTime;

        while (L < R) {
            long mid = (L + R) / 2;
            long totalCooked = 0;

            for (int time : cookTimes) {
                totalCooked += mid / time;
                if (totalCooked >= K) break;
            }

            if (totalCooked >= K) R = mid;
            else L = mid + 1;
        }

        return L;
    }

    private static void backTrack(int idx){
        if (idx >= C) {
            // 탐색
            long minTime = getCookMinTime();

            // 답 초기화
            ans = Math.min(ans, minTime);

            return;
        }

        for (int i = 0; i < N; i++) {
            if (cookTimes[i] <= 1) {
                if (i == N-1) backTrack(idx + 1);
                continue;
            }
            cookTimes[i] -= 1;
            backTrack(idx + 1);
            cookTimes[i] += 1;
        }
    }
}