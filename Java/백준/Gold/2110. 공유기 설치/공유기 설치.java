import java.util.*;
import java.io.*;

public class Main {
    private static int solution(int N, int C, int[] routers) {
        Arrays.sort(routers);

        int ans = -1;
        int L = 1;
        int R = routers[N - 1] - routers[0];
        while (L <= R) {
            int mid = (L + R) / 2;
            int r = routers[0], cnt = 1;

            for (int i = 1; i < N; i++) {
                if (routers[i] - r >= mid){
                    cnt++;
                    r = routers[i];
                }
            }

            if (cnt >= C) {
                ans = mid;
                L = mid + 1;
            } else R = mid - 1;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] routers = new int[N];
        for (int i = 0; i < N; i++)
            routers[i] = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(solution(N, C, routers)));

        bw.flush();
        bw.close();
    }
}