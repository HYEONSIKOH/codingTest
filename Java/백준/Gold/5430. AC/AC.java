import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            char[] chs = br.readLine().toCharArray();
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");
            int[] arr = new int[N];

            for (int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            solution(chs, arr);
        }
    }

    private static void solution(char[] chs, int[] arr) {
        int L = 0, R = arr.length - 1;
        boolean isReversed = false;

        for (char ch : chs) {
            if (ch == 'R') isReversed = !isReversed;
            else {
                if (isReversed) R--;
                else L++;
            }

            if (R + 1 < L) {
                System.out.println("error");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();

        if (isReversed) {
            for (int j = R; j >= L; j--) {
                sb.append(arr[j]);
                if (j != L) sb.append(",");
            }
        } else {
            for (int j = L; j <= R; j++) {
                sb.append(arr[j]);
                if (j != R) sb.append(",");
            }
        }

        System.out.println("[" + sb + "]");
    }

    private static int[] funR (int[] arr) {
        int[] res = new int[arr.length];

        int idx = 0;
        for (int i = arr.length - 1; i >= 0; i--)
            res[idx++] = arr[i];

        return res;
    }
}