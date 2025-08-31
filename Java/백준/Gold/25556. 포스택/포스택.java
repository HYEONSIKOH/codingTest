import java.io.*;
import java.util.*;

public class Main {
    private static int[] stack = new int[4];
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int cnt = 0; cnt < N; cnt++) {
            int x = Integer.parseInt(st.nextToken());
            int best = -1, bestTop = 0;
            for (int i = 0; i < 4; i++) {
                int t = stack[i];
                if (t < x && t >= bestTop) {
                    bestTop = t;
                    best = i;
                }
            }
            if (best == -1) {
                System.out.println("NO");
                return;
            }
            stack[best] = x;
        }
        System.out.println("YES");
    }
}