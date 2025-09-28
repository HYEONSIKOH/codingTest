import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static List<Integer>[] g;
    static int[] indeg;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];
        indeg = new int[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            indeg[b]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) if (indeg[i] == 0) q.add(i);

        while (!q.isEmpty()) {
            int u = q.poll();
            sb.append(u).append(' ');
            for (int v : g[u]) {
                if (--indeg[v] == 0) q.add(v);
            }
        }

        System.out.print(sb.toString().trim());
    }
}