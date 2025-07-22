import java.util.*;
import java.io.*;

public class Main {
    private static boolean visited[];
    private static int answer = 0;
    private static int[] arr;

    private static void solution(int N, int L, int R, int X) {
        visited = new boolean[N];
        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            backtrack(i, i, arr[i], L, R, N, X);
            visited[i] = false;
        }
    }

    private static void backtrack(int idx, int minIdx ,int sum, int L, int R, int N, int X) {
        if (sum > R) return;
        if (L <= sum){
            if (arr[idx] - arr[minIdx] >= X)
                answer++;
        }

        for (int i = idx + 1; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(i, minIdx, sum + arr[i], L, R, N, X);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        solution(N, L, R, X);
        bw.write(String.valueOf(answer));

        // System.gc();
        bw.flush();
        bw.close();
    }
}