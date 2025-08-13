import java.io.*;
import java.util.*;

public class Main {
    private static List<Edge>[] graph;
    private static int ans = Integer.MAX_VALUE;

    private static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 교차로 개수 N, 골목 개수 M, 시작 교차로 번호 A, 도착 교차로 번호 B, 가진 돈 C
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        System.out.print(solution(A, B, C));
    }

    private static int solution(int start, int end, int money) {
        backtrack(start, money, -1, end, (1 << start));

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static void backtrack(int cur, int money, int max, int end, int visited) {
        if (cur == end) {
            ans = Math.min(ans, max);
            return;
        }

        for (Edge e : graph[cur]) {
            if ((visited & (1 << e.to)) == 0 && money >= e.cost) {
                backtrack(e.to, money - e.cost, Math.max(max, e.cost), end, visited | (1 << e.to));
            }
        }
    }
}