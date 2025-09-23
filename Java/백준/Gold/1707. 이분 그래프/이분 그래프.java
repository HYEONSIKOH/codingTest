import java.util.*;
import java.io.*;

public class Main {
    private static int V, E;
    private static List<Integer>[] graph;
    private static byte[] visited;

    private static StringBuilder sb = new StringBuilder();
    private static boolean isPossible = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new List[V];
            for (int i = 0; i < V; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                if (graph[a] == null) graph[a] = new ArrayList<>();
                if (graph[b] == null) graph[b] = new ArrayList<>();

                graph[a].add(b);
                graph[b].add(a);
            }

            solution();
        }

        System.out.print(sb);
    }

    private static void solution() {
        visited = new byte[V];
        isPossible = true;

        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfs(i);
            }

            if (!isPossible) break;
        }

        sb.append(isPossible ? "YES" : "NO").append("\n");
    }

    private static void dfs(int node) {
        for (int next : graph[node]) {
            if (!isPossible) return;

            byte nextNum = (visited[node] == 1) ? (byte)2 : 1;

            if (visited[next] == visited[node]) {
              isPossible = false;
              return;
            } else {
                if (visited[next] == 0) {
                    visited[next] = nextNum;
                    dfs(next);
                }
            }
        }
    }
}