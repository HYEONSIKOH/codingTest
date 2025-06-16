import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static List<Node>[] village;
    private static List<Node>[] reversedVillage;

    private static int[] reverseDist;
    private static int[] dist;

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

    private static void dijkstra (int N, int s) {
        dist = new int[N];
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
    }

    private static void reverseDijkstra (int N, int s) {
        reverseDist = new int[N];
        Arrays.fill(reverseDist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(s, 0));
        reverseDist[s] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (Node nextNode : reversedVillage[node.cur]) {
                int nextCur = nextNode.cur;
                int nextDist = node.dist + nextNode.dist;

                if (nextDist < reverseDist[nextCur]) {
                    reverseDist[nextCur] = nextDist;
                    pq.add(new Node(nextCur, nextDist));
                }
            }
        }
    }

    private static void solution(int N, int X) {
        int ans = -1;
        reverseDijkstra(N, X);
        dijkstra(N, X);

        for (int i = 0; i < N; i++) {
            if (i != X)
                ans = Math.max(ans, dist[i] + reverseDist[i]);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N명의 학생 & N개의 마을정점?
        int M = Integer.parseInt(st.nextToken()); // M개의 도로 개수
        int X = Integer.parseInt(st.nextToken()); // 파티를 할 마을번호?

        village = new ArrayList[N];
        reversedVillage = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            village[i] = new ArrayList<>();
            reversedVillage[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            village[s].add(new Node(e, t));
            reversedVillage[e].add(new Node(s, t));
        }

        solution(N, X - 1);
    }
}