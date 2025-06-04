import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int solution(int N, int S, int[] arr) {
        int sum = arr[0], L = 0, R = 0;
        int ans = Integer.MAX_VALUE;
        while (L < N && R < N) {
            if (sum < S) {
                if (++R < N) sum += arr[R];
            }
            else {
                ans = Math.min(ans, R - L + 1);
                sum -= arr[L++];
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, S, arr));
    }
}