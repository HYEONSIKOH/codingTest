import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] visited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1][M + 1];
        backtrack(0);
        System.out.println(ans);
    }

    static void backtrack(int idx) {
        if (idx == N * M) {
            ans++;
            return;
        }

        // 좌표 계산
        int r = idx / M + 1;
        int c = idx % M + 1;

        // 넴모를 놓지 않는 경우
        backtrack(idx + 1);

        // 넴모를 놓는 경우
        if (!visited[r][c - 1] || !visited[r - 1][c] || !visited[r - 1][c - 1]) {
            visited[r][c] = true;
            backtrack(idx + 1);
            visited[r][c] = false;
        }
    }
}