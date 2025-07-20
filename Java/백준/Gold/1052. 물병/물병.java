import java.util.*;
import java.io.*;

public class Main {

    private static int solution(int N, int K) {
        int ans = 0;
        while(Integer.bitCount(N) > K) {
            ans += N & -N;
            N += N & -N;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(solution(N, K)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}