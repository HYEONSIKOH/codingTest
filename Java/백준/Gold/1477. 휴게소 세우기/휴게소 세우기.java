import java.util.*;
import java.io.*;

public class Main {
    private static int solution(int M, int N, int L, int[] restAreas) {
        Arrays.sort(restAreas);
        int[] dist = new int[N + 1];
        
        for (int i = 0; i < N + 1; i++)
            dist[i] = restAreas[i+1] - restAreas[i];

        Arrays.sort(dist);

        int left = 1, right = dist[N];
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;

            for (int i = 0; i < N + 1; i++) {
                if (dist[i] % mid == 0) cnt += dist[i] / mid - 1;
                else cnt += dist[i] / mid;
            }

            if (cnt > M) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] restAreas = new int[N+2];
        restAreas[0] = 0;
        for (int i = 0; i < N; i++)
            restAreas[i] = Integer.parseInt(st.nextToken());
        restAreas[N] = L;

        bw.write(String.valueOf(solution(M, N, L, restAreas)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}