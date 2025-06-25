import java.util.*;
import java.io.*;

public class Main {
    private static PriorityQueue<int[]> pq;

    private static int solution() {
        int ans = 0;
        int endTime = 0;
        while(!pq.isEmpty()) {
            int[] arr = pq.poll();
            int s = arr[0];
            int e = arr[1];

            if (endTime <= s ) {
                endTime = e;
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        bw.close();
    }
}