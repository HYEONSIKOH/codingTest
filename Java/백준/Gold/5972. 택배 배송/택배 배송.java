import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static List<List<Pair>> v = new ArrayList<>();
    private static int[] dist;

    private static class Pair {
        int node;
        int cost;

        public Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void solution() {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.add(new Pair(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            int s = pq.peek().node;    // 현재 헛간 번호
            int cost = pq.peek().cost; // 비용
            pq.poll();

            for (Pair p : v.get(s)) {
                int nextE = p.node;
                int nextCost = p.cost + cost;

                if (dist[nextE] > nextCost) {
                    dist[nextE] = nextCost;
                    pq.add(new Pair(nextE, nextCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 헛간 개수
        int M = Integer.parseInt(st.nextToken()); // 간선 비용 & 개수

        for (int i = 0; i <= N; i++) v.add(new ArrayList<>());
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cos = Integer.parseInt(st.nextToken());

            v.get(s).add(new Pair(e, cos));
            v.get(e).add(new Pair(s, cos));
        }

        solution();
        System.out.println(dist[N]);
    }
}
