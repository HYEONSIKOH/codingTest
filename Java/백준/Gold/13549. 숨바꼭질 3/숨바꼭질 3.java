import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
        int distance;
        int cur;

        Node(int dist, int cur) {
            this.distance = dist;
            this.cur = cur;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[] dist = new int[100002];

    public static void solution(int N, int K) {
        dist[N] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(0, N));

        while(!pq.isEmpty()) {
            Node n = pq.poll();
            int cur = n.cur;
            int distance = n.distance;

            if (dist[cur] < distance) continue;

            // 한 칸 뒤로가기
            if (cur >= 1 && dist[cur - 1] > distance + 1) {
                dist[cur - 1] = distance + 1;
                pq.add(new Node(distance + 1, cur - 1));
            }

            // 한 칸 앞으로 가기
            if (cur + 1 <= K && dist[cur + 1] > distance + 1) {
                dist[cur + 1] = distance + 1;
                pq.add(new Node(distance + 1, cur + 1));
            }

            // 두 배로 순간이동하기
            if (cur * 2 <= 100000 && dist[cur * 2] > distance) {
                dist[cur * 2] = distance;
                pq.add(new Node(distance, cur * 2));
            }
        }
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, Integer.MAX_VALUE);

        solution(N, K);

        System.out.print(dist[K]);
    }
}
