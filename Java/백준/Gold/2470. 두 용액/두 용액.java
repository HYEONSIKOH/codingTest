import java.util.*;
import java.io.*;

public class Main {
    private static int[] solution(int N, int[] sol) {
        Arrays.sort(sol);
        int temp = Integer.MAX_VALUE;
        int[] ans = new int[2];

        int L = 0, R = sol.length - 1;
        while (L < R) {
            int sum = sol[L] + sol[R];
            if (Math.abs(sum) < temp) {
                temp = Math.abs(sum);
                ans[0] = sol[L];
                ans[1] = sol[R];
            }

            if (sum < 0) L++;
            else if (sum > 0) R--;
            else break;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] sol = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            sol[i] = Integer.parseInt(st.nextToken());

        int[] res = solution(N, sol);

        bw.write(res[0] + " " + res[1]);

        bw.flush();
        bw.close();
    }
}