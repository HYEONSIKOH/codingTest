import java.util.*;
import java.io.*;

public class Main {
    private static class Node {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static int N;
    private static int[] maxDist = new int[2];

    private static List<Node>[] g;
    private static byte[] visited;

    /**
     * 1. 한 지점에서 가장 먼 지점까지의 거리를 구함 => 지름의 한 끝점
     * 2. 1에서 구한 지점에서 가장 먼 지점까지의 거리를 구함 => 지름의 다른 끝 점
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        if (N == 1) {
            System.out.print(0);
            return;
        }

        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            g[a].add(new Node(b, c));
            g[b].add(new Node(a, c));
        }

        System.out.print(solution());
    }

    private static int solution() {
        visited = new byte[N + 1];
        dfs(1, 0);

        visited = new byte[N + 1];
        dfs(maxDist[0], 0);

        return maxDist[1];
    }

    private static void dfs(int start, int cost) {
        visited[start] = 1;

        if (cost > maxDist[1]) {
            maxDist[0] = start;
            maxDist[1] = cost;
        }

        for (Node next : g[start]) {
            if (visited[next.to] == 0)
                dfs(next.to, cost + next.cost);
        }
    }
}