import java.util.*;
import java.io.*;

public class Main {

    private static int solution(int N, int K) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];

        q.offer(new int[]{ N, 0 });
        int ans = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            int[] cur = q.pop();

            if (K == cur[0]) ans = Math.min(ans, cur[1]);
            else {
                // i - 1
                if (cur[0] >= 1 && !visited[cur[0] - 1]) {
                    q.offer(new int[]{cur[0] - 1, cur[1] + 1});
                    visited[cur[0] - 1] = true;
                }

                // i + 1
                if (cur[0] + 1 <= 100000 && !visited[cur[0] + 1]) {
                    q.offer(new int[]{cur[0] + 1, cur[1] + 1});
                    visited[cur[0] + 1] = true;
                }

                // i * 2
                if (cur[0] * 2 <= 100000 && !visited[cur[0] * 2]) {
                    q.offer(new int[]{cur[0] * 2, cur[1] + 1});
                    visited[cur[0] * 2] = true;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(solution(N, K)));

        bw.flush();
        bw.close();
    }
}