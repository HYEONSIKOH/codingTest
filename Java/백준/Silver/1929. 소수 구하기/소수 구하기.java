import java.util.*;
import java.io.*;

public class Main {
    private static final StringBuilder sb = new StringBuilder();

    private static int M, N;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        solution();
        System.out.println(sb);
    }

    private static void solution() {
        visited = new boolean[N+1];
        visited[0] = true;
        visited[1] = true;

        int idx = 2;
        while (true) {
            while(idx <= N && visited[idx]) idx++;

            if (idx > N) break;
            if (M <= idx) sb.append(idx).append("\n");

            for (int i = 1; i <= N/idx; i++) {
                int num = i * idx;
                if (!visited[num])
                    visited[num] = true;
            }
        }
    }
}