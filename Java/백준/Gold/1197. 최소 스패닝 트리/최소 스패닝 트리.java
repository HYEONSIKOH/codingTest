import java.util.*;
import java.io.*;

public class Main {
    private static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static List<Node>[] g;
    private static int[] parent;

    private static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        g = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++)
            g[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            g[a].add(new Node(b, cost));
            g[b].add(new Node(a, cost));
        }

        System.out.println(solution());
    }

    private static int solution() {
        // 인스턴스 초기화
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;

        // 크루즈칼 알고리즘 (MST)
        return kruskal();
    }

    private static int kruskal() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 1; i <= V; i++) {
            for (Node next : g[i]) {
                pq.offer(new int[]{i, next.to, next.cost});
            }
        }

        // 간선이 한개인 경우 (양방향으로 연결해놔서 두 개로 예외)
        if (pq.size() == 2) return pq.poll()[2];

        int ans = 0, cnt = 0;
        while(true) {
            if (cnt == V - 1){
                pq.clear();
                return ans;
            }
            if (pq.isEmpty()) return -1;

            int[] cur = pq.poll();
            int a = cur[0];
            int b = cur[1];
            int cost = cur[2];

            int pa = find(a), pb = find(b);
            if (pa != pb) {
                parent[pa] = pb;
                ans += cost;
                cnt++;
            }
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }
}