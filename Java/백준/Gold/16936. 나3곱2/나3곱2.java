import java.util.*;
import java.io.*;

public class Main {
    private static Map<Long, Integer> m = new HashMap<>();
    private static long[] arr;
    private static int N;
    private static List<Long> res;
    private static boolean[] visited;

    private static void solution() {
        for (int i = 0; i < N; i++) {
            res = new ArrayList<>();
            visited = new boolean[N];

            dfs(arr[i], 1);
            if (res.size() == N) return;
        }
    }

    private static void dfs(long s, int cnt) {
        res.add(s);

        if (cnt == N) return;

        // 나3
        if (s % 3 == 0 && m.containsKey(s / 3) && !visited[m.get(s / 3)]) {
            visited[m.get(s / 3)] = true;
            dfs(s / 3, cnt + 1);
            visited[m.get(s / 3)] = false;
        }

        // 곱2
        if (m.containsKey(s * 2) && !visited[m.get(s * 2)]) {
            visited[m.get(s * 2)] = true;
            dfs(s * 2, cnt + 1);
            visited[m.get(s * 2)] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            m.put(arr[i], i);
        }

        solution();

        for (long r : res)
            bw.write(String.valueOf(r) + " ");

        bw.flush();
    }
}