import java.util.*;
import java.io.*;

public class Main {

    private static int[][] solution(int N, int[] arr) {
        int[][] ans = new int[N][2];
        for (int i = 0; i < N; i++)
            ans[i][1] = Integer.MAX_VALUE;

        // Stack
        Deque<Integer> s = new ArrayDeque<>();

        // 정방향 -> i보다 왼쪽에 있는 탑을 보는 경우의 수
        for (int i = 0; i < N; i++) {
            while (!s.isEmpty() && arr[s.peek()] <= arr[i])
                s.pop();

            // 개수
            ans[i][0] += s.size();

            // 가장 가까운 탑의 index와의 간격
            if (!s.isEmpty()) {
                int dist = Math.abs(s.peek() - i);     // 탐색한 탑과의 거리
                int idxDist = Math.abs(ans[i][1] - i); // 저장되어 있는 탑과의 거리

                if (idxDist > dist) ans[i][1] = s.peek();
                else if (idxDist == dist) ans[i][1] = Math.min(ans[i][1], s.peek());
            }

            s.push(i);
        }

        s.clear();

        // Reverse -> i보다 오른쪽에 있는 탑을 보는 경우의 수
        for (int i = N - 1; i >= 0; i--) {
            while (!s.isEmpty() && arr[s.peek()] <= arr[i])
                s.pop();

            // 개수
            ans[i][0] += s.size();

            // 가장 가까운 탑의 index와의 간격
            if (!s.isEmpty()) {
                int dist = Math.abs(s.peek() - i);     // 탐색한 탑과의 거리
                int idxDist = Math.abs(ans[i][1] - i); // 저장되어 있는 탑과의 거리

                if (idxDist > dist) ans[i][1] = s.peek();
                else if (idxDist == dist) ans[i][1] = Math.min(ans[i][1], s.peek());
            }

            s.push(i);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int[][] res = solution(N, arr);

        for (int i = 0; i < N; i++) {
            if (res[i][1] == Integer.MAX_VALUE) {
                bw.write("0\n");
            } else {
                bw.write(res[i][0] + " " + (res[i][1] + 1) + "\n");
            }
        }

        bw.flush();
    }
}