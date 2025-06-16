import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static class Node implements Comparable<Node>{
        int cur;
        int dist;

        Node(int cur, int dist) {
            this.cur = cur;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist); // dist 내림차순 정렬
        }
    }

    private static int dijkstra (int N, int s, int x, List<Node>[] village) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(s, 0));
        dist[s] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (Node nextNode : village[node.cur]) {
                int nextCur = nextNode.cur;
                int nextDist = node.dist + nextNode.dist;

                if (nextDist < dist[nextCur]) {
                    dist[nextCur] = nextDist;
                    pq.add(new Node(nextCur, nextDist));
                }
            }
        }

        return dist[x];
    }

    private static void solution(int N, int X, List<Node>[] village) {
        int ans = -1;
        for (int i = 0; i < N; i++) {
            if (i != X)
                ans = Math.max(ans, dijkstra(N, i, X, village) + dijkstra(N, X, i, village));
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N명의 학생 & N개의 마을정점?
        int M = Integer.parseInt(st.nextToken()); // M개의 도로 개수
        int X = Integer.parseInt(st.nextToken()); // 파티를 할 마을번호?

        List<Node>[] village = new ArrayList[N];
        for (int i = 0; i < N; i++)
            village[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            village[s].add(new Node(e, t));
        }

        solution(N, X - 1, village);
    }
}