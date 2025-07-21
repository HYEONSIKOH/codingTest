import java.util.*;
import java.io.*;

public class Main {

    private static int[][] arr;
    private static boolean[] visited;
    private static int answer = Integer.MAX_VALUE;

    private static void solution(int N) {
        visited = new boolean[N];
        dfs(0, 0, N);
    }

    private static void dfs(int idx, int count, int N) {
        if (count >= 1 && count < N) {
            int start = 0, link = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i] && visited[j]) start += arr[i][j];
                    else if (!visited[i] && !visited[j]) link += arr[i][j];
                }
            }
            answer = Math.min(answer, Math.abs(start - link));
        }

        for (int i = idx; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, count + 1, N);  // idx는 i+1로, count는 +1
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solution(N);
        bw.write(String.valueOf(answer));

        // System.gc();
        bw.flush();
        bw.close();
    }
}