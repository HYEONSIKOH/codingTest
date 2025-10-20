import java.util.*;
import java.io.*;

public class Main {
    private static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        solution();
    }

    private static void solution() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) q.add(i);

        while (true) {
            for (int i = 1; i < K; i++) q.add(q.poll());
            sb.append(q.poll());
            
            if (q.isEmpty()) break;
            else sb.append(", ");
        }

        sb.append(">");
        System.out.println(sb);
    }
}