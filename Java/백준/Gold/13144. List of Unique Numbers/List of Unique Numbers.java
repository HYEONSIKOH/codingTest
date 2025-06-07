import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static long solution(int N, Integer[] arr) {
        long ans = 0;
        int[] visited = new int[100001];

        int L = 0, R = 0;
        while (R < N) {
            if (visited[arr[R]] == 0) visited[arr[R++]]++;
            else {
                ans += R - L;
                visited[arr[L++]]--;
            }
        }

        ans += (long)(R - L) * (R - L + 1) / 2;
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, arr));
    }
}