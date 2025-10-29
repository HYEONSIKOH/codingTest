import java.util.*;
import java.io.*;

public class Main {
    private static int N, K;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(solution());
    }

    private static int solution() {
        visited = new boolean[N+1];
        int cnt = 0;

        // 0과 1은 True
        visited[0] = true;
        visited[1] = true;

        int idx = 2;
        while (true) {
            while(visited[idx]) idx++;

            for (int i = 1; i <= N/idx; i++) {
                int num = i * idx;
                if (!visited[num]) {
                    cnt++;
                    visited[num] = true;
                }
                if (cnt == K) return num;
            }
        }
    }
}