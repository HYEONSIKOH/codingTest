import java.util.*;
import java.io.*;

import static java.lang.Integer.*;

public class Main {
    private static List<Integer>[] arr;

    private static String solution(int N, int M) {
        int visited = 0;

        for (int i = 0; i < N; i++)
            if (backtrack(i, 1, visited | (1 << i)))
                return "1";

        return "0";
    }

    private static boolean backtrack(int idx, int cnt, int visited) {
        if (cnt == 5) return true;

        for (int next : arr[idx]) {
            if ((visited & (1 << next)) == 0) {
                if (backtrack(next, cnt + 1, visited | (1 << next))) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());

        arr = new ArrayList[N];
        for (int i = 0; i < N; i++)
            arr[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = parseInt(st.nextToken());
            int e = parseInt(st.nextToken());

            arr[s].add(e);
            arr[e].add(s);
        }

        bw.write(solution(N, M));

        // System.gc();
        bw.flush();
        bw.close();
    }
}